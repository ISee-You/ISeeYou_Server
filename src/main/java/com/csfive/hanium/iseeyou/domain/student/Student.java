package com.csfive.hanium.iseeyou.domain.student;

import com.csfive.hanium.iseeyou.domain.parent.Parent;
import com.csfive.hanium.iseeyou.enums.GenderType;
import com.csfive.hanium.iseeyou.enums.HandType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ID")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private HandType handType;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private GenderType gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @Builder
    public Student(String name, String email, String password, HandType handType, GenderType gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.handType = handType;
        this.gender = gender;
    }
}
