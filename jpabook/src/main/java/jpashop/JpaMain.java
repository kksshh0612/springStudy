package jpashop;

import jpashop.domain.Item;
import jpashop.domain.Member;
import jpashop.domain.Order;
import jpashop.domain.OrderItem;

import jakarta.persistence.*;
import jpashop.domain.items.Book;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try {
            Book book = new Book();
            book.setName("jpa");
            book.setAuthor("김영한");

            entityManager.persist(book);



            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            entityManager.close();
        }

        entityManagerFactory.close();
    }
}
