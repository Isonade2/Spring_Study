package JPA.Hello.Repository;

import JPA.Hello.domain.Member;
import JPA.Hello.domain.Plant;
import JPA.Hello.domain.PlantHistory;
import JPA.Hello.domain.plantType;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PlantRepository plantRepository;
    @Autowired
    private EntityManager em;


    @Test
    @Rollback(value = false)
    public void save() throws Exception{
        Member member1 = getMember("member1", "c1004sos@naver.com");
        Member member2 = getMember("member2","koala@naver.com");

        Long plant1 = getPlant(member1);
        Long plant2 = getPlant(member1);

        getPlant(member2);
        getPlant(member2);

        PlantHistory plantHistory = new PlantHistory();
        plantHistory.setPlant(em.find(Plant.class,plant1));
        em.persist(plantHistory);
    }

    private Long getPlant(Member member1) {
        Plant plant = new Plant();
        plant.setMember(member1);
        plant.setType(plantType.lettuce);
        em.persist(plant);
        return plant.getId();
    }

    private Member getMember(String username, String email) {
        Member member = new Member();
        member.setUsername(username);
        member.setEmail(email);
        member.setPassword("0000");
        em.persist(member);
        return member;
    }


}