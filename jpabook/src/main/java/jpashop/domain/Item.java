package jpashop.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn        //기본 DTYPE
public abstract class Item extends BaseEntity{

    @Id @GeneratedValue     //기본값이 auto
    @Column(name = "ITEM_ID")
    private Long id;

    @OneToMany(mappedBy = "id")
    private List<Category> categories = new ArrayList<>();

    private String name;

    private int price;

    private int stockQuantity;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
