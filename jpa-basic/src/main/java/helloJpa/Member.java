package helloJpa;

import javax.persistence.*;
import java.util.Date;

@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
        initialValue = 1, allocationSize = 50)       //allocationSize는 기본값이 50
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name = "name")              //객체는 username, 디비는 name으로 쓰고싶을 때
    private String username;

    private Integer age;                //그냥 쓰면 디비에 Integer랑 적절한 숫자 타입이 만들어짐

    @Enumerated(EnumType.STRING)        //Enum타입을 쓸 수 있다.
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)   //날짜 타입을 만들 수 있다. Date, Time, Timestamp 이렇게 세개가 enum으로 있음
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob                                //큰 컨텐츠를 넣을 때
    private String description;

    public Member(){}       //JPA를 사용할 때, 생성자를 만들면, 이렇게 기본 생성자를 꼭 만들어줘야 한다.

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
