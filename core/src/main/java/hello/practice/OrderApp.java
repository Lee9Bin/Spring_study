package hello.practice;

import hello.practice.member.Grade;
import hello.practice.member.Member;
import hello.practice.member.MemberService;
import hello.practice.member.MemberServiceImpl;
import hello.practice.order.Order;
import hello.practice.order.OrderService;
import hello.practice.order.OrderServiceImple;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImple();

        Member member = new Member(1L,"memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(member.getMemberId(), "itemA",10000);

        System.out.println("order = " + order);

        System.out.println("order.calculatePrice() = " + order.calculatePrice());
    }
}
