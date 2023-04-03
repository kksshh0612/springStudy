package helloJpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    //다대일 양방향
    @OneToMany(mappedBy = "team")       //Member에 있는 team 변수와 매핑
    private List<Member> members = new ArrayList<>();

    //일대다
//    @OneToMany
//    @JoinColumn(name = "TEAM_ID")     //이거 안쓰면 조인 테이블 방식을 사용함.
//    private List<Member> members = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
