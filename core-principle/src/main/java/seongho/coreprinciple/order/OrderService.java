package seongho.coreprinciple.order;

import seongho.coreprinciple.order.Order;

public interface OrderService {

    Order createOrder(Long memberId, String itemName, int itemPrice);
}
