package springbootjpaprac.springbootjpaprac.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springbootjpaprac.springbootjpaprac.domain.Address;
import springbootjpaprac.springbootjpaprac.domain.Order;
import springbootjpaprac.springbootjpaprac.domain.OrderStatus;
import springbootjpaprac.springbootjpaprac.repository.OrderRepository;
import springbootjpaprac.springbootjpaprac.repository.OrderSearch;
import springbootjpaprac.springbootjpaprac.repository.order.simplequery.SimpleOrderQueryDto;
import springbootjpaprac.springbootjpaprac.repository.order.simplequery.SimpleOrderQueryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * xToOne(ManyToOne, OneToOne) 관계에서의 성능 최적화
 * Order
 * Order -> Member
 * Order -> Delivery
 *
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final SimpleOrderQueryRepository simpleOrderQueryRepository;

    @GetMapping("api/v1/simple-orders")     //jackson 라이브러리가 Member와 order를 왔다갔다하면서 json 객체를 무한으로 생성
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByCriteria(new OrderSearch());
        //그런데, 이렇게 하면 엔티티를 직접 노출하는 것이기 때무넹, 이렇게 하면 안됨.
        return all;
    }

    @GetMapping("api/v2/simple-orders")     // N + 1 문제 발생
    public List<SimpleOrderDto> ordersV2(){
        List<Order> all = orderRepository.findAllByCriteria(new OrderSearch());
        List<SimpleOrderDto> result = all.stream()
                .map(order -> new SimpleOrderDto(order))
                .collect(Collectors.toList());

        return result;
    }

    @GetMapping("api/v3/simple-orders")     //페치 조인을 통해 lazy였던 것들을 조인해서 한번에 가져옴으로써 쿼리가 하나 나감 -> N + 1 문제 해결!!!!
    public List<SimpleOrderDto> ordersV3(){
        List<Order> orders = orderRepository.findAllWithMemberDelivery();   //페치 조인 이용
        List<SimpleOrderDto> result = orders.stream()
                .map(order -> new SimpleOrderDto(order))
                .collect(Collectors.toList());

        return result;
    }

    @GetMapping("api/v4/simple-orders")
    public List<SimpleOrderQueryDto> ordersV4(){
        return simpleOrderQueryRepository.findOrderDtos();
    }

    @Data
    static class SimpleOrderDto{
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order){
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
        }
    }
}
