package seongho.coreprinciple.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository{        //MemberRepository 인터페이스의 구현 클래스임

    private static Map<Long, Member> store = new HashMap<>();           //Long과 Member형 객체를 값으로 하는 Map

    //구현 클래스는 인터페이스의 모든 함수를 오버라이딩 해야 함.
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
