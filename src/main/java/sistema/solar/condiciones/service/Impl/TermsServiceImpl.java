package sistema.solar.condiciones.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sistema.solar.condiciones.entity.Planet;
import sistema.solar.condiciones.repository.TermsRepository;
import sistema.solar.condiciones.service.TermsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TermsServiceImpl implements TermsService {

    @Autowired
    @Qualifier("termsRepository")
    TermsRepository termsRepository;

    private Logger logger;

    private List<Planet> listPlanet(){
        /*List<Planet> list = new ArrayList<>();
        Planet ferengi = new Planet();
        Planet betasoide = new Planet();
        Planet vulcano = new Planet();

        ferengi.setAngle((long)1);
        ferengi.setDirection(false);
        ferengi.setName("Ferengi");
        ferengi.setRadio((long) 500);

        betasoide.setAngle((long)3);
        betasoide.setDirection(false);
        betasoide.setRadio((long)2000);
        betasoide.setName("Betasoide");

        vulcano.setRadio((long)1000);
        vulcano.setName("Vulcano");
        vulcano.setDirection(true);
        vulcano.setAngle((long)5);

        list.add(vulcano);
        list.add(betasoide);
        list.add(ferengi);

        return list;*/
        return termsRepository.findAll();
    }

    private List<Long> listAngle(Long day){
        List<Long> angleList = new ArrayList(Arrays.asList(angleBetweenPlanet(getPlanet().get(0).ubicationByDay(day),getPlanet().get(1).ubicationByDay(day)),
                angleBetweenPlanet(getPlanet().get(1).ubicationByDay(day),getPlanet().get(2).ubicationByDay(day)),
                angleBetweenPlanet(getPlanet().get(0).ubicationByDay(day),getPlanet().get(2).ubicationByDay(day))));
        return angleList;
    }

    @Override
    public List<Planet> getPlanet() {
        logger.log(Level.INFO,"Detalles de los planetas: \n", listPlanet());
        return listPlanet();
    }

    @Override
    public boolean isAligned(Long day) {
        final Long data = getPlanet().get(0).ubicationByDay(day);
        return getPlanet().stream().allMatch(planet -> (data.equals(planet.ubicationByDay(day))
                ||data.equals(planet.getOpposite(planet.ubicationByDay(day)))));
    }

    @Override
    public Integer isInATriangle(Long day){

        List<Long> angleList = listAngle(day);
        if(!(angleList.stream().allMatch(angle -> angle>=90)||angleList.stream().anyMatch(angle -> angle==180))){
            return -1;
        }else{
                Long sum = angleList.get(0) + angleList.get(1) + angleList.get(2);
                return Integer.valueOf(Math.toIntExact(sum));
            }
    }

    @Override
    public Long angleBetweenPlanet(Long angle1, Long angle2) {
        Long biggestNum = Math.max(angle1,angle2);
        Long smallerNum = Math.min(angle1,angle2);
        Long angle = biggestNum - smallerNum;
        return angle;
    }

    @Override
    public boolean itsOptimal(Long day) {
        List<Long> angleList = listAngle(day);
        if(angleList.stream().anyMatch(angle -> angle==180||angle==0)){
            return true;
        }
        return false;
    }


}
