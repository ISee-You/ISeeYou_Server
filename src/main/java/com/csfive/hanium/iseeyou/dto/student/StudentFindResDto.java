package com.csfive.hanium.iseeyou.dto.student;

import com.csfive.hanium.iseeyou.domain.student.Student;
import com.csfive.hanium.iseeyou.enums.GenderType;
import com.csfive.hanium.iseeyou.enums.HandType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentFindResDto {
    private String name;
    private String email;
    private String password;
    private HandType handType;
    private GenderType genderType;

    public StudentFindResDto(Student student) {
        this.name = student.getName();
        this.email = student.getEmail();
        this.password = student.getPassword();
        this.handType = student.getHandType();
        this.genderType = student.getGender();
    }
}
