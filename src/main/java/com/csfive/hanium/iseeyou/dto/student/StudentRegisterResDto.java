package com.csfive.hanium.iseeyou.dto.student;

import com.csfive.hanium.iseeyou.domain.student.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudentRegisterResDto {
    private String name;
    private String email;

    public StudentRegisterResDto(Student student) {
        this.name = student.getName();
        this.email = student.getEmail();
    }
}
