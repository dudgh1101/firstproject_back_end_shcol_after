package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepositry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleRepositry articleRepositry;
//    DI => 의존성 주의
//    Get
    //전부 가져오기
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepositry.findAll();
    }
    //하나 가져오기
    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id){
        return articleRepositry.findById(id).orElse(null);
    }
//    Post
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){
        Article article = dto.toEntity();
        return articleRepositry.save(article);
    }
//    Patch
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> index(@PathVariable Long id, @RequestBody ArticleForm dto){

//        1.수정용 엔티티 생성
        Article article = dto.toEntity();
        log.info("id : {},article : {}",id,article.toString());

//        2.대상 엔티티 조회
        Article target = articleRepositry.findById(id).orElse(null);

//        3. 잘못된 요청 처리(대상이 없거나, id가 다른경우)
        if(target == null || id != article.getId()){
            log.info("잘못된 요청입니다. id : {} article : {}",id,article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
//        4.업데이트 및 정상 확인'
        target.patch(article);
        Article updated = articleRepositry.save(article);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }



//    delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
//        1.대상 찾기
        Article target = articleRepositry.findById(id).orElse(null);

        if(target ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        articleRepositry.delete(target);
        return ResponseEntity.status(HttpStatus.OK).body(null);


    }
}

