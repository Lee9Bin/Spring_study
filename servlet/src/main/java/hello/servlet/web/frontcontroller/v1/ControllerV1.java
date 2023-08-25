package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV1 {

    //여기서 굳이 인터페이스로 선언한 이유는 ? 프론트컨트롤단에서 다형성을 활용하기 위해서!!
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
