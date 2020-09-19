package com.csfive.hanium.iseeyou.domain.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Proficiencytime {
    private String categoryname;
    private long hour;
    private long min;
    private long sec;
    private String level;

    public void setProficiency (long sec){
        System.out.println("cattegory sec : "+sec);
        convertTime(sec);
        convertLevel(this.hour);
    }

    private void convertTime(long sec){
        long m = sec/60;
        long h = m/60;
        long s = sec%60;
        m = m%60;

        this.hour= h;
        this.min=m;
        this.sec=s;
    }

    private void convertLevel(long hour){
        if(hour >= 10000){
            this.level =  "챌린저";
        }else if(hour >=9000){
            this.level =  "그랜드마스터";
        }else if(hour >=8000){
            this.level =  "마스터";
        }else if(hour >=7000){
            this.level =  "다이아";
        }else if(hour >= 5000){
            this.level =   "플레티넘";
        }else if(hour >= 3000){
            this.level =   "골드";
        }else if(hour >= 1000){
            this.level =   "실버";
        }else if(hour >= 150){
            this.level =   "브론즈";
        }else{
            this.level =  "아이언";
        }
    }
}
