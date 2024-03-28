package JPA.Hello.Repository;


import JPA.Hello.domain.Member;
import JPA.Hello.domain.Plant;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlantRepository {
    private final EntityManager em;

    public void save(Plant plant){
        em.persist(plant);
    }

    public Plant findOne(Long id){
        return em.find(Plant.class,id);
    }

    public List<Plant> findAll(){
        return em.createQuery("select p from Plant p", Plant.class)
                .getResultList();
    }
}
