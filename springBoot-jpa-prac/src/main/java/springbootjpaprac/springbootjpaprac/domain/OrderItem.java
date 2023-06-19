package springbootjpaprac.springbootjpaprac.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import springbootjpaprac.springbootjpaprac.domain.items.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int orderPrice;

    private int count;

    //== 생성 매서드 ==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);        //해당 아이템의 재고 수량만큼 삭제

        return orderItem;
    }

    ///== 비지니스 로직 ==//
    /** 주문 취소 */
    public void cancel(){
        getItem().addStock(count);
    }

    /** 주문 상품 전체 가격 조회 */
    public int getTotalPrice(){
        return getOrderPrice() * getCount();
    }
}
