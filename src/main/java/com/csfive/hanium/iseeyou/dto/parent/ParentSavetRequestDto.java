package com.csfive.hanium.iseeyou.dto.parent;

import com.csfive.hanium.iseeyou.domain.parent.Parent;
import com.csfive.hanium.iseeyou.enums.GenderType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ParentSavetRequestDto {

    @NotEmpty(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
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
