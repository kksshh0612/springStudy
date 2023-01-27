package seongho.coreprinciple.member;

//회원 정보를 저장할 것은 아직 정해지지 않았음. 메모리에 저장할 수도 있고, DB에 저장할 수도 있음.
public interface MemberRepository {

    void save(Member member);           //멤버 객체를 저장하는 함수

    Member findById(Long memberId);     //아이디를 이용해 멤버를 찾는 함수. 멤버 객체를 리턴함.
}
