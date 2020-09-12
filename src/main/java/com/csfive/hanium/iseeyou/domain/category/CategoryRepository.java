package com.csfive.hanium.iseeyou.domain.category;

import com.csfive.hanium.iseeyou.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    //List<Category> findByYearAndMonthAndDay(int year, int month, int day);
    Category findByYearAndMonthAndDay(int year, int month, int day);
    Optional<Category> findByStudent(Student student);
}
