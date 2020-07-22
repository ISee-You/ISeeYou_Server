package com.csfive.hanium.iseeyou.service;

import com.csfive.hanium.iseeyou.domain.student.Student;
import com.csfive.hanium.iseeyou.domain.student.StudentRepository;
import com.csfive.hanium.iseeyou.dto.student.StudentFindResDto;
import com.csfive.hanium.iseeyou.dto.student.StudentSaveReqDto;
import com.csfive.hanium.iseeyou.dto.student.StudentUpdateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static com.csfive.hanium.iseeyou.utils.ResponseMessage.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepository repository;

    @Transactional
    public ResponseEntity<String> save(final StudentSaveReqDto saveDto) {
        Student student = Student.builder()
                .name(saveDto.getName())
                .email(saveDto.getEmail())
                .password(saveDto.getPassword())
                .handType(saveDto.getHandType())
                .genderType(saveDto.getGenderType())
                .build();

        repository.save(student);

        return new ResponseEntity<>(CREATE_USER, CREATED);
    }

    @Transactional
    public ResponseEntity<String> update(final Long id, final StudentUpdateReqDto updateDto) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("id: %d, 존재하지 않는 아이디 입니다.", id)));

        student.setName(updateDto.getName());
        student.setEmail(updateDto.getEmail());
        student.setPassword(updateDto.getPassword());
        student.setHandType(updateDto.getHandType());
        student.setGenderType(updateDto.getGenderType());

        return new ResponseEntity<>(UPDATE_USER, OK);
    }

    @Transactional
    public ResponseEntity<String> delete(final Long id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("id: %d, 존재하지 않는 아이디 입니다.", id)));

        repository.delete(student);

        return new ResponseEntity<>(DELETE_USER, OK);
    }


    public ResponseEntity<StudentFindResDto> find(final Long id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("id: %d, 존재하지 않는 아이디 입니다.", id)));

        StudentFindResDto resDto = new StudentFindResDto(student);
        return new ResponseEntity<>(resDto, OK);
    }
}
