package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    public void 회원가입() throws Exception {
        Member member = new Member();
        member.setName("kim");

        memberService.join(member);
        Member findMember = memberRepository.findOne(member.getId());

        Assertions.assertThat(findMember).isEqualTo(member);
        System.out.println("findMember = " + findMember);
        System.out.println("member = " + member);
    }


    @Test
    public void 중복회원예외() throws Exception {
        Member member1 = new Member();
        member1.setName("kim");


        Member member2 = new Member();
        member2.setName("kim");

        memberService.join(member1);
        try{
            memberService.join(member2);
        } catch (IllegalStateException e){
            e.printStackTrace();
            return;
        }




        fail("예외가 발생해야한다.");
    }

}