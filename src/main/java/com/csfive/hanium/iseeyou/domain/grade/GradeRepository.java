package com.csfive.hanium.iseeyou.domain.grade;

import com.csfive.hanium.iseeyou.dto.grade.GradeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query("select new com.csfive.hanium.iseeyou.dto.grade.GradeDto(g) from Grade g " +
            "where g.id = :gradeId")
    GradeDto findGradeDtoById(@Param("gradeId") Long gradeId);
}
