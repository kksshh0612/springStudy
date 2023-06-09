package springbootjpaprac.springbootjpaprac.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springbootjpaprac.springbootjpaprac.domain.*;
import springbootjpaprac.springbootjpaprac.domain.items.Item;
import springbootjpaprac.springbootjpaprac.repository.ItemRepository;
import springbootjpaprac.springbootjpaprac.repository.MemberRepository;
import springbootjpaprac.springbootjpaprac.repository.OrderRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        //관련 엔티티 조회
        Member member = memberRepository.find(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송 정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.배송준비중);

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장장
        orderRepository.save(order);
        //cascade 옵션 덕분에 관련된 delivery, orderItem도 persist 됨. 이게 가능한 이유는 delivery와 orderItem의 라이프사이클이 order와 같기 때문이다.
        //만약에 다른 비지니스 로직에서 delivery나 orderItem을 사용한다면 cascade.All을 사용하면 안된다.

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    /**
     * 주문 검색
     */
//    public List<Order> findOrders


}
