package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age ={}", username, age);

        response.getWriter().write("ok");
    }

    /**
     * request.getParameter()를 @RequestParam로 바꾸기
     * 문자를 숫자로 바꿔주지 않아도 되고 request.getParameter()를 쓰지 않아도 된다!
     */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge){
        log.info("username = {}, age ={}", memberName, memberAge);

        return "ok";
    }

    /**
     * 넘어오는 파라미터 이름과 선언한 변수 이름이 같다면 @RequestParam("username") -> @RequestParam 사용 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age){
        log.info("username = {}, age ={}", username, age);

        return "ok";
    }

    /**
     * String, int 등의 **단순 타입**이면 @RequestParam 도 생략 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(
            String username,
            int age){
        log.info("username = {}, age ={}", username, age);

        return "ok";
    }

    /**
     * @RequestParam(required = true), @RequestParam(required = false) 를 활용해 필수 값 지정 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age){
        log.info("username = {}, age ={}", username, age);

        return "ok";
    }

    /**
     * defaultValue = "guest" 기본 값을 줄 수 있다.
     * 따라서 (required = true, defaultValue = "guest") 이런 경우는 의미가 없다!
     * 빈 문자로 들어올 경우에도 defaultValue가 적용 된다.
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") Integer age){
        log.info("username = {}, age ={}", username, age);

        return "ok";
    }

    /**
     * Map 활용
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap){
        log.info("username = {}, age ={}", paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }

}
