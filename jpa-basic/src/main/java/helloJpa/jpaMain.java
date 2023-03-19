package helloJpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        //디비에 데이터를 저장하거나 이런 코드들이 이 사이에 들어가는 것.
        Member member = new Member();
        member.setId(1L);
        member.setName("memberA");
        em.persist(member);

        transaction.commit();

        em.close();

        emf.close();
    }
}
