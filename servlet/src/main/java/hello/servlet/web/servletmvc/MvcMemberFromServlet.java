package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//서블릿은 컨트롤러 역할
@WebServlet(name = "mvcMemberFromServlet",urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFromServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        //컨트롤러에서 뷰로 이동할때 사용하는 거
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        //다른 서블릿이나 jsp로 이동할 수 있는 기능 이다. 서버 내부에서 다시 호출이 일어난다.
        dispatcher.forward(request, response);
    }
}
