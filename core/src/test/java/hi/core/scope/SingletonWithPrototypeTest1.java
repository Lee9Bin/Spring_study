package hi.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class,ClientBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count = clientBean1.logic();
        assertThat(count).isEqualTo(1);

        ClientBean clientBean2= ac.getBean(ClientBean.class);
        int count2 = clientBean1.logic();
        // 프로토타입빈이 새로 만들어져야하는데 뭐지 ????
        assertThat(count2).isEqualTo(1);

    }

    @Scope("singleton")
    static class ClientBean{
        // private final PrototypeBean prototypeBean; // 생성자에서 이미 생성점에서 주입이 돼있어 그래서 같이 쓰다보니 그래

        // @Autowired // 생성자 하나로 안해두 되긴해
        // public ClientBean(PrototypeBean prototypeBean) {
        //     this.prototypeBean = prototypeBean;
        // }


        // ObjectFactory, ObjectProvider를 통해서 하는 방법 -> 과거의 ObjectFactory에서 편의기능이 추가된 ObjectProvider
        // @Autowired
        // private ObjectFactory<PrototypeBean> prototypeBeansProvider;
        // private ObjectProvider<PrototypeBean> prototypeBeansProvider;

        // JSR-330 Provider 통해서
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
        // @Autowired 무식한 방법
        // ApplicationContext applicationContext;
        //
        // public int logic() {
        //     PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
        //     prototypeBean.addCount();
        //     int count = prototypeBean.getCount();
        //         return count;
        //     }
    }

    // @Scope("singleton")
    // static class ClientBean2{
    //     private final PrototypeBean prototypeBean; // 생성자에서 이미 생성점에서 주입이 돼있어 그래서 같이 쓰다보니 그래
    //
    //     @Autowired // 생성자 하나로 안해두 되긴해
    //     public ClientBean2(PrototypeBean prototypeBean) {
    //         this.prototypeBean = prototypeBean;
    //     }
    //
    //     public int logic() {
    //         prototypeBean.addCount();
    //         int count = prototypeBean.getCount();
    //         return count;
    //     }
    // }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count ++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init"+this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

}
