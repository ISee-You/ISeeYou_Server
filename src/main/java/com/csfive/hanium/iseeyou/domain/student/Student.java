package com.csfive.hanium.iseeyou.domain.student;

import com.csfive.hanium.iseeyou.domain.parent.Parent;
import com.csfive.hanium.iseeyou.enums.GenderType;
import com.csfive.hanium.iseeyou.enums.HandType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ID")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = true)
    private HandType handType;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = true)
    private GenderType gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;

    public void changeParent(final Parent parent) {
        this.parent = parent;
        parent.getStudents().add(this);
    }

    public void removeParent(final Parent parent) {
        validateEqualsParent(parent);
        this.parent.getStudents().remove(this);
        removeParent();
    }

    public void removeParent() {
        this.parent = null;
    }

    public void changeStudent(final Parent parent) {
        this.parent = parent;
    }

    public void update(final String name, final String email, final String password, final HandType handType, final GenderType genderType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.handType = handType;
        this.gender = genderType;
    }

    public boolean isEqualsOfEmail(final String email) {
        return getEmail().equals(email);
    }

    private void validateEqualsParent(final Parent parent) {
        if (!this.parent.equals(parent)) {
            throw new IllegalArgumentException("부모가 일치하지 않습니다.");
        }
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
