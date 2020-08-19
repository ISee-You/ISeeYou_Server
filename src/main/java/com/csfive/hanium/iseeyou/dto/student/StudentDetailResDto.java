package com.csfive.hanium.iseeyou.dto.student;

import com.csfive.hanium.iseeyou.domain.student.Student;
import com.csfive.hanium.iseeyou.enums.GenderType;
import com.csfive.hanium.iseeyou.enums.HandType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class StudentDetailResDto {

    private Long id;
    private String name;
    private String password;
    private HandType handType;
    private GenderType genderType;

    public StudentDetailResDto(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.password = student.getPassword();
        this.handType = student.getHandType();
        this.genderType = student.getGender();
    }
}
