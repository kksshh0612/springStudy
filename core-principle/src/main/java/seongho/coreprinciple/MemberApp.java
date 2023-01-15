package seongho.coreprinciple;

import seongho.coreprinciple.member.Grade;
import seongho.coreprinciple.member.Member;
import seongho.coreprinciple.member.MemberService;
import seongho.coreprinciple.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {        //psvm 엔터
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "김성호", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
