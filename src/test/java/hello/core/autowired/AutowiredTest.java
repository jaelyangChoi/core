package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

/**
 * 스프링 빈이 아닌 것은 자동 주입 대상이 되지 않는다.
 * 주입할 스프링 빈이 없어도 동작해야 할 때가 있다.
 * 그런데 @Autowired 만 사용하면 required 옵션의 기본값이 true 로 되어 있어서 자동 주입 대상이 없으면 오류가 발생한다.
 * <p>
 * 자동 주입 대상을 옵션으로 처리하는 방법은 다음과 같다.
 * 1. @Autowired(required=false) : 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨
 * 2. org.springframework.lang.@Nullable : 자동 주입할 대상이 없으면 null이 입력된다.
 * 3. Optional<> : 자동 주입할 대상이 없으면 Optional.empty 가 입력된다.
 */

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        //자동 주입할 대상이 없으면 메소드 호출 자체가 안된다.
        @Autowired(required = false)
        public void setNoBean1(Member member) {
            System.out.println("setNoBean1 = " + member);
        }

        //@Nullable : 빈이 없으면 null로 받는다
        @Autowired
        public void setNoBean2(@Nullable Member member) {
            System.out.println("setNoBean2 = " + member); //null 호출.
        }

        //Optional : null일수도 있고 아닐수도 있는 상태를 감쌈
        @Autowired(required = false)
        public void setNoBean3(Optional<Member> member) {
            System.out.println("setNoBean3 = " + member); //Optional.empty 호출
        }
    }
}
