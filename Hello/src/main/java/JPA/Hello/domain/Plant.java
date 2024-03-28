package JPA.Hello.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Plant {
    @Id @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private plantType type;

    @ManyToOne
    private Member member;
}
