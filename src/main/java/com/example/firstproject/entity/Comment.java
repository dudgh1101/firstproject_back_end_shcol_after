package com.example.firstproject.entity;


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






}
