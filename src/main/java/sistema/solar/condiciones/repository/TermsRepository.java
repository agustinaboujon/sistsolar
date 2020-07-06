package sistema.solar.condiciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistema.solar.condiciones.entity.Planet;

@Repository("termsRepository")
public interface TermsRepository extends JpaRepository<Planet, Long> {
}
