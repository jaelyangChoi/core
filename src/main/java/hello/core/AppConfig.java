package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepositoy;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @Configuration을 붙이면 바이트코드를 조작하는 CGLIB 기술을 사용해서 싱글톤을 보장한다.
 *  @Bean만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않는다.
    - memberRepository() 처럼 의존관계 주입이 필요해서 메서드를 직접 호출할 때 싱글톤을 보장하지 않는다.
 */
@Configuration  //AppConfig을 상속받은 AppConfig@CGLIB 클래스가 빈으로 등록된다.
public class AppConfig {

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.orderService"); //테스트 용도
        return new MemberServiceImpl(memberRepository()); //@Configuration을 붙이지 않으면 스프링 빈이 아닌 MemoryMemberRepositoy 사용
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository"); //테스트 용도
        return new MemoryMemberRepositoy();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService"); //테스트 용도
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
