package seongho.coreprinciple;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages = "seongho.coreprinciple",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)     //AppConfig를 제외함. 기존 코드 버리지 않기 위해서 한거임.
        //원래는 설정정보도 컴포넌트 스캔 대상
)
public class AutoAppConfig {

}
