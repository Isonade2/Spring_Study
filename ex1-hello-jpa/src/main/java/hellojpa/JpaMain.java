package hellojpa;

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
            Member member1 = new Member();
            member1.setName("kim");
            em.persist(member1);

            em.flush();
            em.clear();


            List<Member> result = em.createQuery("select m from Member m where m.name like '%kim'", Member.class)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member = " + member);
            }




            em.flush();
            em.clear();

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

    private static void printMember(Member member) {
        System.out.println("member.getName() = " + member.getName());
    }

    private static void printMemberAndTeam(Member member) {
        String name = member.getName();
        System.out.println("name = " + name);
        Team team = member.getTeam();
        System.out.println("team.getName() = " + team.getName());
    }
}
