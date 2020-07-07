package sistema.solar.condiciones.service.Impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import sistema.solar.condiciones.entity.Forecast;
import sistema.solar.condiciones.entity.Period;
import sistema.solar.condiciones.repository.ForecastRepository;
import sistema.solar.condiciones.repository.PeriodRepository;
import sistema.solar.condiciones.service.ForecastService;
import sistema.solar.condiciones.service.PeriodService;
import sistema.solar.condiciones.service.TermsService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Data
public class PeriodServiceImpl implements PeriodService {

    @Autowired
    @Qualifier("periodRepository")
    private PeriodRepository periodRepository;

    private Logger logger;

    @Override
    public List<Period> listPeriod() {
        logger.log(Level.INFO,"Detalles de periodos: \n", periodRepository.findAll());
        return periodRepository.findAll();
    }

}
