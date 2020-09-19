package com.csfive.hanium.iseeyou.dto.category;

import lombok.Getter;

@Getter
public class CategoryProficiencyResDto {
    private String categoryname;
    private Long hour;
    private Long min;
    private Long sec;
    private String userProficiency;

    public CategoryProficiencyResDto(String categoryname,long hour, long min, long sec,String userProficiency){
        this.categoryname = categoryname;
        this.hour = hour;
        this.min = min;
        this.sec = sec;
        this.userProficiency = userProficiency;
    }
}
