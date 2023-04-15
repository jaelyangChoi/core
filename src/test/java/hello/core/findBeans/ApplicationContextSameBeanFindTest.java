package hello.core.findBeans;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import jdk.jfr.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationContextSameBeanFindTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회 시, 같은 타입이 둘 이상 있으면 중복 오류가 발생한다.")
    void findBeansByType() {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("같은 타입의 빈이 둘 이상 있을 경우, 이름을 지정해서 조회")
    void findBeanByNameAndType() {
        ac.getBeansOfType(DiscountPolicy.class)
                .keySet()
                .forEach(name -> System.out.println("name = " + name + ", object = " + ac.getBean(name)));

        DiscountPolicy bean = ac.getBean("discountPolicy2", DiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Configuration
    static class SameBeanConfig {
        @Bean
        DiscountPolicy discountPolicy() {
            return new FixDiscountPolicy();
        }

        @Bean
        DiscountPolicy discountPolicy2() {
            return new RateDiscountPolicy();
        }
    }
}
