package com.example.firstproject.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity// JPA에서 제공하는 어노테이션이다.
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Article { //클래스를 기반으로  DB테이블을 생성해준다 테이블 이름은 클래스이름과 동일하다

    @Id//레코드를 인식할 수 있는 primary key(무조건 있어야됨)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //알아서 자동 생성해줌 1....2....3...4
//strategy = GenerationType.IDENTITY => 더미데이터를 인식해서 번호를 정해줌
    private Long id;

    @Column
    private String title;

    @Column
    private String content;


    public void patch(Article article) {
        if(article.title!=null){
            this.title = article.title;
        }

        if(article.content!=null){
            this.content = article.content;
        }
    }

}
