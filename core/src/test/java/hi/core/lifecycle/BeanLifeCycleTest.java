package hi.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        // System.out.println(client.getUrl());
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        @Bean
        // @Bean(initMethod = "init" , destroyMethod = "close")
        // @Bean(initMethod = "init" , destroyMethod = "")
        //                          destroyMethod 는 기본값이 (inferred) (추론)으로 등록되어 있다.
        // 이 추론 기능은 close , shutdown라는 이름의 메서드를 자동으로 호출해준다. 이름 그대로 종료 메서드를 추론해서 호출해준다.
        // 따라서 직접 스프링 빈으로 등록하면 종료 메서드는 따로 적어주지 않아도 잘 동작한다.
        // 추론 기능을 사용하기 싫으면 destroyMethod="" 처럼 빈 공백을 지정하면 된다.
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello.com");
            return networkClient;
        }

    }
}
