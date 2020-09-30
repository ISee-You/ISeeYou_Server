package com.csfive.hanium.iseeyou.dto.grade;

import com.csfive.hanium.iseeyou.domain.grade.Grade;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class GradeDto {

    private Long id;
    private LocalDate examDate;
    private double korean;
    private double english;
    private double math;
    private double society;
    private double science;

    public GradeDto(final Grade grade) {
        this.id = grade.getId();
        this.examDate = grade.getExamDate();
        this.korean = grade.getKorean();
        this.english = grade.getEnglish();
        this.math = grade.getMath();
        this.society = grade.getSociety();
        this.science = grade.getScience();
    }
}
