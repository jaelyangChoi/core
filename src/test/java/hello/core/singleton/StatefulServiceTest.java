package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        //ThreadA : A사용자 10000원 주문
        statefulService1.order("userA", 10000);
        //ThreadB : B사용자 20000원 주문
        statefulService1.order("userB", 20000);

        //ThreadA : A사용자가 주문 금액 조회
        System.out.println("userA의 price = " + statefulService1.getPrice());
        assertThat(statefulService1.getPrice()).isNotEqualTo(10000);
    }

    @Test
    void statelessServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatelessService statelessService1 = ac.getBean("statelessService", StatelessService.class);
        StatelessService statelessService2 = ac.getBean("statelessService", StatelessService.class);

        //ThreadA : A사용자 10000원 주문
        int userAPrice = statelessService1.order("userA", 10000);
        //ThreadB : B사용자 20000원 주문
        int userBPrice = statelessService1.order("userB", 20000);

        //ThreadA : A사용자가 주문 금액 조회
        System.out.println("userA의 price = " + userAPrice);
        assertThat(userAPrice).isEqualTo(10000);
    }

    @Configuration
    static class TestConfig { //static이어야 AnnotationConfigApplicationContext에서 TestConfig.class에 접근 가능.
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
        @Bean
        public StatelessService statelessService() {
            return new StatelessService();
        }
    }
}