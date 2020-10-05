package com.csfive.hanium.iseeyou.domain.attitude;

import com.csfive.hanium.iseeyou.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AttitudeRepository extends JpaRepository<Attitude, Long>, AttitudeRepositoryCustom {

    @Query("select a from Attitude a " +
            "join fetch a.poses.poses " +
            "where a = :attitude " +
            "and a.student = :student")
    Attitude findOneByStudent(@Param("attitude") Attitude attitude, @Param("student") Student student);

    @Query("select a from Attitude a " +
            "join fetch a.poses.poses " +
            "where a = :attitude")
    Optional<Attitude> findJoinFetchByEntity(Attitude attitude);
}
