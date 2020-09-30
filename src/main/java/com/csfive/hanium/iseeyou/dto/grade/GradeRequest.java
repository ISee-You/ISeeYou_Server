package com.csfive.hanium.iseeyou.dto.grade;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class GradeRequest {

    private LocalDate examDate;
    private double korean;
    private double english;
    private double math;
    private double society;
    private double science;

    public GradeRequest(final LocalDate examDate, final double korean, final double english, final double math, final double society, final double science) {
        this.examDate = examDate;
        this.korean = korean;
        this.english = english;
        this.math = math;
        this.society = society;
        this.science = science;
    }
}
