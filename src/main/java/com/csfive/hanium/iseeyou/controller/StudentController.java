package com.csfive.hanium.iseeyou.controller;

import com.csfive.hanium.iseeyou.domain.student.Student;
import com.csfive.hanium.iseeyou.dto.parent.ParentEmailDto;
import com.csfive.hanium.iseeyou.dto.parent.ParentNameAndEmailDto;
import com.csfive.hanium.iseeyou.dto.student.*;
import com.csfive.hanium.iseeyou.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;

import static com.csfive.hanium.iseeyou.utils.ResponseMessage.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/stduents")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody final StudentSaveReqDto saveDto) {
        studentService.save(saveDto);

        return ResponseEntity.ok(CREATE_USER);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> update(@PathVariable("userId") Long id, @RequestBody final StudentUpdateReqDto updateDto) {
        studentService.update(id, updateDto);

        return ResponseEntity.ok(UPDATE_USER);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> delete(@PathVariable("userId") Long id) {
        studentService.delete(id);

        return ResponseEntity.ok(DELETE_USER);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<StudentFindResDto> findBy(@PathVariable("userId") Long id) {
        StudentFindResDto resDto = studentService.find(id);

        return ResponseEntity.ok(resDto);
    }

    @PostMapping("/login")
    public ResponseEntity<StudentFindResDto> login(@RequestBody final StudentLoginReqDto loginReqDto) {
        Student student = studentService.login(loginReqDto);
        StudentFindResDto studentFindResDto = studentService.find(student.getId());
        if (student == null) {
            throw new NoResultException("없는 사용자입니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentFindResDto);
    }

    @PostMapping("{userId}/parent")
    public ResponseEntity<String> registerTo(@PathVariable("userId") Long id,
                                             @RequestBody final ParentNameAndEmailDto parentNameAndEmailDto) {
        try {
            studentService.registerTo(id, parentNameAndEmailDto);
            return ResponseEntity.ok(REGISTER_SUCCESS);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(REGISTER_FAIL);
        }
    }

    @DeleteMapping("{userId}/parent")
    public ResponseEntity<String> deleteTo(@PathVariable("userId") Long id,
                                           @RequestBody final ParentEmailDto parentEmailDto) {
        try {
            studentService.deleteTo(id, parentEmailDto);
            return ResponseEntity.ok(DELETE_USER);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(DELETE_FAIL);
        }
    }
}
