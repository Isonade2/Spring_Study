package hellojpa;

import jakarta.persistence.*;
import org.hibernate.metamodel.internal.MemberResolver;

import java.util.List;


public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //code
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.setTeam(team);
            em.persist(member);

            Member member2 = new Member();
            member2.setName("member2");
            member2.setTeam(team);
            em.persist(member2);



            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMember = " + findMember);
            Team team1 = findMember.getTeam();
            System.out.println("team1 = " + team1);
            System.out.println("team1.getMembers() = " + team1.getMembers());


            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
