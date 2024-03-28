package JPA.Hello.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter @Setter
public class PlantHistory {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Plant plant;

    private Integer temp;
    private Integer humidity;
    private Integer amountWater;
    private Integer illuminance;
}
