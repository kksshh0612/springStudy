package seongho.coreprinciple.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{        //관례상 인터페이스의 구현체가 하나이면 인터페이스이름+Impl로 네이밍함.

    //////////////////싱글톤 패턴으로 바꿔보기//////////////////

//    private static final MemberService memberServiceImpl = new MemberServiceImpl()
    //아, 여기서 의존관계 주입을 해주려면 DIP를 위반하는구나. 애초에 싱글톤 패턴을 하면 DIP를 위반하고 OCP 위반까지 하게된다.

    /////////////////////////////////////////////////////////


//    private final MemberRepository memberRepository = new MemoryMemberRepository();     //실제 할당에서 구현체에 의존하는 문제 (DIP위반)
    private MemberRepository memberRepository;      //DIP를 지켜줌. 멤버 정보를 담을 객체

    @Autowired
    public  MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    //클라이언트의 구현 클래스에서는 멤버 인터페이스만 거쳐서 작동할 수 있음. 다형성 이용
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //싱글톤 테스트용
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
