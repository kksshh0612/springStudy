package seongho.coreprinciple.BeanFind;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.boot.sql.init.dependency.AbstractBeansOfTypeDatabaseInitializerDetector;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import seongho.coreprinciple.discount.DiscountPolicy;
import seongho.coreprinciple.discount.FixDiscountPolicy;
import seongho.coreprinciple.discount.RateDiscountPolicy;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);

    @Configuration
    static class TestConfig{

        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }

    @Test
    @DisplayName("부모 타입으로 조회 시 자식이 둘 이상이면 중복 오류가 발생한다.")
    void findBeanByParentTypeDuplicate(){
//        DiscountPolicy bean = applicationContext.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> applicationContext.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회 시 자식이 둘 이상이면 중복 오류가 발생한다.")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy rateDiscountPolicy = applicationContext.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(DiscountPolicy.class);

        DiscountPolicy fixDiscountPolicy = applicationContext.getBean("fixDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(DiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")           //안좋은 방법
    void findBeanBySubType(){
        RateDiscountPolicy bean = applicationContext.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회")
    void findAllBeansByParentType(){
        Map<String, DiscountPolicy> beansOfType = applicationContext.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회(Object)")       //최상위인 Object 하위 모든 빈. 그니까 스프링 빈에 등록된 모든 빈이 다 나옴
    void findAllBeansByObjectType(){
        Map<String, Object> beansOfType = applicationContext.getBeansOfType(Object.class);
        for(String key : beansOfType.keySet()){
            System.out.println("key = " + key + " / value = " + beansOfType.get(key));
        }
    }

}
