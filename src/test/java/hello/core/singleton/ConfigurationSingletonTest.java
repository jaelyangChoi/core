package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        //모두 같은 인스턴스를 참고하고 있다.
        System.out.println("memberService -> memberRepository = " + memberService.getMemberRepository());
        System.out.println("orderService -> memberRepository = " + orderService.getMemberRepository());
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        //AnnotationConfigApplicationContext 에 파라미터로 넘긴 값은 스프링 빈으로 등록된다
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //부모 타입으로 조회시 자식 타입도 조회되므로 AppConfig@CGLIB 클래스가 리턴되는 것이다
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
        //출력: bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$554e4285
    }
    /**스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서
     * AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록.
     * AppConfig@CGLIB 예상 코드
        @Bean
        public MemberRepository memberRepository() {
            if (memoryMemberRepository가 이미 스프링 컨테이너에 등록되어 있으면?) {
                return 스프링 컨테이너에서 찾아서 반환;
            } else { //스프링 컨테이너에 없으면
                기존 로직을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록
                return 반환 }
        }
     */
}