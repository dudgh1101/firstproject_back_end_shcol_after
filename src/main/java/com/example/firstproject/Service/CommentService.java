package com.example.firstproject.Service;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        // 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        //변환 엔티티 -> 디티오

        List<CommentDto> dtos = new ArrayList<>();

        for(int i = 0; i<comments.size();i++){
            Comment c = comments.get(i);
            CommentDto dto = CommentDto.creatreCommentDto(c);
            dtos.add(dto);
        }

        //반환
        return dtos;
    }

    public CommentDto create(Long articleId, CommentDto dto) {
        //게시글 조회및 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패: 대상 게시물이 없습니다."));

        //댓글 엔티티를 생성
        Comment comment = Comment.createComment(dto,article);

        //댓글 엔티티를 db로 저장
        Comment created = commentRepository.save(comment);
        //dto로 변환해서 반환
        return CommentDto.creatreCommentDto(created);
    }

    public CommentDto update(Long id, CommentDto dto) {
        
        //댓글 조회및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("댓글이 존재하지 않습니다"));
        //댓글 수정
        target.patch(dto);
        //db에 바꾼거 다시 저장(갱신)
        Comment updated = commentRepository.save(target);
//        댓글 엔티티를 dto로 변화후 반환
        return CommentDto.creatreCommentDto(updated);
    }

    public CommentDto delete(Long id) {
        //댓글 조회및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("댓글 삭제실패:대상이 존재하지 않습니다."));

        //댓글 삭제
        commentRepository.delete(target);

        //삭제 댓글을 dto로 변환 후 반환
        return CommentDto.creatreCommentDto(target);
    }
}
