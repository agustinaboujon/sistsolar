package sistema.solar.condiciones.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "forecast")
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_forecast")
    private Long idForecast;
    private String weather;

    @Column(name = "id_type_forecast")
    private Long idTypeForecast;
    private Integer period;
    private Integer angle;

}
