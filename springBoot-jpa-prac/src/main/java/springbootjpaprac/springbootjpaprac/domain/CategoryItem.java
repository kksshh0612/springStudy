package springbootjpaprac.springbootjpaprac.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import springbootjpaprac.springbootjpaprac.domain.items.Item;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class CategoryItem {

    @Id @GeneratedValue
    @Column(name = "category_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public void addCategory(Category category){
        this.category = category;
        category.getCategoryItems().add(this);
    }

    public void addItem(Item item){
        this.item = item;
        item.getCategoryItems().add(this);
    }

}
