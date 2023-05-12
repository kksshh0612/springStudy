package helloJpa;

import jakarta.persistence.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
//@SequenceGenerator(
//        name = "MEMBER_SEQ_GENERATOR",
//        sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
//        initialValue = 1, allocationSize = 50)       //allocationSize는 기본값이 50
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "name")              //객체는 username, 디비는 name으로 쓰고싶을 때
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)                  //객체지향 모델링
    @JoinColumn(name = "TEAM_ID")       //조인할 컬럼명
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    public Member(){}       //JPA를 사용할 때, 생성자를 만들면, 이렇게 기본 생성자를 꼭 만들어줘야 한다.

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    //연관관계 편의 매서드
    //위치는 두 연관관계 엔티티 중 핵심 비지니스에 편의 매서드를 넣는게 좋다.
    public void setTeam(Team team) {        //관례상 setTeam보다는 changeTeam과 같이 일반적인 setter가 아님을 밝혀주는 것이 좋다.
        this.team = team;
        team.getMembers().add(this);
    }
}
