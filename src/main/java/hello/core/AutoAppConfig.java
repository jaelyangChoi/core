package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration //싱글톤 빈을 보장
@ComponentScan( //@Component가 붙은 클래스를 전부 스캔해 빈으로 등록
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        //기존 설정 정보를 빈으로 등록하지 않기 위해.
)
public class AutoAppConfig {
}
