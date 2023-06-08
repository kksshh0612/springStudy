package springbootjpaprac.springbootjpaprac.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter         //값타입은 변경불가능하게 설계해야 한다.
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address(){}           //JPA 스펙상 만든 것. new로 함부로 생성하면 안됨.

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
