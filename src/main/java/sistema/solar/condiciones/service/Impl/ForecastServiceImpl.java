package sistema.solar.condiciones.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import sistema.solar.condiciones.entity.Forecast;
import sistema.solar.condiciones.entity.Period;
import sistema.solar.condiciones.constants.WeatherConstants;
import sistema.solar.condiciones.repository.ForecastRepository;
import sistema.solar.condiciones.repository.PeriodRepository;
import sistema.solar.condiciones.repository.TermsRepository;
import sistema.solar.condiciones.service.ForecastService;
import sistema.solar.condiciones.service.TermsService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Component
public class ForecastServiceImpl implements ForecastService {

    @Autowired
    TermsService termsService;

    @Autowired
    @Qualifier("termsRepository")
    TermsRepository termsRepository;

    @Autowired
    @Qualifier("periodRepository")
    private PeriodRepository periodRepository;

    @Autowired
    @Qualifier("forecastRepository")
    ForecastRepository forecastRepository;

    private Logger logger;
    @Override
    public void setForecast(Long year) {
        Integer day = Math.toIntExact(year * 364);

        Integer myType = -1;
        Period droughtPeriod = new Period(0, WeatherConstants.DROUGHT,"Drought", 0, 0);
        Period optimalPeriod = new Period(0, WeatherConstants.OPTIMAL,"Optimal", 0, 0);
        Period rainingPeriod = new Period(0, WeatherConstants.RAINING,"Raining",0, 0);
        Period unknowPeriod = new Period(0, WeatherConstants.UNKNOW,"Unknow",0, 0);

        /*myType:
        *-1 = start
        * 0 = drought
        * 1 = optimal
        * 2 = raining
        * 3 = unknow*/

        for (int i = 0; i <= day; i++) {
            Forecast forecast = new Forecast();
            if (termsService.isAligned(Long.valueOf(i)) || i==0) {
                if(myType != 0){
                    myType = 0;
                    droughtPeriod.setQuantity(droughtPeriod.getQuantity() +1 );
                }
                forecast.setWeather("Drought");
                forecast.setPeriod(droughtPeriod.getQuantity());
                forecast.setIdTypeForecast(WeatherConstants.DROUGHT);
            } else if (termsService.itsOptimal(Long.valueOf(i))) {
                if(myType != 1){
                    myType = 1;
                    optimalPeriod.setQuantity(optimalPeriod.getQuantity() +1 );
                }
                forecast.setWeather("Optimal");
                forecast.setPeriod(optimalPeriod.getQuantity());
                forecast.setIdTypeForecast(WeatherConstants.OPTIMAL);

            } else if (termsService.isInATriangle(Long.valueOf(i)) > 0) {
                if(myType != 2){
                    myType = 2;
                    rainingPeriod.setQuantity(rainingPeriod.getQuantity() +1 );
                }
                forecast.setAngle(termsService.isInATriangle(Long.valueOf(i)));
                forecast.setWeather("Raining");
                forecast.setPeriod(rainingPeriod.getQuantity());
                forecast.setIdTypeForecast(WeatherConstants.RAINING);
                if(forecast.getAngle() > rainingPeriod.getMaxPoint()){
                    rainingPeriod.setMaxPoint(forecast.getAngle());
                    rainingPeriod.setDayOfMaxPoint(i);
                }

            } else {
                if(myType != 3){
                    myType = 3;
                    unknowPeriod.setQuantity(unknowPeriod.getQuantity() +1 );
                }
                forecast.setWeather("Unknow");
                forecast.setPeriod(unknowPeriod.getQuantity());
                forecast.setIdTypeForecast(WeatherConstants.UNKNOW);
            }

            forecastRepository.save(forecast);
        }
        periodRepository.deleteAll();
        periodRepository.save(droughtPeriod);
        periodRepository.save(optimalPeriod);
        periodRepository.save(rainingPeriod);
        periodRepository.save(unknowPeriod);

        logger.log(Level.INFO,"Recopilación de periodos de sequía \n", droughtPeriod);
        logger.log(Level.INFO,"Recopilación de periodos óptimos \n", optimalPeriod);
        logger.log(Level.INFO,"Recopilación de periodos de lluvia \n", rainingPeriod);
        logger.log(Level.INFO,"Recopilación de periodos desconocidos \n", unknowPeriod);
        logger.log(Level.INFO,"Recopilación de periodos realizada con éxito\n");
    }

    @Override
    public Forecast getForecast(Long day) {
        logger.log(Level.INFO,"Clima del día consultado: \n", forecastRepository.findById(day).get());
        return forecastRepository.findById(day).get();
    }

    public Long getMaxPoint() {
        List<Forecast> listPoint = forecastRepository.findAll();
        List<Forecast> listRaining = new ArrayList<>();
        for(Forecast forecast : listPoint){
            if(forecast.getIdTypeForecast().equals(Long.valueOf(3)))
                listRaining.add(forecast);
        }
        Forecast var = listRaining.stream().max(Comparator.comparing(item -> item.getAngle())).get();
        logger.log(Level.INFO,"Día con pico máximo de lluvia: \n", var.getIdForecast());

        return var.getIdForecast();
    }
}
