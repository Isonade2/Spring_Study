package JPA.Hello.domain;


import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public enum joinType {
    Local,Kakao
}
