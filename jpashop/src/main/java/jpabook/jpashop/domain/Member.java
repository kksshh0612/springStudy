package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;        //내장타입

    @OneToMany(mappedBy = "member")      //하나의 회원이 여러가지 상품을 주문하기 때문
    private List<Order> orders = new ArrayList<>();
}
