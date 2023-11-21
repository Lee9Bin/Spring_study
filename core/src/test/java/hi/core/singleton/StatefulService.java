package hi.core.singleton;

public class StatefulService {
    // private int price; 싱글톤으로 관리하면 공유되는 필드를 사용하면 문제가 생겨 ...

    public int order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        // this.price = price; //이곳이 문제!
        // 따라서 공유되지 않는 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
        return price;
    }

    // public int getPrice(){
    //     return price;
    // }
}
