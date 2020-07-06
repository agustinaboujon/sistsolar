package sistema.solar.condiciones;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
//TODO
    @Bean("termsRest")
    public RestTemplate registerRestTemplate(){
        return new RestTemplate();
    }

}
