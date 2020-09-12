package com.csfive.hanium.iseeyou.dto.category;

import com.csfive.hanium.iseeyou.domain.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryDetailResDto {
    private int year;
    private int month;
    private int day;
    private int time_book;
    private int time_pen;
    private int time_laptop;
    private Long student_id;
    private String student_email;


    public CategoryDetailResDto(Category category){
        this.year = category.getYear();
        this.month = category.getMonth();
        this.day = category.getDay();
        this.time_book = category.getTime_book();
        this.time_pen = category.getTime_pen();
        this.time_laptop = category.getTime_laptop();
        this.student_id = category.getStudent().getId();
        this.student_email = category.getStudent().getEmail();
    }
}
