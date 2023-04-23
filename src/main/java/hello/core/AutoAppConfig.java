package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration //싱글톤 빈을 보장
@ComponentScan( //@Component가 붙은 클래스를 전부 스캔해 빈으로 등록
        //탐색 대상 지정
//        basePackages = {"hello.core"}, //default는 @ComponentScan이 붙은 설정 정보 클래스의 패키지.
        //기존 설정 정보를 빈으로 등록하지 않기 위해.
        excludeFilters= @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
