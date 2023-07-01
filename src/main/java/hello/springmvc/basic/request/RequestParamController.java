package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class RequestParamController {
    //    @ResponseBody : View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력 RestController와 같음
    @ResponseBody
    @RequestMapping("/request-param-v1")
    public String requestParamV1(
            @RequestParam("username") String username,
            @RequestParam("age") int age
    ) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam String username,
            @RequestParam int age
    ) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(String username, int age) { //parameter와 변수명 같을 경우 생략 가능
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,//required =false 정보 안와도 됨
            @RequestParam(required = false) Integer age) { //required = true  정보 무조건 와야함 int에는 null이 들어갈 수 없다. 기본값이 true다.
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,//빈문자의 경우 default값으로 설정된다.
            @RequestParam(required = false, defaultValue = "-1") Integer age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }
/*    @RequestParam Map
    Map(key=value)
    @RequestParam MultiValueMap
    MultiValueMap(key=[value1, value2, ...] ex) (key=userIds, value=[id1, id2])
    파라미터의 값이 1개가 확실하다면 Map 을 사용해도 되지만, 그렇지 않다면 MultiValueMap 을 사용하자*/

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username = {] , age = {}", helloData.getUsername(), helloData.getUsername());
        log.info("helloData = {]", helloData);
        return "ok";
    }
/*    스프링MVC는 @ModelAttribute 가 있으면 다음을 실행한다.
    HelloData 객체를 생성한다.
    요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다. 그리고 해당 프로퍼티의 setter를
    호출해서 파라미터의 값을 입력(바인딩) 한다.
    예) 파라미터 이름이 username 이면 setUsername() 메서드를 찾아서 호출하면서 값을 입력한다.*/

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) { //ModelAttribute는 생략 가능하다.
        log.info("username = {] , age = {}", helloData.getUsername(), helloData.getUsername());
        log.info("helloData = {]", helloData);
        return "ok";
    }
}
