package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean{

        @Autowired(required = false) //@Autowired(required=false) : 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨
        public void setNoBead1(Member member1){
            System.out.println("member1 = " + member1);
        }

        @Autowired //org.springframework.lang.@Nullable : 자동 주입할 대상이 없으면 null이 입력된다.
        public void setNoBead2(@Nullable Member member2){
            System.out.println("member2 = " + member2);
        }

        @Autowired //Optional<> : 자동 주입할 대상이 없으면 Optional.empty 가 입력된다.
        public void setNoBead3(Optional<Member> member3){
            System.out.println("member3 = " + member3);
        }



    }
}
