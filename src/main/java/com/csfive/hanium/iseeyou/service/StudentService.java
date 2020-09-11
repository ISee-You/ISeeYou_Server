package com.csfive.hanium.iseeyou.service;

import com.csfive.hanium.iseeyou.domain.parent.Parent;
import com.csfive.hanium.iseeyou.domain.parent.ParentRepository;
import com.csfive.hanium.iseeyou.domain.student.Student;
import com.csfive.hanium.iseeyou.domain.student.StudentRepository;
import com.csfive.hanium.iseeyou.dto.parent.ParentEmailDto;
import com.csfive.hanium.iseeyou.dto.parent.ParentNameAndEmailDto;
import com.csfive.hanium.iseeyou.dto.student.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;

    @Transactional
    public void save(final StudentSaveReqDto saveDto) {
        Student student = Student.builder()
                .name(saveDto.getName())
                .email(saveDto.getEmail())
                .password(saveDto.getPassword())
                .handType(saveDto.getHandType())
                .gender(saveDto.getGenderType())
                .build();

        studentRepository.save(student);
    }

    @Transactional
    public void update(final Long id, final StudentUpdateReqDto updateDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("id: %d, 존재하지 않는 아이디 입니다.", id)));

        student.setName(updateDto.getName());
        student.setEmail(updateDto.getEmail());
        student.setPassword(updateDto.getPassword());
        student.setHandType(updateDto.getHandType());
        student.setGender(updateDto.getGenderType());
    }

    @Transactional
    public void delete(final Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("id: %d, 존재하지 않는 아이디 입니다.", id)));

        studentRepository.delete(student);
    }

    public StudentFindResDto find(final Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("id: %d, 존재하지 않는 아이디 입니다.", id)));

        StudentFindResDto resDto = new StudentFindResDto(student);
        return resDto;
    }

    public Student login(final StudentLoginReqDto loginReqDto) {
        return studentRepository.findByEmailAndPassword(loginReqDto.getEmail(), loginReqDto.getPassword());
    }

    @Transactional
    public void registerTo(final Long id, final ParentNameAndEmailDto parentDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("id: %d, 존재하지 않는 아이디 입니다.", id)));
        
        Parent parent = parentRepository.findByNameAndEmail(parentDto.getName(), parentDto.getEmail());

        student.changeParent(parent);
    }

    @Transactional
    public void deleteTo(final Long id, final ParentEmailDto parentDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("id: %d, 존재하지 않는 아이디 입니다.", id)));
        
        Parent parent = parentRepository.findByEmail(parentDto.getEmail());
        
        student.deleteTo(parent);
    }
}
