package com.csfive.hanium.iseeyou.dto.student;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentLoginRequest {
    private String email;
    private String password;
}
