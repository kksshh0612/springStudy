package springbootjpaprac.springbootjpaprac.repository;

import lombok.Getter;
import lombok.Setter;
import springbootjpaprac.springbootjpaprac.domain.OrderStatus;

@Getter @Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;
}
