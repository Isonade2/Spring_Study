package study.datajpa.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void 회원가입(){
        Member member = new Member("memberA");
        Member savedMember = memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.find(savedMember.getId());

        org.assertj.core.api.Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());

    }

    @Test
    public void basicCRUD(){
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        Member findMember1 = memberJpaRepository.findById(member1.getId()).get();
        Member findMember2 = memberJpaRepository.findById(member2.getId()).get();
        org.assertj.core.api.Assertions.assertThat(findMember1).isEqualTo(member1);
        org.assertj.core.api.Assertions.assertThat(findMember2).isEqualTo(member2);

        List<Member> all = memberJpaRepository.findAll();
        org.assertj.core.api.Assertions.assertThat(all.size()).isEqualTo(2);

        long count = memberJpaRepository.count();
        org.assertj.core.api.Assertions.assertThat(count).isEqualTo(2);

        memberJpaRepository.delete(member1);
        memberJpaRepository.delete(member2);

        long deletedCount = memberJpaRepository.count();
        org.assertj.core.api.Assertions.assertThat(deletedCount).isEqualTo(0);

    }

}