package springbootjpaprac.springbootjpaprac.repository.order.simplequery;

import lombok.Data;
import springbootjpaprac.springbootjpaprac.domain.Address;
import springbootjpaprac.springbootjpaprac.domain.Order;
import springbootjpaprac.springbootjpaprac.domain.OrderStatus;

import java.time.LocalDateTime;

@Data
public class SimpleOrderQueryDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderQueryDto(Order order){
        orderId = order.getId();
        name = order.getMember().getName();
        orderDate = order.getOrderDate();
        orderStatus = order.getStatus();
        address = order.getDelivery().getAddress();
    }
}
