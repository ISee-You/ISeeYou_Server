package com.csfive.hanium.iseeyou.domain.grade;

import com.csfive.hanium.iseeyou.domain.BaseTimeEntity;
import com.csfive.hanium.iseeyou.domain.student.Student;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Grade extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GRADE_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "SUTDENT_ID")
    private Student student;

    private LocalDate examDate;

    private double korean;
    private double english;
    private double math;
    private double society;
    private double science;

    @Builder
    public Grade(final Student student, final LocalDate examDate,
                 final double korean, final double english, final double math, final double society, final double science) {
        validatePositiveNumber(korean);
        validatePositiveNumber(english);
        validatePositiveNumber(math);
        validatePositiveNumber(society);
        validatePositiveNumber(science);
        this.student = student;
        this.examDate = examDate;
        this.korean = korean;
        this.english = english;
        this.math = math;
        this.society = society;
        this.science = science;
    }

    private void validatePositiveNumber(final double score) {
        if (score < 0) {
            throw new IllegalArgumentException(String.format("score: %.2f 시험 점수는 0보다 커야합니다.", score));
        }
    }
}
