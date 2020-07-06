package sistema.solar.condiciones.service;

import org.springframework.stereotype.Component;
import sistema.solar.condiciones.entity.Period;

import java.util.List;

@Component
public interface PeriodService {
    public List<Period> listPeriod();
}
