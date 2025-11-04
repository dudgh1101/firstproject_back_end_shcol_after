package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

@Controller
public class SecondController {

    @GetMapping("/random-quote")
    public String random(Model model){
        String[] randomList =
                {"행복은 습관인다. 그것을 몸에 지녀라 -하버드-",
                        "고개 숙이지 마십시오. 세상을 똑바로 정면으로 바라보십시오 -헬렌켈러-",
                        "고난의 시기에 동요하지 않는 것 -베토벤-",
                        "당신이 할 수 있다고 믿든 할 수 없다고 믿든 믿는대로 될 것이다. -헨리 포드-",
                        "작은 기회로부터 종종 위대한 업적이 시작된다. -데모스테네스-"};
        Random random = new Random();
        int randomInt = (int)(Math.random() * randomList.length);
        model.addAttribute("randomQuote",randomList[randomInt]);

        return "quote";

    }
}
