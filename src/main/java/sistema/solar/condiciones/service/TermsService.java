package sistema.solar.condiciones.service;

import org.springframework.stereotype.Component;
import sistema.solar.condiciones.entity.Planet;

import java.util.List;

@Component
public interface TermsService {
    public List<Planet> getPlanet();
    public boolean isAligned(Long day);
    public Integer isInATriangle(Long day);
    public Long angleBetweenPlanet(Long angle1, Long angle2);
    public boolean itsOptimal(Long day);

}
