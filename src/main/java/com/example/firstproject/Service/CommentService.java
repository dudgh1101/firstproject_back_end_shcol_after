package com.example.firstproject.Service;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.dto.CommentDto;
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
        
        //댓글 엔티티를 생성
        //댓글 엔티티를 db로 저장
        //dto로 변환해서 반환
        return null;
    }
}
