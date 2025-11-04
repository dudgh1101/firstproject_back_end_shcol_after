package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

@Controller
public class firstController {

    //어노테이션을 이용해서 맵핑해줌
    @GetMapping("/hi")
     public String niceToMeetYou(Model model){
        model.addAttribute("username","ki ");
         return "greetings"; //자동으로 greetings,mustache를 브라우저로 전송해줌
     }

    @GetMapping("/bye")
    public String goodbye(Model model){
        model.addAttribute("username","홍길동");
        return "goodBye";
    }
}

// @ : 어노테이션
// 소스코등에 추가해 사용하는 메타데이터의 일종이다
//메타 데이터는 프로그램에서 처리해야할 데아터가 아니라 컴파일 및 실행과정에서 코드를 어떻게 처리해야
//하는지 알려주는 추가정보를 뜻한다.