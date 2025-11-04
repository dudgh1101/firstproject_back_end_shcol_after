package com.example.firstproject.dto;



import com.example.firstproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@ToString
public class MembersForm {
    private Long id;
    private String email;
    private String pw;


    public Member toEntity() {

        return new Member(id,email,pw);
    }
}
