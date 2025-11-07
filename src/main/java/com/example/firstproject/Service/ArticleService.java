package com.example.firstproject.Service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service // 서비스 객체 생성 (서비스객체를 스프링부트 내무에 생성)
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepositry;

    public List<Article> findAll() {
        return articleRepositry.findAll();
    }
    public Article index(Long id) {
        return articleRepositry.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article entity = dto.toEntity();

        if(entity.getId() != null){
            return articleRepositry.save(entity);
        }
        return null;
    }
    public Article update(Long id,ArticleForm dto){
        //        1.수정용 엔티티 생성
        Article article = dto.toEntity();
        log.info("id : {},article : {}",id,article.toString());

//        2.대상 엔티티 조회
        Article target = articleRepositry.findById(id).orElse(null);

//        3. 잘못된 요청 처리(대상이 없거나, id가 다른경우)
        if(target == null || id != article.getId()){
            log.info("잘못된 요청입니다. id : {} article : {}",id,article.toString());
            return null;
        }
//        4.업데이트 및 정상 확인'
        target.patch(article);
        Article updated = articleRepositry.save(target);
        return updated;
    }

    public Article delete(Long id) {
        //        1.대상 찾기
        Article target = articleRepositry.findById(id).orElse(null);

        if(target ==null){
            return null;
        }
        articleRepositry.delete(target);
        return  target;
    }

    @Transactional

    public List<Article> createArticles(List<ArticleForm> dtos) {
        //dto 묶음을 엔티티 묶음으로 변화
//        List<Article> articleList = dtos.stream().map(dto -> dto.toEntity()).collect(Collector.toList<>);
        List<Article> articleList = new ArrayList<>();
        for(int i = 0; i<dtos.size();i++){
            ArticleForm dto = dtos.get(i);
            Article entity = dto.toEntity();
            articleList.add(entity);
        }
            //entity 묶음을 리스트로 데이터베이스에 넣기
//            articleList.stream().forEach();
        for(int i = 0; i < articleList.size();i++){
            Article article = articleList.get(i);
            articleRepositry.save(article);
        }
        //강제 예외 발생
        articleRepositry.findById(-1L).orElseThrow(
                ()-> new IllegalArgumentException("결제 실패")
        );
        //결과값 반환
        return articleList;
    }
}
