package com.csfive.hanium.iseeyou.domain.category;

import com.csfive.hanium.iseeyou.domain.student.Student;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STIDENT_ID")
    private Student student;

    private int year;

    private int month;

    private int day;

    private int time_pen;

    private int time_book;

    private int time_laptop;

    @Builder
    public Category(Student student){
        this.student = student;
    }

    public void update(int year, int month, int day, int time_pen, int time_book,int time_laptop){
        this.year = year;
        this.month = month;
        this.day = day;
        this.time_pen = time_pen;
        this.time_book = time_book;
        this.time_laptop = time_laptop;
    }
}
