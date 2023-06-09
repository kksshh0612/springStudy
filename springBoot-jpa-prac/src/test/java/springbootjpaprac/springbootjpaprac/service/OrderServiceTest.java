package springbootjpaprac.springbootjpaprac.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springbootjpaprac.springbootjpaprac.domain.Address;
import springbootjpaprac.springbootjpaprac.domain.Member;
import springbootjpaprac.springbootjpaprac.domain.Order;
import springbootjpaprac.springbootjpaprac.domain.OrderStatus;
import springbootjpaprac.springbootjpaprac.domain.items.Book;
import springbootjpaprac.springbootjpaprac.domain.items.Item;
import springbootjpaprac.springbootjpaprac.exception.NotEnoughStockException;
import springbootjpaprac.springbootjpaprac.repository.OrderRepository;

@SpringBootTest
@Transactional
public class OrderServiceTest {

    @PersistenceContext EntityManager entityManager;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
        //given
        Member member = createMember();
        Item book = createItem();

        //when
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        Assertions.assertEquals(OrderStatus.ORDER, getOrder.getStatus());       //주문의 상태
        Assertions.assertEquals(1, getOrder.getOrderItems().size());    //주문한 상품의 종류
        Assertions.assertEquals(orderCount * 10000, getOrder.getTotalPrice());  //주문상 상품의 금액
        Assertions.assertEquals(8, book.getStockQuantity());
    }

    @Test
    public void 주문취소() throws Exception{
        //given
        Member member = createMember();
        Item book = createItem();

        int orderCount = 5;
        int prevStockQuantity = book.getStockQuantity();
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        Assertions.assertEquals(prevStockQuantity, book.getStockQuantity());
        Assertions.assertEquals(OrderStatus.CANCEL, getOrder.getStatus());
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception{
        Member member = createMember();
        Item book = createItem();

        int orderCount = 12;
//        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        NotEnoughStockException thrown = Assertions.assertThrows(NotEnoughStockException.class, ()->
                orderService.order(member.getId(), book.getId(), orderCount));
    }

    private Member createMember(){
        Member member = new Member();
        member.setName("member1");
        member.setAddress(new Address("청주", "1순환로", "28643"));
        entityManager.persist(member);

        return member;
    }

    private Item createItem(){
        Item book = new Book();
        book.setName("오브젝트");
        book.setPrice(10000);
        book.setStockQuantity(10);
        entityManager.persist(book);

        return book;
    }
}
