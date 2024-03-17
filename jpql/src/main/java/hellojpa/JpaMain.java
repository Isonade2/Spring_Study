package hellojpa;

import hellojpa.jpql.*;
import jakarta.persistence.*;
import org.hibernate.metamodel.internal.MemberResolver;

import java.time.LocalDateTime;
import java.util.List;


public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //code
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team1 = new Team();
            team1.setName("팀A");
            em.persist(team1);

            Team team2 = new Team();
            team2.setName("팀B");
            em.persist(team2);

            Team team3 = new Team();
            team3.setName("팀C");
            em.persist(team3);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.changeTeam(team1);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.changeTeam(team1);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.changeTeam(team2);
            em.persist(member3);

            Member member4 = new Member();
            member4.setUsername("회원4");
            em.persist(member4);

            em.flush();
            em.clear();

            int executeUpdate = em.createQuery("update Member m set m.age = 20 where m.username = '회원1'")
                    .executeUpdate();
            Member member = em.find(Member.class, 1L);
            System.out.println("member.getAge() = " + member.getAge());


            tx.commit();
        } catch (Exception e){
            System.out.println("error!");
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}
