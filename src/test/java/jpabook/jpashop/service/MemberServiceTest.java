package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional // for roll back
class MemberServiceTest {
    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;

    // 회원 가입 test
    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("hwang");

        //when
        Long joinedId = memberService.join(member);
        
        //then
        assertEquals(member, memberService.findOne(joinedId));
    
    }
    // 중복 회원 예외 test
    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("hwang");
        Member member2 = new Member();
        member2.setName("hwang");

        //when
        memberService.join(member1);
        try{
            memberService.join(member2);
        }catch (IllegalStateException e){
            return;
        }

        //then
        fail("여기에 도달하면 안됨.");
    }
}