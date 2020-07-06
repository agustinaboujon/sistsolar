package sistema.solar.condiciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistema.solar.condiciones.entity.Forecast;

@Repository("forecastRepository")
public interface ForecastRepository extends JpaRepository<Forecast, Long>{
}
