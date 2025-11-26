package com.example.firstproject.entity;


import com.example.firstproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    //@JoinColumn(name = "외래키 이름") : 외레키 생성, Article 엔티티의 기본키(id)와 맵핑
    private Article article;//하나의 댓글 엔티티 여러개가 하나의 Article에 연결된다.

    @Column
    private String nickname;

    @Column
    private String body;


    public static Comment createComment(CommentDto dto, Article article) {

        //예외 발생
        if(dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패: 댓글에 아이디가 없어야합니다");

        if(dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패: 게시글의 아이디가 잘못되었습니다");

        //엔티팉 생성및 반환

        return new Comment(dto.getId(),article, dto.getNickname(), dto.getBody());
    }

    public void patch(CommentDto dto) {
        
        //예외 발생
        if(this.id != dto.getId())
            throw new IllegalArgumentException("잘못된 아이디가 입력되었습니다.");
        //객체 갱신
        if (dto.getNickname() != null){
            this.nickname = dto.getNickname();
        }
        if (dto.getBody() != null){
            this.body = dto.getBody();
        }
    }
}
