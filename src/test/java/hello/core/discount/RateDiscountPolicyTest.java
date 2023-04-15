package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다")
    void vip_o() {
        //given
        Long memberId = 1L;
        Member member = new Member(memberId, "최재량", Grade.VIP);
        int itemPrice = 10000;

        //when
        int discount = discountPolicy.discount(member, itemPrice);
        //then
        Assertions.assertThat(discount).isEqualTo((int)(itemPrice*0.1));
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
    void vip_x() {
        //given
        Long memberId = 1L;
        Member member = new Member(memberId, "최재량", Grade.BASIC);
        int itemPrice = 10000;

        //when
        int discount = discountPolicy.discount(member, itemPrice);
        //then
        Assertions.assertThat(discount).isEqualTo(0);
    }
}