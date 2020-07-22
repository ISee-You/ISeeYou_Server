package com.csfive.hanium.iseeyou.web.dto;

import com.csfive.hanium.iseeyou.domain.parent.Gender;
import com.csfive.hanium.iseeyou.domain.parent.Parent;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ParentSavetRequestDto {

    private String name;
    private String email;
    private String password;
    private Gender gender;

    @Builder
    public ParentSavetRequestDto(String name, String email, String password, Gender gender){
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    public Parent toEntity(){
        return Parent.builder()
                .name(name)
                .email(email)
                .password(password)
                .gender(gender)
                .build();
    }

}
