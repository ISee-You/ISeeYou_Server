package com.csfive.hanium.iseeyou.domain.grade;

import com.csfive.hanium.iseeyou.domain.student.Student;
import com.csfive.hanium.iseeyou.dto.grade.GradeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query("select new com.csfive.hanium.iseeyou.dto.grade.GradeDto(g) from Grade g " +
            "where g.id = :gradeId")
    GradeDto findGradeDtoById(@Param("gradeId") Long gradeId);

    @Query("select g from Grade g " +
            "where g.student = :student " +
            "order by g.examDate asc")
    List<Grade> findAllByStudent(@Param("student") Student student);
}
