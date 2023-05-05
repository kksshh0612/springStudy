package jpashop.domain;

import jakarta.persistence.*;

@Entity
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")        //주인(단방향 매핑)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")       //주인
    private Order order;

    private int orderPrice;

    private int count;

    public Long getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void addOrder(Order order) {             //연관관계 편의 매서드
        this.order = order;
        order.getOrderItems().add(this);
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
