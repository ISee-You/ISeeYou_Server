package com.csfive.hanium.iseeyou.domain.student;

import com.csfive.hanium.iseeyou.domain.parent.Parent;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "STUDENT_ID")
    private Long id;

    private String name;

    private String email;

    private String password;

    @ManyToOne
    //@JoinColumn(name = "id")
    private Parent parent;

}
