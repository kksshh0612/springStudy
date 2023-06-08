package springbootjpaprac.springbootjpaprac.domain.items;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.aop.target.LazyInitTargetSource;
import springbootjpaprac.springbootjpaprac.domain.Category;
import springbootjpaprac.springbootjpaprac.domain.CategoryItem;

import java.util.ArrayList;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItems = new ArrayList<>();

    private String name;

    private int price;

    private  int stockQuantity;


}
