package HiDemo.core1;

import HiDemo.core1.member.Grade;
import HiDemo.core1.member.Member;
import HiDemo.core1.member.MemberService;
import HiDemo.core1.member.MemberServiceImpl;
import HiDemo.core1.order.Order;
import HiDemo.core1.order.OrderService;
import HiDemo.core1.order.OrderServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();
        //OrderService orderService = appConfig.orderService();

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);

        long memberId = 1L;
        Member member = new Member(memberId,"memberA",Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);
        System.out.println("order = " + order);

    }

}
