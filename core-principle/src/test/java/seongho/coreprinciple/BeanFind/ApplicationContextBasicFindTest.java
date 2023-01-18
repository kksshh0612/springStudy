package seongho.coreprinciple.BeanFind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import seongho.coreprinciple.AppConfig;
import seongho.coreprinciple.member.MemberService;
import seongho.coreprinciple.member.MemberServiceImpl;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);    //스프링 컨테이너

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = applicationContext.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        //MemberService 인터페이스로만 조회하는 것이 아니라, 구현 클래스로 조회하는 것. 어차피 리턴이 MemberServiceImpl이기 때문에 상관없긴 한데,
        //역할에 집중한 코드가 아닌, 구현에 집중한 코드이기 때문에 안좋다.
        MemberService memberService = applicationContext.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX(){
        assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean("XXX", MemberService.class));
    }
}
