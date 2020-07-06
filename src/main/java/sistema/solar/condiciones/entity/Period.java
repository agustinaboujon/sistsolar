package sistema.solar.condiciones.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "period")
@Data
@NoArgsConstructor
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_period")
    private Long idPeriod;

    @Column(name = "max_point")
    private Integer maxPoint;

    @Column(name = "id_type_forecast")
    private Long idTypeForecast;

    private String weather;
    private Integer quantity;

    @Column(name = "day_of_max_point")
    private Integer dayOfMaxPoint;

    public Period(Integer maxPoint, Long idTypeForecast, String weather, Integer quantity, Integer dayOfMaxPoint) {
        this.maxPoint = maxPoint;
        this.idTypeForecast = idTypeForecast;
        this.weather = weather;
        this.quantity = quantity;
        this.dayOfMaxPoint = dayOfMaxPoint;
    }

}
