package com.csfive.hanium.iseeyou.domain.attitude;

import com.csfive.hanium.iseeyou.domain.student.Student;

import java.time.LocalDateTime;
import java.util.List;

public interface AttitudeRepositoryCustom {
    List<Attitude> findAllByStudentAndBetweenLocalDateTime(Student student, LocalDateTime startDate, LocalDateTime endDate);
}
