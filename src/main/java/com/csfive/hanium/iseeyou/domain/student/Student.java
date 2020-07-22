package com.csfive.hanium.iseeyou.domain.student;

import com.csfive.hanium.iseeyou.enums.GenderType;
import com.csfive.hanium.iseeyou.enums.HandType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "STUDENTS")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "HAND")
    private HandType handType;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "GENDER")
    private GenderType genderType;

    @Column(name = "PARENT_ID")
    private Long parentId;

    @Column(name = "RECORDER_ID")
    private Long recorderId;

    @Builder
    public Student(String name, String email, String password, HandType handType, GenderType genderType, Long parentId, Long recorderId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.handType = handType;
        this.genderType = genderType;
        this.parentId = parentId;
        this.recorderId = recorderId;
    }
}
