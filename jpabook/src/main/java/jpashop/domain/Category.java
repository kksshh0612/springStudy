package jpashop.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Long id;

    @OneToMany(mappedBy = "id")
    List<Item> items = new ArrayList<>();

    private String name;

    //셀프로 양방향 매핑
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Category parent;        //상위 카테고리

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();       //하위 카테고리


}
