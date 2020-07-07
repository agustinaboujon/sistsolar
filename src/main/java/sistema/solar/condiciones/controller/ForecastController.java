package sistema.solar.condiciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sistema.solar.condiciones.entity.Forecast;
import sistema.solar.condiciones.service.ForecastService;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/forecast")
public class ForecastController {

    @Autowired
    private ForecastService forecastService;

    private Logger logger;

    @PostMapping(path = "/record-weather")
    public void recordWeather(@RequestParam("year") Long year){
        forecastService.setForecast(year);
    }

    @GetMapping(path = "/get-forecast-byday")
    public Forecast getForecastByDay(@RequestParam("day") Integer day){
        try{
            return forecastService.getForecast(Long.valueOf(day));
        }catch (Exception e){
            logger.log(Level.WARNING,"No hay detalles en la base de ese día \n");
        }
    return null;
    }
}
