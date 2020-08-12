package com.csfive.hanium.iseeyou.domain.student;

import com.csfive.hanium.iseeyou.domain.parent.Parent;
import com.csfive.hanium.iseeyou.enums.GenderType;
import com.csfive.hanium.iseeyou.enums.HandType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "hand")
    private HandType handType;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "gender")
    private GenderType genderType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @Column(name = "recorderIdx")
    private Long recorderIdx;

    @Builder
    public Student(String name, String email, String password, HandType handType, GenderType genderType,  Long recorderIdx) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.handType = handType;
        this.genderType = genderType;
        this.recorderIdx = recorderIdx;
    }
}
