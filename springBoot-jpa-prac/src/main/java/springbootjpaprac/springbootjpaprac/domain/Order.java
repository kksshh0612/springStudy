package springbootjpaprac.springbootjpaprac.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "orders")     //sql에서 order by 때문에 order는 예약어임 따라서 다른 테이블명 지정
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    Delivery delivery;

    LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    OrderStatus status;

    //== 연관관계 편의 매서드 ==//
    public void addMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
