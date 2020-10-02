package com.csfive.hanium.iseeyou.controller;

import com.csfive.hanium.iseeyou.domain.grade.GradeRepository;
import com.csfive.hanium.iseeyou.dto.grade.GradeDto;
import com.csfive.hanium.iseeyou.dto.grade.GradeRequest;
import com.csfive.hanium.iseeyou.service.GradeService;
import com.csfive.hanium.iseeyou.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.csfive.hanium.iseeyou.utils.ResponseMessage.SAVE_FAIL;
import static com.csfive.hanium.iseeyou.utils.ResponseMessage.SAVE_SUCCESS;
import static com.csfive.hanium.iseeyou.utils.StatusCode.BAD_REQUEST;
import static com.csfive.hanium.iseeyou.utils.StatusCode.OK;

@Slf4j
@RestController
@RequestMapping("/api/v1/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;
    private final GradeRepository gradeRepository;

    @PostMapping("/students/{studentId}")
    public ResponseEntity saveWithStudent(@RequestBody GradeRequest gradeRequest,
                                          @PathVariable("studentId") Long studentId){
        try {
            Long gradeId = gradeService.saveWithStudent(gradeRequest, studentId);
            GradeDto gradeDto = gradeRepository.findGradeDtoById(gradeId);

            return ResponseEntity.ok(DefaultResponse.res(OK, SAVE_SUCCESS, gradeDto));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body(DefaultResponse.res(BAD_REQUEST, SAVE_FAIL));
        }
    }
}
