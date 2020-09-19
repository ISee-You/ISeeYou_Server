package com.csfive.hanium.iseeyou.domain.category;

import com.csfive.hanium.iseeyou.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.year=:year AND c.month=:month AND c.day=:day AND c.student = :student")
    List<Category> findByYearAndMonthAndDay(@Param("year")int year,@Param("month") int month,@Param("day") int day,@Param("student")Student student);
//    Category findByYearAndMonthAndDay(int year, int month, int day);
    List<Category> findByStudent(Student student);

//    @Query("SELECT c FROM Category c WHERE c.student = :student")
//    <T> List<T> findByStudent(@Param("student") Student student, Class<T> type);

//    List<Category> findByStudent_Id(long student_id);


}
