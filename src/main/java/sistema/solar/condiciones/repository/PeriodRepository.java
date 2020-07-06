package sistema.solar.condiciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistema.solar.condiciones.entity.Period;

@Repository("periodRepository")
public interface PeriodRepository extends JpaRepository<Period, Long> {
}
