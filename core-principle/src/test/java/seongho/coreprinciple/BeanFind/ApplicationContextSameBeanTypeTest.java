package seongho.coreprinciple.BeanFind;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import seongho.coreprinciple.AppConfig;
import seongho.coreprinciple.member.MemberRepository;
import seongho.coreprinciple.member.MemoryMemberRepository;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextSameBeanTypeTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Configuration
    static class SameBeanConfig{

        //두 빈의 인스턴스 타입이 같음.
        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }

    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상이면 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate(){
//        applicationContext.getBean(MemberRepository.class);     //NoUniqueBeanDefinitionException 터짐
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> applicationContext.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상이면 빈 이름을 지정하면 된다.")
    void findBeanByName(){
        MemberRepository memberRepository = applicationContext.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType(){
        Map<String, MemberRepository> beansOfType = applicationContext.getBeansOfType(MemberRepository.class);
        for(String key : beansOfType.keySet()){
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

}
