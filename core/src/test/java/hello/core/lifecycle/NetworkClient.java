package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct; //javax로 시작하면 자바에서 공식적으로 지원한다.
                                       // 스프링에 종속적인 기술이 아니라 JSR-250 라는 자바 표준이다. 따라서 스프링이 아닌 다른 컨테이너에서도 동작한다.
import javax.annotation.PreDestroy;

public class NetworkClient{
    private String url;

    // 생성자 부분을 보면 url 정보 없이 connect가 호출되는 것을 확인할 수 있다.
    // 당연한 이야기이지만 객체를 생성하는 단계에는 url이 없고, 객체를 생성한 다음에 외부에서 수정자 주 입을 통해서 setUrl() 이 호출되어야 url이 존재하게 된다.
    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        // connect();
        // call("초기화 연결 메세지");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스를 시작할때 호출
    public void connect(){
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call = " + url + " message = " + message);

    }

    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("close = " + url);
    }


    // InitializingBean 인터페이스를 활용해 의존관계 주입이 끝났음을 알려줘
    // @Override
    // public void afterPropertiesSet() throws Exception {
    //     System.out.println("NetworkClient.afterPropertiesSet");
    //     connect();
    //     call("초기화 연결 메세지");
    // }
    //
    // //DisposableBean 인터페이스를 활용해 빈이 종료될때를 알려줘
    // @Override
    // public void destroy() throws Exception {
    //     System.out.println("NetworkClient.destroy");
    //     disconnect();
    // }
    // public class NetworkClient implements InitializingBean, DisposableBean
    //     초기화, 소멸 인터페이스 단점
    // 이 인터페이스는 스프링 전용 인터페이스다. 해당 코드가 스프링 전용 인터페이스에 의존한다. 초기화, 소멸 메서드의 이름을 변경할 수 없다.
    // 내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없다.



    // @Bean(initMethod = "init" , destroyMethod = "close") ->메서드를 만들고 빈에서 설정정보에 지정을 해서 사용 간으
    // 메서드 이름을 자유롭게 줄 수 있다.
    // 스프링 빈이 스프링 코드에 의존하지 않는다.
    // 코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드 를 적용할 수 있다.
    // public void init() {
    //     System.out.println("NetworkClient.init");
    //     connect();
    //     call("초기화 연결 메세지");
    // }
    //
    // public void close(){
    //     System.out.println("NetworkClient.close");
    //     disconnect();
    // }


    // 애노테이션을 활용하는 방법 결론은 -> 이 방법을 사용
    // 유일한 단점 외부 라이브러리에는 적용하지 못한다는 것이다. 외부 라이브러리를 초기화, 종료 해야 하면 @Bean의 기능을 사용하자.
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close(){
        System.out.println("NetworkClient.close");
        disconnect();
    }

}
