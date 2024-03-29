package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import hello.core.repository.MemberRepository;
import hello.core.repository.MemoryMemberRepository;
import hello.core.service.MemberService;
import hello.core.service.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
//        Appconfig appconfig = new Appconfig();
//        MemberService memberService = appconfig.memberService();
//        OrderService orderService = appconfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Appconfig.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(memberId,"itemA",20000);
        System.out.println("order = " + order);
    }
}
