package jpashop;

import jpashop.domain.Item;
import jpashop.domain.Member;
import jpashop.domain.Order;
import jpashop.domain.OrderItem;

import javax.persistence.*;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member();
            member1.setName("member1");

            Item item = new Item();
            item.setName("item1");
            item.setPrice(10000);

            Order order = new Order();
            order.addMember(member1);

            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.addOrder(order);

            entityManager.persist(member1);
            entityManager.persist(item);
            entityManager.persist(order);
            entityManager.persist(orderItem);



            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            entityManager.close();
        }

        entityManagerFactory.close();
    }
}
