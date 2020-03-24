package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {

//    @Autowired MemberRepository memberRepository;
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    // 이전에는 직접 유저를 생성하면서 테스트해봤던 것들을 단순히 이렇게 멤버를 직접 생성해보는 상황을 코드로 재현함으로써
//    // 이전보다 훨씬 편리하고 빠르게 테스트를 진행할 수 있게됨
//    public void testMember() throws Exception {
//        //given
//        Member member = new Member();
//        member.setUsername("sehyun");
//
//        //when
//        Long saveId = memberRepository.save(member);
//        Member findMember = memberRepository.find(saveId);
//
//        //then
//        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
//    }
}