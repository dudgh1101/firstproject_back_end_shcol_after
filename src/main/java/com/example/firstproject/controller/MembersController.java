package com.example.firstproject.controller;

import com.example.firstproject.dto.MembersForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class MembersController {

    @Autowired
    private MemberRepository memberRepository;


    @GetMapping("/members/signUp")
    public String SignupPage(Model model){

        return "members/new";
    }

    @PostMapping("/members/join")
    public String Join(MembersForm form){

        System.out.println(form.toString());

        Member member = form.toEntity();
        System.out.println(member.toString());

        Member saved = memberRepository.save(member);
        System.out.println(saved.toString());

        return "redirect:/members/" + saved.getId();
    }
    @GetMapping("/members/{id}")
    public String Show(@PathVariable Long id, Model model){

        Member memberEntity= memberRepository.findById(id).orElse(null);
        // url창에서 데이터를 받음
        //데이터를 컨트롤러가 레포지토리한테 아이디를 주면서 요청
        //findById가 select * from where을 포함해서 찾음
        //그 데이터를 모델에 등록
//          마지막으로 view에서 보여주기
        model.addAttribute("member",memberEntity);
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model){

        List<Member> memberEntityList = memberRepository.findAll();

        model.addAttribute("memberEntityList", memberEntityList);

        return  "members/index";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){

        Member member = memberRepository.findById(id).orElse(null);

        model.addAttribute("member",member);
        return "members/edit";
    }

    @PostMapping("/members/update")
    public String update(MembersForm form){

        log.info(form.toString());
        Member memberEntity = form.toEntity();
        log.info(memberEntity.toString());


        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);

        if(target != null)
        {
            memberRepository.save(memberEntity);
        }
        return "redirect:/members/"+memberEntity.getId();
    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes rttr){
        log.info("삭제요청이 들어왔습니다.");

        Member target = memberRepository.findById(id).orElse(null);

        if(target != null){
            memberRepository.delete(target);
        }

        rttr.addFlashAttribute("msg","삭제 완료");

        return "redirect:/members";
    }
}
