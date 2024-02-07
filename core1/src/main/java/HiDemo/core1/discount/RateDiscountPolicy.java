package HiDemo.core1.discount;

import HiDemo.core1.member.Grade;
import HiDemo.core1.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{
    private final int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
