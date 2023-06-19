package springbootjpaprac.springbootjpaprac;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootJpaPracApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJpaPracApplication.class, args);
    }

    //@GetMapping("api/v1/simple-orders") 에 관한 것. jackson이 지연로딩 프록시 객체에 대한 처리를 못해서 넣은 코드
    @Bean
    Hibernate5JakartaModule hibernate5JakartaModule(){      //지연로딩에 대해 null을 넣음. 근데 쓰지 말기
        return  new Hibernate5JakartaModule();
    }
}
