package seongho.coreprinciple.BeanFind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import seongho.coreprinciple.AppConfig;

public class ApplicationContextInfoTest {

    //스프링 컨테이너. ApplicationContext 인터페이스의 구현체.
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();     //빈 이름
        for(String beanDefinitionName : beanDefinitionNames){
            Object bean = applicationContext.getBean(beanDefinitionName);       //빈 이름으로 빈 객체 찾아서 저장
            System.out.println("name = " + beanDefinitionName + " / Object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();     //빈 이름
        for(String beanDefinitionName : beanDefinitionNames){
            BeanDefinition beanDefinition = applicationContext.getBeanDefinition(beanDefinitionName);

            //ROLE_APPLICATION : 일반적으로 사용자가 정의한 빈
            //ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {       //스프링 내부에서 만든 빈 말고, 애플리케이션에서 만든 빈이면
                Object bean = applicationContext.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " / Object = " + bean);
            }
        }
    }

}
