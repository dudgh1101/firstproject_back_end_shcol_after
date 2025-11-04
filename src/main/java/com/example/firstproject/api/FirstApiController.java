package com.example.firstproject.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //REST API 용 컨트롤러 -> json반환
public class FirstApiController {

    @GetMapping("/api/hello")
    public String hello(){
        return "hello world";//키벨류가 없기애 헬로ㅓ 월드만 출력(json형태임)
    }

}
