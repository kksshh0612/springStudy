package seongho.coreprinciple.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifecycleTest {

    @Test
    public void lifecycleTest(){
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = applicationContext.getBean(NetworkClient.class);
        applicationContext.close();         //close를 사용하기 위해서는 AnnotationConfigApplicationContext의 부모인 ConfigurableApplicationContext를 사용
    }

    @Configuration
    static class LifeCycleConfig{
//        @Bean(initMethod = "init", destroyMethod = "close")
//        @Bean(initMethod = "init")
        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://어쩌구저쩌구url");
            return networkClient;
        }
    }
}
