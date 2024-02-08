package HiDemo.core1;

import HiDemo.core1.discount.DiscountPolicy;
import HiDemo.core1.discount.FixDiscountPolicy;
import HiDemo.core1.discount.RateDiscountPolicy;
import HiDemo.core1.member.MemberRepository;
import HiDemo.core1.member.MemberService;
import HiDemo.core1.member.MemberServiceImpl;
import HiDemo.core1.member.MemoryMemberRepository;
import HiDemo.core1.order.OrderService;
import HiDemo.core1.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }
    @Bean
    public MemberRepository memberRepository(){
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
