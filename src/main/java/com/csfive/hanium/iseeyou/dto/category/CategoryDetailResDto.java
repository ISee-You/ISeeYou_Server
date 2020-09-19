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
    private int booktime;
    private int pentime;
    private int laptoptime;
    private Long student_id;
    private String student_email;


    public CategoryDetailResDto(Category category){
        this.year = category.getYear();
        this.month = category.getMonth();
        this.day = category.getDay();
        this.booktime = category.getBookTime();
        this.pentime = category.getPenTime();
        this.laptoptime = category.getLaptopTime();
        this.student_id = category.getStudent().getId();
        this.student_email = category.getStudent().getEmail();
    }
}
