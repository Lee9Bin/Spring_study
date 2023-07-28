package hello.practice.member;

import java.util.HashMap;
import java.util.Map;

public class MemberMemoryRepositoryImpl implements MemberRepository {
    private static Map<Long, Member> store =new HashMap<>();
    @Override

    public void save(Member member) {
        store.put(member.getMemberId() , member);
    }

    @Override
    public Member findBy(Long memberId) {
        return store.get(memberId);
    }
}
