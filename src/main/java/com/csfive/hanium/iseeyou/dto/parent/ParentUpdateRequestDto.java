package com.csfive.hanium.iseeyou.dto.parent;

import com.csfive.hanium.iseeyou.enums.GenderType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParentUpdateRequestDto {
    private String name;
    private String email;
    private String password;
    private GenderType gender;
}
