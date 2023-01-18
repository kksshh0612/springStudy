package seongho.coreprinciple.Singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import seongho.coreprinciple.AppConfig;
import seongho.coreprinciple.member.MemberService;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        assertThat(memberService1).isNotSameAs(memberService2);

        //웹 어플리케이션은 고객의 요청이 매우 많은데, 예를 들어, 초당 5만개의 객체가 생성된다 치면, 매우 비효율적이다.
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonService1 =  SingletonService.getInstance();
        SingletonService singletonService2 =  SingletonService.getInstance();

        assertThat(singletonService1).isSameAs(singletonService2);
        //isSameAs : ==
        //isEqualTo : 자바의 equals와 같음
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        //스프링 컨테이너에서 꺼내서 사용하는 것이기 때문에, 직접 new로 생성하는 것이 아니다.
        MemberService memberService1 = applicationContext.getBean("memberService", MemberService.class);
        MemberService memberService2 = applicationContext.getBean("memberService", MemberService.class);

        assertThat(memberService1).isSameAs(memberService2);
    }
}
