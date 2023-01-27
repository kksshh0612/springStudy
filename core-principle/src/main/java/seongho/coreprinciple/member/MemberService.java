package seongho.coreprinciple.member;

// 회원에게 제공하는 동작에 관한 인터페이스.
public interface MemberService {

    void join(Member member);           //회원가입

    Member findMember(Long memberId);   //멤버 찾기
}
