package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**Q. 설정 클래스에 @ComponentScan을 붙이고, Springboot를 실행하면? @SpringbootApplication 내부에도 @ComponentScan이 있는데???
 * A. @ComponentScan이 여러 개일 경우 설정을 병합해서 실행하기 때문에 컴포넌트 스캔은 한 번만 실행된다.
 *    이와는 별개로, 컴포넌트 스캔을 통한 빈 등록과 @Bean을 통한 수동 등록을 같이 할 때는 빈 중복 등록으로 충돌 오류가 발생한다.
 */
//@ComponentScan이 포함되어 있어 스프링 부트 애플리케이션일 경우 따로 @ComponentScan을 붙일 필요가 없다.
@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
