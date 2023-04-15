package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;

public class OrderServiceImpl implements OrderService{
    private MemberRepository memberRepository = new MemoryMemberRepositoy();
    private DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        //단일 책임의 원칙 준수 : 할인에 대한 건 discountPolicy에게 맡긴다. 변경이 있어도 Order는 변경할 필요가 없다.
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
