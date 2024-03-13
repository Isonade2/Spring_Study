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
            Address address = new Address("city", "street","zipcode");

            Member member = new Member();
            member.setHomeAddress(new Address("city1","street","10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new Address("old1","street","10000"));
            member.getAddressHistory().add(new Address("old2","street","10000"));

            em.persist(member);

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
