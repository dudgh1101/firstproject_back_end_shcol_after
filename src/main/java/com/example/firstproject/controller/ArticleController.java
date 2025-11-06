package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j// Simple Logging Facade for java, 로깅을 위한 어노테이션
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;


    @GetMapping("/articles/new")
    public String newArticleForm(){

        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        log.info(form.toString());
        
        //1.dto를 entity로 변환
        Article article = form.toEntity();
        log.info(article.toString());

        //2. repository로 entity를 DB에 저장
        Article saved =  articleRepository.save(article);
        log.info(saved.toString());

        return "redirect:/articles/"+saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String Show(@PathVariable Long id, Model model){
        //1.id 를 통해 데이터 가져오기
//        Optional<Article> articleEntity = articleRepository.findById(id);
        Article articleEntity = articleRepository.findById(id).orElse(null);
//        log.info(articleEntity.getId(id).orE);
        //2. 가져온 데이터를 nvc 중 에 Model에 등록
    model.addAttribute("article",articleEntity);

        //3. 보여줄 페이지를 설정(view)
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){

        //1. 모든 Articles를 가져옴
//        이터레이블 -> 자바 객체가 반복이 가능하다는 인터페이스,
//        List<Article> articlesEntityList = (List<Article>) articleRepository.findAll();
//        Iterable<Article> articlesEntityList = articleRepository.findAll();
        List<Article> articlesEntityList = articleRepository.findAll();
        //인타페이스에서 반환타입을 어레이리스트로 바꾼 것(다형성)
//        부모는 자식타입의 객체를 참조할 수 있다

        //2. 가져온 데이터 묶음을 모델에 전달
        model.addAttribute("articleList",articlesEntityList);
        //3. view 페이지를 설정
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id,Model model){

//      수정할 데이터 가져오기
        Article articlesEntity = articleRepository.findById(id).orElse(null);
//        model에 데이터 등록
        model.addAttribute("article",articlesEntity);
//      view 페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){

        log.info(form.toString());
        
        //DTO를 Entity로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        //2.Entity 를 DB에 저장
        //2-1.DB에서 기존 데이터 가져온다.
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        //2-2.기존 데이터가 있다면 값을 갱신한다.(없으면 갱신 X)
        if(target !=null){
            articleRepository.save(articleEntity);
        }
        //수정 결과 페이지로 리다이렉트
        return "redirect:/articles/"+articleEntity.getId();
    }
    
    //원래는 @DeleteMapping을 써야함
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔음");

        //1. 삭제 대상을 가져온다
        Article target = articleRepository.findById(id).orElse(null);


        //대상을 삭제한다
        if (target != null) {
            articleRepository.delete(target);
        }
        rttr.addFlashAttribute("msg","삭제가 완료 되었습니다");



        return "redirect:/articles";
    }


}
