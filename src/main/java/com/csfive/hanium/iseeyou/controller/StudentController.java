package com.csfive.hanium.iseeyou.controller;

import com.csfive.hanium.iseeyou.dto.student.StudentFindResDto;
import com.csfive.hanium.iseeyou.dto.student.StudentSaveReqDto;
import com.csfive.hanium.iseeyou.dto.student.StudentUpdateReqDto;
import com.csfive.hanium.iseeyou.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/stduents")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity save(@RequestBody final StudentSaveReqDto saveDto){
        return studentService.save(saveDto);
    }

    @PutMapping("/{userId}")
    public ResponseEntity update(@PathVariable("userId") Long id, @RequestBody final StudentUpdateReqDto updateDto){
        return studentService.update(id, updateDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity delete(@PathVariable("userId") Long id){
        return studentService.delete(id);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<StudentFindResDto> findBy(@PathVariable("userId") Long id){
        return studentService.find(id);
    }
}
