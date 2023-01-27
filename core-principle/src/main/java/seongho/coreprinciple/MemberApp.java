package seongho.coreprinciple;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import seongho.coreprinciple.member.Grade;
import seongho.coreprinciple.member.Member;
import seongho.coreprinciple.member.MemberService;
import seongho.coreprinciple.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {        //psvm 엔터
//        AppConfig appConfig = new AppConfig();      //의존관계 주입을 위한 객체
//        MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);        //스프링 컨테이너
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);     //스프링 빈

        Member member = new Member(1L, "김성호", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
