package com.example.firstproject.Service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest //해당 클래스는 스프링 부트와 연동되어 테스팅 된다.
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        //예상
        Article a = new Article(1L,"가가가가","1111");
        Article b = new Article(2L,"나나나나","2222");
        Article c = new Article(3L,"다다다다","3333");

        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));

        //실제
        List<Article> articles = articleService.index();

        //비교

        assertEquals(expected.toString(),articles.toString());
    }

    @Test
    void Index_성공____존재하는_id_입력() {
//        예상
        Long id = 1L;
        Article expected = new Article(id,"가가가가","1111");
//        실제
        Article article = articleService.index(id);

//        비교

        assertEquals(expected.toString(),article.toString());

    }


    @Test
    void Index_실패____존재하지않는_id_입력() {

        Long id = -4L;
        Article expected = null;

        Article article = articleService.index(id);

        assertEquals(expected,article);
        
    }
    
    //테스트-create 다 실패함 살펴볼것
    @Test
    @Transactional
    void create__성공_title과_content만_있는_dto_입력() {
        //예상
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null,title,content);
        Article expected = new Article(4L,title,content);
        //실제
        Article article = articleService.create(dto);

        //비교
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void create__실패_id가_포함된_dto_입력() {
        //예상
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(4L,title,content);
        Article expected = null;
        //실제
        Article article = articleService.create(dto);

        //비교
        assertEquals(expected,article);
    }
    @Test
    @Transactional
    void  update_성공____존재하는_id와_title_cotent가_있는_dto가_입력(){
        
        //예상

        Long id = 1L;
        String title = "가수정용";
        String content = "가수정이야";

        ArticleForm dto = new ArticleForm(id,title,content);
        Article expected = new Article(1L,"가수정용","가수정이야");

        //실제
        Article article = articleService.update(id,dto);
        //비교

        assertEquals(expected.toString(),article.toString());

        
    }

    @Test
    @Transactional
    void  update____존재하는_id와_title만_있는_dto가_입력되었을_때(){
        //예상

        Long id = 1L;
        String title = "가수정용";
        String content = null;
        ArticleForm dto = new ArticleForm(id,title, content);
        Article expected = new Article(1L,"가수정용","1111");

        //실제
        Article article = articleService.update(id,dto);
        //비교

        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void  update_실패___존재하지않는_id의_dto입력(){

        //예상

        Long id = -1L;
        String title = "가수정용";
        String content = "기수정";
        ArticleForm dto = new ArticleForm(id,title ,content);
        Article expected = null;

        //실제

        Article article = articleService.update(id,dto);
        
        //비교
        assertEquals(expected,article);
    }

    @Test
    @Transactional
    void  update_id만_있는_dto_입력(){
        //예상
        Long id = 1L;
        String title = null;
        String content = null;
        ArticleForm dto = new ArticleForm(id,title ,content);
        Article expected = new Article(id,"가가가가","1111");
        //실제
        Article article = articleService.update(id,dto);
        //비교
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void  delete_id_존재하는_id_입력____성공(){
        //예상
        Long id = 1L;
        Article expected = new Article(id,"가가가가","1111");
        //실제
        Article article = articleService.delete(id);

        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void  delete_id_존재하지_않는_id_입력____실패(){
        //예상
        Long id = -1L;
        Article expected = null;
        //실제
        Article article = articleService.delete(id);

        assertEquals(expected,article);
    }
}