package org.springboot.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springboot.api.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    private final Logger LOGGER = LoggerFactory.getLogger(GetController.class);
    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String getHello() {
        LOGGER.info("getHello() 메소드 호출");
        return "Hello, World!";
    }

    @GetMapping(value="name")
    public String getName(){
        return "Flature";
    }

    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable){
        return variable;
    }

    @GetMapping
    public String getVariable2(@PathVariable("variable") String var){
        return var;
    }

    @GetMapping(value = "/request1")
    public String getRequestParam1(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String organization) {
        return "name: " + name + ", email: " + email + ", organization: " + organization;
    }

    //어떤 값이 들어올지 모르는 경우
    @GetMapping(value = "/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param){
        StringBuilder sb = new StringBuilder();

        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n" );
        });
        return sb.toString();
    }

    @GetMapping(value="/request3")
    public String getRequestParam3(MemberDto memberDto) {
        // return memberDto.getName() + ", " + memberDto.getEmail() + ", " + memberDto.getOrganization();
        return memberDto.toString();
    }
}