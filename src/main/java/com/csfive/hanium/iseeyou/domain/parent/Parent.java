package com.csfive.hanium.iseeyou.domain.parent;

import com.csfive.hanium.iseeyou.domain.student.Student;
import com.csfive.hanium.iseeyou.enums.GenderType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PARENT_ID")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderType gender;

    @OneToMany(mappedBy = "parent_id")
    private List<Student> students = new ArrayList<Student>();

    @Builder
    public Parent(String name, String password, String email, GenderType gender){
        this.name = name;
        this.password = password;
        this.email = email;
        this.gender = gender;
    }

    public void addChild(Student student){
        this.students.add(student);
        student.setParent(this);
    }

    public void deleteChild(Student student){
        this.students.remove(student);
        student.setParent(null);
    }

    public void update(String name, String password, String email, GenderType gender){
        this.name = name;
        this.password = password;
        this.email = email;
        this.gender = gender;
    }
}
