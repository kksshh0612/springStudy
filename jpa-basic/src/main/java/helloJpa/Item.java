package helloJpa;


import jakarta.persistence.*;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED)             //joined strategy
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)       //single table strategy
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)      //table per class strategy    해당 클래스를 추상클래스로 해야됨.
@DiscriminatorColumn            //디폴트가 DTYPE
public class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
