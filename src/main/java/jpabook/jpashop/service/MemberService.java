package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 중복 체크
    private void validateDuplicateMember(Member member) {
        List<Member> sameName = memberRepository.findByName(member.getName());
        if(!sameName.isEmpty()){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    /***
     * 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long targetId){
        return memberRepository.findOne(targetId);
    }
}
