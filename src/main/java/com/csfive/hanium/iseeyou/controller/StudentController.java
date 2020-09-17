package com.csfive.hanium.iseeyou.controller;

import com.csfive.hanium.iseeyou.dto.student.*;
import com.csfive.hanium.iseeyou.service.StudentService;
import com.csfive.hanium.iseeyou.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.csfive.hanium.iseeyou.utils.ResponseMessage.*;
import static com.csfive.hanium.iseeyou.utils.StatusCode.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity save(@RequestBody final StudentSaveRequest saveRequest) {
        try {
            Long studentId = studentService.save(saveRequest);
            return ResponseEntity.ok(DefaultResponse.res(OK, CREATE_USER, studentId));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body(DefaultResponse.res(BAD_REQUEST, NOT_CREATE_USER));
        }
    }

    @PutMapping("/{studentId}")
    public ResponseEntity update(@PathVariable("studentId") Long studentId,
                                 @RequestBody final StudentUpdateRequest updateRequest) {
        try {
            studentService.update(studentId, updateRequest);
            return ResponseEntity.ok(DefaultResponse.res(OK, UPDATE_USER));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body(DefaultResponse.res(BAD_REQUEST, UPDATE_FAIL_USER));
        }
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity delete(@PathVariable("studentId") final Long studentId) {
        try {
            studentService.delete(studentId);
            return ResponseEntity.ok(DefaultResponse.res(OK, DELETE_USER));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body(DefaultResponse.res(BAD_REQUEST, DELETE_FAIL));
        }
    }

    @GetMapping("/{studentId}")
    public ResponseEntity find(@PathVariable("studentId") final Long studentId) {
        try {
            Long findStudentId = studentService.find(studentId);
            return ResponseEntity.ok(DefaultResponse.res(OK, FIND_USER, findStudentId));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body(DefaultResponse.res(BAD_REQUEST, NOT_FOUND_USER));
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody final StudentLoginRequest loginRequest) {
        try {
            Long studentId = studentService.login(loginRequest);
            return ResponseEntity.ok(DefaultResponse.res(OK, LOGIN_SUCCESS, studentId));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body(DefaultResponse.res(BAD_REQUEST, LOGIN_FAIL));
        }

    }

    @PostMapping("{studentId}/{parentId}")
    public ResponseEntity registerParent(@PathVariable("studentId") final Long studentId,
                                         @PathVariable("parentId") final Long parentId) {
        try {
            studentService.registerParent(studentId, parentId);
            return ResponseEntity.ok(DefaultResponse.res(OK, REGISTER_SUCCESS));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body(DefaultResponse.res(BAD_REQUEST, REGISTER_FAIL));
        }
    }

    @DeleteMapping("{studentId}/{parentId}")
    public ResponseEntity deleteParent(@PathVariable("studentId") final Long studentId,
                                       @PathVariable("parentId") final Long parentId) {
        try {
            studentService.deleteParent(studentId, parentId);
            return ResponseEntity.ok(DefaultResponse.res(OK, DELETE_SUCCESS));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body(DefaultResponse.res(BAD_REQUEST, DELETE_FAIL));
        }
    }
}
