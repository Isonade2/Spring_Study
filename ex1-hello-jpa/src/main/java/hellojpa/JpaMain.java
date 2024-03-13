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

            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);
            em.persist(child1);
            em.persist(child2);
            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());



            em.remove(findParent);

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
