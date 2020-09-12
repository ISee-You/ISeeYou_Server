package com.csfive.hanium.iseeyou.dto.category;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryDetailReqDto {
    private int year;
    private int month;
    private int day;
}
