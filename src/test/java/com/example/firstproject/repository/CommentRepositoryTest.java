package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
//jpa와 연동한 테스트를 하겠다.
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;


    @Test
    @DisplayName("특정 게시물의 모든 댓글 조회")
    void findByArticleId() {
//        Case1: 4번 게시글의 모든 댓글 조회
        {
            //입력 데이터 준비
            Long articleid = 4L;

            //실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleid);

            //예상
            Article article = new Article(4L,"당신의 인생 영화는?","댓글 ㄱ");
            Comment A = new Comment(1L,article,"park","굳 윌 헌팅");
            Comment B = new Comment(2L,article,"kim","아이 엠 샘");
            Comment C = new Comment(3L,article,"choi","쇼생크의 탈출");
            List<Comment> expected = Arrays.asList(A,B,C);
            //검증
            assertEquals(expected.toString(),comments.toString(),"4번의 모든 댓글을 출력");
        }

//        Case2 1번 게시물의 모든 댓글 조회
        {
            //입력 데이터 준비
            Long id = 1L;
            //실제 수행
            List<Comment> comments = commentRepository.findByArticleId(id);
            //예상하기
            Article article = new Article(1L, "가가가가","1111");
            List<Comment> expected = Arrays.asList();

            //검증
            assertEquals(expected.toString(),comments.toString(),"1번 글은 댓글 출력(없음)");
        }
        //Case3 9번 게시물의 모든 댓글조회
        {
            //입력 데이터 준비
            Long articleid = 9L;

            //실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleid);

            //예상
            Article article = null;
            List<Comment> expected = Arrays.asList();
            //검증
            assertEquals(expected.toString(),comments.toString(),"9번의 모든 댓글을 출력(없음)");
        }
        //Case3 9999번 게시물의 모든 댓글조회
        {
            //입력 데이터 준비
            Long articleid = 9999L;

            //실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleid);

            //예상
            Article article = null;
            List<Comment> expected = Arrays.asList();
            //검증
            assertEquals(expected.toString(),comments.toString(),"9999번의 모든 댓글을 출력(없음)");
        }
        //Case3 -1번 게시물의 모든 댓글조회
        {
            //입력 데이터 준비
            Long articleid = -1L;

            //실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleid);

            //예상
            Article article = null;
            List<Comment> expected = Arrays.asList();
            //검증
            assertEquals(expected.toString(),comments.toString(),"-1번의 모든 댓글을 출력(없음)");
        }

        

    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글을 조회")
    void findByNickname() {
//        Case1: Park의 모든 댓글 조회
        {
            //입력 데이터 준비
            String nickname = "park";

            //실제수헹
            List<Comment> comments = commentRepository.findByNickname(nickname);
            //예상하기
            
            Comment a= new Comment(1L,new Article(4L,"당신의 인생 영화는?","댓글 ㄱ"),"park","굳 윌 헌팅");
            Comment b = new Comment(4L,new Article(5L,"당신의 소울 푸드는?","댓글 ㄱㄱ"),"park","치킨");
            Comment c = new Comment(7L,new Article(6L,"당신의 취미는?","댓글 ㄱㄱㄱ"),"park","조깅");
            List<Comment> expected = Arrays.asList(a,b,c);
            //검증
            assertEquals(expected.toString(),comments.toString(),"park님의 모든 댓글 출력");
        }
        //Case : kim의 모든 댓글조회
        {
            //입력 데이터 준비
            String nickname = "kim";

            //실제수헹
            List<Comment> comments = commentRepository.findByNickname(nickname);
            //예상하기

            Comment a= new Comment(2L,new Article(4L,"당신의 인생 영화는?","댓글 ㄱ"),"kim","아이 엠 샘");
            Comment b = new Comment(5L,new Article(5L,"당신의 소울 푸드는?","댓글 ㄱㄱ"),"kim","샤브샤브");
            Comment c = new Comment(8L,new Article(6L,"당신의 취미는?","댓글 ㄱㄱㄱ"),"kim","유튜브");
            List<Comment> expected = Arrays.asList(a,b,c);
            //검증
            assertEquals(expected.toString(),comments.toString(),"kim님의 모든 댓글 출력");
        }

        //Case : null의 모든 댓글조회
        {
            //입력 데이터 준비
            String nickname = null;

            //실제수헹
            List<Comment> comments = commentRepository.findByNickname(nickname);
            //예상하기
            List<Comment> expected = Arrays.asList();
            //검증
            assertEquals(expected.toString(),comments.toString(),"null의 모든 댓글 출력(없음)");
        }
        //Case : ""의 모든 댓글조회
        {
            //입력 데이터 준비
            String nickname = "";

            //실제수헹
            List<Comment> comments = commentRepository.findByNickname(nickname);
            //예상하기

            List<Comment> expected = Arrays.asList();
            //검증
            assertEquals(expected.toString(),comments.toString()," ''님의 모든 댓글 출력(없음).");
        }
        //Case : "i"의 모든 댓글조회
        {
            //입력 데이터 준비
            String nickname = "i";

            //실제수헹
            List<Comment> comments = commentRepository.findByNickname(nickname);
            //예상하기

            List<Comment> expected = Arrays.asList();
            //검증
            assertEquals(expected.toString(),comments.toString(),"i의 모든 댓글 출력(없음)");
        }
    }
    
}