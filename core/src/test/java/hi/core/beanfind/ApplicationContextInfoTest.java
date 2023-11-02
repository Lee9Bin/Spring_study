package hi.core.beanfind;

import hi.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test //내가 등록한 빈 뿐만 아니라 스프링 내부의 빈도 출력이 된다 ... 내가 등록한 빈만 보고 싶은데? findApplicationBean()로!
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        //빈 정의된 이름을 배열에 뽑아온다.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        //iter을 하고 탭을 하면 향상된 포문을 뽑아준다.
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("beanDefinitionName = " + beanDefinitionName + " object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        //빈 정의된 이름을 배열에 뽑아온다.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        //iter을 하고 탭을 하면 향상된 포문을 뽑아준다.
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("beanDefinitionName = " + beanDefinitionName + " object = " + bean);
            }
        }
    }
}
