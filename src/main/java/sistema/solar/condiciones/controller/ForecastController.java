package sistema.solar.condiciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sistema.solar.condiciones.entity.Forecast;
import sistema.solar.condiciones.service.ForecastService;

@RestController
@RequestMapping(path = "/forecast")
public class ForecastController {

    @Autowired
    private ForecastService forecastService;

    @PostMapping(path = "/record-weather")
    public void recordWeather(@RequestParam("year") Long year){
        forecastService.setForecast(year);
    }

    @GetMapping(path = "/get-forecast-byday")
    public Forecast getForecastByDay(@RequestParam("day") Integer day){
        try{
            return forecastService.getForecast(Long.valueOf(day));
        }catch (Exception e){
            System.out.println("No se encontró registro de ese día");
    }
    return null;
    }
}
