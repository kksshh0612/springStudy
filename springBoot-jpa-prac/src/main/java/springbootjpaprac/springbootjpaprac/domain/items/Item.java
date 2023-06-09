package springbootjpaprac.springbootjpaprac.domain.items;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import springbootjpaprac.springbootjpaprac.domain.CategoryItem;
import springbootjpaprac.springbootjpaprac.exception.NotEnoughStockException;

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

    //== 비지니스 로직 ==// 아이템의 재고가 + / - 되는 비지니스 로직은 해당 엔티티 안에 있어야 객체지향적인 것.
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;

        if(restStock < 0){
            throw new NotEnoughStockException("stock lack");
        }
        this.stockQuantity = restStock;
    }
}
