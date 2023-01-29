package seongho.coreprinciple.Singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import seongho.coreprinciple.AppConfig;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService bean1 = applicationContext.getBean(StatefulService.class);
        StatefulService bean2 = applicationContext.getBean(StatefulService.class);

        //쓰레드A. A사용자가 만원 주문
        bean1.order("A", 10000);
        //쓰레드B. A사용자가 만원 주문
        bean2.order("B", 20000);

        int price = bean1.getPrice();

        System.out.println("price = " + price);
        Assertions.assertThat(bean1.getPrice()).isEqualTo(20000);
    }

    @Configuration
    static class TestConfig {

        @Bean
        public StatefulService statefulService() {

            return new StatefulService();
        }
    }
}
