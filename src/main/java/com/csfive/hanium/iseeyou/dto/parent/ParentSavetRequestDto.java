package com.csfive.hanium.iseeyou.dto.parent;

import com.csfive.hanium.iseeyou.domain.parent.Parent;
import com.csfive.hanium.iseeyou.enums.GenderType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ParentSavetRequestDto {

    private String name;
    private String email;
    private String password;
    private GenderType gender;

    @Builder
    public ParentSavetRequestDto(String name, String email, String password, GenderType gender){
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
