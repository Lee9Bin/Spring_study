package hello.servlet.domain.member;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// public junit5부터 없어도 돼
public class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach //테스트가 끝날때마다 초기화 하기 위해서 ->각 테스트가 끝날때마다 실행
    void afterEach() {
        memberRepository.clearStore(); //만약 이게 없으면 테스트 코드가 순서가 보장이 안돼서 오류 날수도 ...
    }

    @Test
    void save() {
        //given -> 이런게 주어졌을때
        Member member1 = new Member("hello", 20);

        //when -> 이런게 실행 됐을때
        Member savemember = memberRepository.save(member1);

        //then -> 결과가 이렇게 돼야해
        Member findmember = memberRepository.findById(member1.getId());
        assertThat(findmember).isEqualTo(savemember);
    }

    @Test
    void findAll() {
        //given -> 이런게 주여졌을때
        Member member1 = new Member("hello", 20);
        Member member2 = new Member("hi", 21);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when -> 이런게 실행 됐을떄
        List<Member> result = memberRepository.findAll();

        //then -> 결과가 이렇게 돼야해
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }
}
