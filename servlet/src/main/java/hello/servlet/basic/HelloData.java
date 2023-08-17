package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HelloData {
    private String username;
    private int age;

    // json라이브러리가 get,set으로 값을 꺼내가 -> 하지만 loombok을 사용하면 @Getter @Setter를 써주면 자동으로 들어가 밑에 코드가
    // public String getUsername() {
    //     return username;
    // }
    //
    // public void setUsername(String username) {
    //     this.username = username;
    // }
    //
    // public int getAge() {
    //     return age;
    // }
    //
    // public void setAge(int age) {
    //     this.age = age;
    // }
}
