package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service        //컴포넌트 스캔에 의해 자동으로 스프링 빈으로 등록됨
@Transactional(readOnly = true)     //읽기 전용으로 DB 최적화
@RequiredArgsConstructor            //final이 있는 필드를 가지고 생성자 만들어줌.(lombok)
//스프링은 생성자가 하나만 있으면 자동으로 @Autowired 주입해줌
public class MemberService {

    private final MemberRepository memberRepository;

//    @Autowired          //생성자 주입
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    //회원가입
    @Transactional          //readOnly true를 기본으로하고, 얘는 명시적으로 true 아니게 해줌
    public Long join(Member member){
        validateDuplicateMember(member);            //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        //중복회원 검증 예외처리
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){         //찾은 회원 리스트가 비어있지 않으면
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //단일 회원 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
