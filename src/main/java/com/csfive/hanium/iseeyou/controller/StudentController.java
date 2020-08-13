package com.csfive.hanium.iseeyou.controller;

import com.csfive.hanium.iseeyou.domain.student.Student;
import com.csfive.hanium.iseeyou.dto.student.StudentFindResDto;
import com.csfive.hanium.iseeyou.dto.student.StudentLoginReqDto;
import com.csfive.hanium.iseeyou.dto.student.StudentSaveReqDto;
import com.csfive.hanium.iseeyou.dto.student.StudentUpdateReqDto;
import com.csfive.hanium.iseeyou.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> login(@RequestBody final StudentLoginReqDto loginReqDto) {
        Student student = studentService.login(loginReqDto);
        if (student == null) {
            return ResponseEntity.badRequest().body(LOGIN_FAIL);
        }
        return ResponseEntity.ok(LOGIN_SUCCESS);
    }
}
