package com.csfive.hanium.iseeyou.domain.student;

import com.csfive.hanium.iseeyou.domain.attitude.Attitude;
import com.csfive.hanium.iseeyou.domain.parent.Parent;
import com.csfive.hanium.iseeyou.enums.GenderType;
import com.csfive.hanium.iseeyou.enums.HandType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
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
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attitude> attitudes = new ArrayList<>();

    public void changeParent(final Parent parent) {
        this.parent = parent;
        parent.getStudents().add(this);
    }

    public void deleteTo(final Parent parent) {
        if (!this.parent.equals(parent)) {
            throw new IllegalArgumentException("부모가 일치하지 않습니다.");
        }
        this.parent.getStudents().remove(this);
        this.parent = null;
    }

    @Builder

    public Student(String name, String email, String password, HandType handType, GenderType gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.handType = handType;
        this.gender = gender;
    }
}
