package com.csfive.hanium.iseeyou.service;

import com.csfive.hanium.iseeyou.domain.parent.Parent;
import com.csfive.hanium.iseeyou.domain.parent.ParentRepository;
import com.csfive.hanium.iseeyou.domain.student.Student;
import com.csfive.hanium.iseeyou.domain.student.StudentRepository;
import com.csfive.hanium.iseeyou.dto.student.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;

    @Transactional
    public Long save(final StudentSaveRequest saveRequest) {
        Student student = Student.builder()
                .name(saveRequest.getName())
                .password(saveRequest.getPassword())
                .build();

        studentRepository.save(student);
        return student.getId();
    }

    @Transactional
    public void update(final Long studentId, final StudentUpdateRequest updateRequest) {
        Student student = getStudent(studentId);

        if(!student.isEqualsOfEmail(updateRequest.getEmail())){
            validateDuplicateEmail(updateRequest.getEmail());
        }
        student.update(updateRequest.getName(), updateRequest.getEmail(), updateRequest.getPassword(), updateRequest.getHandType(), updateRequest.getGenderType());
    }

    @Transactional
    public void delete(final Long studentId) {
        Student student = getStudent(studentId);
        studentRepository.delete(student);
    }

    public Student find(final Long studentId) {
        Student findStudent = getStudent(studentId);
        return findStudent;
    }

    public Long login(final StudentLoginRequest loginRequest) {
        Student findStudent = getStudent(loginRequest);
        return findStudent.getId();
    }

    @Transactional
    public void registerParent(final Long studentId, final Long parentId) {
        Student student = getStudent(studentId);
        Parent parent = parentRepository.getOne(parentId);

        student.changeParent(parent);
    }

    @Transactional
    public void deleteParent(final Long studentId, final Long parentId) {
        Student student = getStudent(studentId);
        Parent parent = parentRepository.getOne(parentId);

        student.removeParent(parent);
    }

    private Student getStudent(final Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException(String.format("id: %d, 존재하지 않는 아이디 입니다.", studentId)));
    }

    private Student getStudent(final StudentLoginRequest loginRequest) {
        return studentRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword())
                .orElseThrow(() -> new IllegalArgumentException(String.format("input email: %s, input password: %s 이메일 또는 비밀번호가 다릅니다.",
                        loginRequest.getEmail(), loginRequest.getPassword())));
    }

    private void validateDuplicateEmail(final String email) {
        Optional<Student> student = studentRepository.findByEmail(email);
        if (student.isPresent()) {
            throw new IllegalArgumentException(String.format("email: %s, 이미 존재하는 이메일입니다.", email));
        }
    }

    private void validateEqualsEmail(final String currentEmail, final String inputEmail) {
        if (!currentEmail.equals(inputEmail)) {
            throw new IllegalArgumentException(String.format("input email: %s, 이메일이 다릅니다.", inputEmail));
        }
    }

    private void validateEqualsPassword(final String currentPassword, final String inputPassword) {
        if (!currentPassword.equals(inputPassword)) {
            throw new IllegalArgumentException(String.format("input password: %s, 비밀번호가 다릅니다.", inputPassword));
        }
    }
}
