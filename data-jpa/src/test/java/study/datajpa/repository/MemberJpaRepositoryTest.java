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

        @Test
        public void findByUsernameAndAgeGreaterThen(){
            Member member1 = new Member("AAA", 10);
            Member member2 = new Member("AAA", 20);
            memberJpaRepository.save(member1);
            memberJpaRepository.save(member2);

            List<Member> result = memberJpaRepository.findByUsernameAndAgeGreaterThen("AAA", 15);

            for (Member member : result) {
                System.out.println("member = " + member.getAge());
            }
        }
        @Test
        public void testNamedQuery(){
            Member m1 = new Member("AAA", 10);
            Member m2 = new Member("BBB", 20);
            memberJpaRepository.save(m1);
            memberJpaRepository.save(m2);

            List<Member> aaa = memberJpaRepository.findByUsername("AAA");
            Member findMember = aaa.get(0);
            org.assertj.core.api.Assertions.assertThat(findMember).isEqualTo(m1);
        }
        @Test
        public void paging() {
            memberJpaRepository.save(new Member("member1",10));
            memberJpaRepository.save(new Member("member2",10));
            memberJpaRepository.save(new Member("member3",10));
            memberJpaRepository.save(new Member("member4",10));
            memberJpaRepository.save(new Member("member5",10));

            int age = 10;
            int offset = 0;
            int limit = 3;
            List<Member> members = memberJpaRepository.findByPage(age, offset, limit);
            long totalCount = memberJpaRepository.totalCount(age);

            org.assertj.core.api.Assertions.assertThat(members.size()).isEqualTo(3);
            org.assertj.core.api.Assertions.assertThat(totalCount).isEqualTo(5);
        }
}