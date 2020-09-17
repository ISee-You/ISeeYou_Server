package com.csfive.hanium.iseeyou.domain.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmailAndPassword(String email, String password);
    Optional<Student> findByNameAndEmail(String name,String email);
    Optional<Student> findByEmail(String email);
}
