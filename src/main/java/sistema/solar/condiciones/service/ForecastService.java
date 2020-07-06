package sistema.solar.condiciones.service;

import sistema.solar.condiciones.entity.Forecast;


public interface ForecastService {
    public void setForecast (Long year);
    public Forecast getForecast(Long day);

}
