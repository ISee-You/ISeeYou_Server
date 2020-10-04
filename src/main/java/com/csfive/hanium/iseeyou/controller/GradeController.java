package com.csfive.hanium.iseeyou.controller;

import com.csfive.hanium.iseeyou.domain.grade.Grade;
import com.csfive.hanium.iseeyou.domain.grade.GradeRepository;
import com.csfive.hanium.iseeyou.dto.grade.GradeDto;
import com.csfive.hanium.iseeyou.dto.grade.GradeRequest;
import com.csfive.hanium.iseeyou.service.GradeService;
import com.csfive.hanium.iseeyou.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.csfive.hanium.iseeyou.utils.ResponseMessage.*;
import static com.csfive.hanium.iseeyou.utils.StatusCode.BAD_REQUEST;
import static com.csfive.hanium.iseeyou.utils.StatusCode.OK;

@Slf4j
@RestController
@RequestMapping("/api/v1/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;
    private final GradeRepository gradeRepository;

    @GetMapping("/{gradeId}")
    public ResponseEntity findOne(@PathVariable("gradeId") Long gradeId) {
        try {
            final Long findGradeId = gradeService.findOne(gradeId);
            GradeDto gradeDto = gradeRepository.findGradeDtoById(findGradeId);

            return ResponseEntity.ok(DefaultResponse.res(OK, FIND_GRADE, gradeDto));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body(DefaultResponse.res(BAD_REQUEST, NOT_FOUND_GRADE));
        }
    }

    @GetMapping
    public ResponseEntity findAll() {
        try {
            final List<Grade> grades = gradeService.findAll();
            List<GradeDto> gradeDtos = grades.stream()
                    .map(GradeDto::new)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(DefaultResponse.res(OK, FIND_GRADE, gradeDtos));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body(DefaultResponse.res(BAD_REQUEST, NOT_FOUND_GRADE));
        }
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity findAllByStudent(@PathVariable("studentId") Long studentId) {
        try {
            final List<Grade> grades = gradeService.findAllByStudent(studentId);
            List<GradeDto> gradeDtos = grades.stream()
                    .map(GradeDto::new)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(DefaultResponse.res(OK, FIND_GRADE, gradeDtos));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body(DefaultResponse.res(BAD_REQUEST, NOT_FOUND_GRADE));
        }
    }

    @PostMapping("/students/{studentId}")
    public ResponseEntity saveWithStudent(@RequestBody GradeRequest gradeRequest,
                                          @PathVariable("studentId") Long studentId) {
        try {
            Long gradeId = gradeService.saveWithStudent(gradeRequest, studentId);
            GradeDto gradeDto = gradeRepository.findGradeDtoById(gradeId);

            return ResponseEntity.ok(DefaultResponse.res(OK, SAVE_SUCCESS, gradeDto));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body(DefaultResponse.res(BAD_REQUEST, SAVE_FAIL));
        }
    }

    @PutMapping("/{gradeId}")
    public ResponseEntity update(@PathVariable("gradeId") Long gradeId,
                                 @RequestBody GradeRequest gradeRequest) {
        try {
            gradeService.update(gradeId, gradeRequest);
            final GradeDto gradeDto = gradeRepository.findGradeDtoById(gradeId);

            return ResponseEntity.ok(DefaultResponse.res(OK, UPDATE_GRADE_SUCCESS, gradeDto));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body(DefaultResponse.res(BAD_REQUEST, UPDATE_GRADE_FAIL));
        }
    }

    @DeleteMapping("/{gradeId}")
    public ResponseEntity deleteOne(@PathVariable("gradeId") Long gradeId){
        try {
            gradeService.delete(gradeId);
            return ResponseEntity.ok(DefaultResponse.res(OK, DELETE_GRADE_SUCCESS));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body(DefaultResponse.res(BAD_REQUEST, DELETE_GRADE_FAIL));
        }
    }

    @DeleteMapping("students/{studentId}")
    public ResponseEntity deleteAllByStudent(@PathVariable("studentId") Long studentId){
        try {
            gradeService.deleteAllByStudent(studentId);
            return ResponseEntity.ok(DefaultResponse.res(OK, DELETE_SUCCESS));
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(DefaultResponse.res(BAD_REQUEST, DELETE_GRADE_FAIL));
        }
    }
}
