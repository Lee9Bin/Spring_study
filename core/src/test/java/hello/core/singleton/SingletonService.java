package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();
    private SingletonService(){
    // 생성자를 private로 막아서 밖에서 생성 못하게
    }
    public static SingletonService getInstance(){
        return instance;
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

    public static void main(String[] args) {

    }
}
