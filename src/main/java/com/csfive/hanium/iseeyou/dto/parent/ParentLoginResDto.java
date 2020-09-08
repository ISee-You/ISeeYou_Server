package com.csfive.hanium.iseeyou.dto.parent;

import com.csfive.hanium.iseeyou.domain.parent.Parent;
import com.csfive.hanium.iseeyou.enums.GenderType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ParentLoginResDto {
    private Long id;
    private String email;
    private String name;
    private GenderType gender;

    public ParentLoginResDto(Parent parent){
        this.id = parent.getId();
        this.email = parent.getEmail();
        this.name = parent.getName();
        this.gender = parent.getGender();
    }

}
