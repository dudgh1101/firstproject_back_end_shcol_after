package com.example.firstproject.api;

import com.example.firstproject.Service.ArticleService;
import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
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
    private ArticleService articleService;
//    DI => 의존성 주의
//    Get

    @GetMapping("/api/articles")
    public List<Article> show(){
        return articleService.findAll();
    }
    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id){
        return articleService.index(id);
    }
//   Post
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
////    Patch
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> index(@PathVariable Long id, @RequestBody ArticleForm dto){

        Article updated = articleService.update(id,dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
//
//
//
////    delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article deleted = articleService.delete(id);
        return (deleted != null) ? ResponseEntity.status(HttpStatus.OK).body(null) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//
//
  }

//  트렌젝션
//    실패했을떄 다시 롤벡
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null) ? ResponseEntity.status(HttpStatus.CREATED).body(createdList) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}


