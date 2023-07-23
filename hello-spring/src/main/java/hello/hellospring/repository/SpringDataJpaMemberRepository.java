package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{
    // findByName() , findByEmail() 처럼 메서드 이름 만으로 조회 기능 제공
    @Override
    Optional<Member> findByName(String name);
}
