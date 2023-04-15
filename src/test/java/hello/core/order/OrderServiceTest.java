package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    private MemberService memberService = new MemberServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    private FixDiscountPolicy discountPolicy = new FixDiscountPolicy();
    @Test
    void order() {
        //given
        Long memberId = 1L;
        Member member = new Member(memberId, "최재량", Grade.VIP);
        memberService.join(member);

        //when
        Order order = orderService.createOrder(memberId, "LG그램뷰플러스2", 330000);

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(discountPolicy.getDiscountFixAmount());
    }
}
