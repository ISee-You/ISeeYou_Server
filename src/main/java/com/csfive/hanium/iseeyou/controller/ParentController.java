package com.csfive.hanium.iseeyou.controller;

import com.csfive.hanium.iseeyou.dto.parent.*;
import com.csfive.hanium.iseeyou.dto.student.StudentDetailResDto;
import com.csfive.hanium.iseeyou.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.csfive.hanium.iseeyou.utils.ResponseMessage.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/parents")
@RestController
public class ParentController {

    private final ParentService parentService;

    @PostMapping("/")
    public ResponseEntity<String> signup(@RequestBody ParentSavetRequestDto saveRequestDto){
        parentService.signup(saveRequestDto);
        return ResponseEntity.ok(CREATE_USER);
    }

    @PostMapping("/Login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto){
        Long id = parentService.login(loginRequestDto);

        return ResponseEntity.ok(LOGIN_SUCCESS);
    }

    @PostMapping("/{parentId}/Registration")
    public ResponseEntity<String> studentRegistration(@PathVariable("parentId") Long parentId,@RequestBody StudentRegistrationReqDto studentRegistrationReqDto){
        parentService.studentRegistration(parentId, studentRegistrationReqDto);
        return ResponseEntity.ok(SAVE_SUCCESS);
    }

    @DeleteMapping("/{parentId}/DeletStudent")
    public ResponseEntity<String> deleteStudent(@PathVariable("parentId") Long parentId, @RequestBody ParentDeleteStudentReqDto parentDeleteStudentReqDto){
        parentService.deleteStudent(parentId, parentDeleteStudentReqDto);
        return ResponseEntity.ok(DELETE_USER);
    }

    @GetMapping("/{parentId}/Students")
    public ResponseEntity<List<StudentDetailResDto>> findStudent(@PathVariable("parentId")Long id){
        List<StudentDetailResDto> studentList = parentService.findStudents(id);

        return ResponseEntity.ok(studentList);
    }

    @PutMapping("/{parentId}")
    public ResponseEntity<Long> update(@PathVariable("parentId") Long id, @RequestBody ParentUpdateRequestDto updateRequestDto){
        Long studentid = parentService.update(id, updateRequestDto);
        return ResponseEntity.ok(studentid);
    }

    @DeleteMapping("/{parentId}")
    public ResponseEntity<String> delete(@PathVariable("parentId") Long id){
        parentService.delete(id);
        return ResponseEntity.ok(DELETE_USER);
    }

}
