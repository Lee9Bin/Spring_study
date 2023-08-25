package hello.servlet.web.frontcontroller.V2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.V2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.V2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.V2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerV2Map = new HashMap<>();

    public FrontControllerServletV2() {
        controllerV2Map.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerV2Map.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerV2Map.put("/front-controller/v2/members", new MemberListControllerV2());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");

        // 요청이 들어오는 경로를 가져와
        String requestURI = request.getRequestURI();

        ControllerV2 controllerV2 = controllerV2Map.get(requestURI);
        if (controllerV2 == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 프론트 컨틀롤러가 url매핑 정보를 저장하고 해당 요청에 맞는 서블릿을 호출한뒤
        // 해당 컨트롤러는 뷰에 대한 이름만 넣어주고 반환해주면 된다. -> 이렇게 하면서 세부 컨트롤러의 중복된 코드를 줄일 수 있다.
        MyView view = controllerV2.process(request, response);
        view.render(request, response);
    }
}
