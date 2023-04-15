package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepositoy;

public class RateDiscountPolicy implements DiscountPolicy {
    private double discountRate = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP)
            return (int) (price * (discountRate / 100));
        else return 0;
    }
}
