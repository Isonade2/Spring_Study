package HiDemo.core1.order;

import HiDemo.core1.discount.DiscountPolicy;
import HiDemo.core1.discount.FixDiscountPolicy;
import HiDemo.core1.discount.RateDiscountPolicy;
import HiDemo.core1.member.Member;
import HiDemo.core1.member.MemberRepository;
import HiDemo.core1.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;



    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId,itemName,itemPrice,discountPrice);

    }
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
