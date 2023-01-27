package seongho.coreprinciple.order;

import seongho.coreprinciple.order.Order;

// 주문 관련 기능을 제공하는 인터페이스.
public interface OrderService {

    Order createOrder(Long memberId, String itemName, int itemPrice);
}
