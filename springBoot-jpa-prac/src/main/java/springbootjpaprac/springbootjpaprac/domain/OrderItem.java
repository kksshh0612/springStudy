package springbootjpaprac.springbootjpaprac.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import springbootjpaprac.springbootjpaprac.domain.items.Item;

@Entity
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int orderPrice;

    private int count;

    //== 연관관계 편의 매서드 ==//
    public void addOrder(Order order){
        this.order = order;
        order.getOrderItems().add(this);
    }
}
