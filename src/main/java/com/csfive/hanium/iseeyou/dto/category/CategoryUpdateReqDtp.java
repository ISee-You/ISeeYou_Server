package com.csfive.hanium.iseeyou.dto.category;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryUpdateReqDtp {
    private int year;
    private int month;
    private int day;
    private int time_pen;
    private int time_book;
    private int time_laptop;
}
