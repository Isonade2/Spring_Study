package hellojpa;

import hellojpa.domain.Book;
import hellojpa.domain.Item;
import hellojpa.domain.Order;
import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Book book = new Book();
            book.setAuthor("martin");
            book.setIsbn("13213");
            book.setName("히히");
            book.setPrice(1000);
            em.persist(book);


            tx.commit();



        }catch(Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
