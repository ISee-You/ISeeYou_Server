package com.csfive.hanium.iseeyou.dto.category;

import com.csfive.hanium.iseeyou.domain.category.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
public class CategoryDetailReqDto {
    private int year;
    private int month;
    private int day;
}
