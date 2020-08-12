package com.csfive.hanium.iseeyou.dto.parent;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParentAddChildRequestDto {
    private String studentEmail;
    private String studentName;
}
