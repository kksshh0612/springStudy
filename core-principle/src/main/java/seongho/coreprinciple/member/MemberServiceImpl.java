package seongho.coreprinciple.member;

public class MemberServiceImpl implements MemberService{        //관례상 인터페이스의 구현체가 하나이면 인터페이스이름+Impl로 네이밍함.

    private final MemberRepository memberRepository = new MemoryMemberRepository();     //실제 할당에서 구현체에 의존하는 문제 (DIP위반)

    //클라이언트의 구현 클래스에서는 멤버 인터페이스만 거쳐서 작동할 수 있음. 다형성 이용
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
