package com.csfive.hanium.iseeyou.service;

import com.csfive.hanium.iseeyou.domain.grade.Grade;
import com.csfive.hanium.iseeyou.domain.grade.GradeRepository;
import com.csfive.hanium.iseeyou.domain.student.Student;
import com.csfive.hanium.iseeyou.domain.student.StudentRepository;
import com.csfive.hanium.iseeyou.dto.grade.GradeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public Long saveWithStudent(final GradeRequest gradeRequest, final Long studentId) {
        Student student = findStudentById(studentId);

        Grade grade = Grade.builder()
                .student(student)
                .examDate(gradeRequest.getExamDate())
                .korean(gradeRequest.getKorean())
                .english(gradeRequest.getEnglish())
                .math(gradeRequest.getMath())
                .society(gradeRequest.getSociety())
                .science(gradeRequest.getScience())
                .build();

        gradeRepository.save(grade);

        return grade.getId();
    }

    public Long findOne(final Long gradeId) {
        Grade findGrade = findGradeById(gradeId);
        return findGrade.getId();
    }

    private Grade findGradeById(final Long gradeId) {
        return gradeRepository.findById(gradeId)
                .orElseThrow(() -> new NoSuchElementException(String.format("gradeId: %d, 존재하지 않는 아이디 입니다.", gradeId)));
    }

    private Student findStudentById(final Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException(String.format("studentId: %d, 존재하지 않는 아이디 입니다.", studentId)));
    }
}
