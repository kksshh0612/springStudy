package seongho.coreprinciple.Singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import seongho.coreprinciple.AppConfig;
import seongho.coreprinciple.member.MemberRepository;
import seongho.coreprinciple.member.MemberServiceImpl;
import seongho.coreprinciple.order.OrderServiceImpl;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = applicationContext.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = applicationContext.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = applicationContext.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberRepository1" + memberRepository1);        //memberService에서 참조하는 빈
        System.out.println("memberRepository2" + memberRepository2);        //orderService에서 참조하는 빈
        System.out.println("memberRepository" + memberRepository);

        //세 개의 빈이 모두 같음.
        assertThat(memberRepository1).isEqualTo(memberRepository);
        assertThat(memberRepository2).isEqualTo(memberRepository);

    }

    @Test
    void configurationDeep(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        //AppConfig도 스프링 빈으로 등록된다.
        AppConfig bean = applicationContext.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
    }
}
