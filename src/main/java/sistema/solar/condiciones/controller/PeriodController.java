package sistema.solar.condiciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sistema.solar.condiciones.entity.Period;
import sistema.solar.condiciones.service.PeriodService;

import java.util.List;

@RestController
@RequestMapping(path = "/period")
public class PeriodController {

    @Autowired
    private PeriodService periodService;

    @GetMapping(path = "/get-period")
    public List<Period> getPeriod(){ return periodService.listPeriod(); }

}
