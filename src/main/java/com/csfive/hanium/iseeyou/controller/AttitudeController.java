package com.csfive.hanium.iseeyou.controller;

import com.csfive.hanium.iseeyou.domain.attitude.Attitude;
import com.csfive.hanium.iseeyou.dto.attitude.AttitudeDto;
import com.csfive.hanium.iseeyou.dto.attitude.AttitudeResult;
import com.csfive.hanium.iseeyou.service.AttitudeService;
import com.csfive.hanium.iseeyou.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.csfive.hanium.iseeyou.utils.ResponseMessage.*;
import static com.csfive.hanium.iseeyou.utils.StatusCode.BAD_REQUEST;
import static com.csfive.hanium.iseeyou.utils.StatusCode.OK;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/attitudes")
@RequiredArgsConstructor
public class AttitudeController {

    private final AttitudeService attitudeService;

    @PostMapping("/init/{studentId}")
    public ResponseEntity initSaveWithStudent(@PathVariable(name = "studentId") Long studentId) {
        try {
            Long attitudeId = attitudeService.initSaveWithStudent(studentId);
            return ResponseEntity.ok(DefaultResponse.res(OK, SAVE_SUCCESS, attitudeId));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body(DefaultResponse.res(BAD_REQUEST, SAVE_FAIL));
        }
    }

    @PostMapping("/{studentId}")
    public ResponseEntity saveWithStudentAndPoses(@PathVariable(name = "studentId") Long studentId,
                                                  @RequestBody final AttitudeResult attitudeResults) {
        try {
            final Long attitudeId = attitudeService.saveWithStudentAndPoses(studentId, attitudeResults);
            return ResponseEntity.ok(DefaultResponse.res(OK, SAVE_SUCCESS, attitudeId));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body(DefaultResponse.res(BAD_REQUEST, SAVE_FAIL));
        }
    }

    @PutMapping("/{attitudeId}/{poseId}")
    public ResponseEntity plusCountOfPose(@PathVariable(name = "attitudeId") Long attitudeId,
                                          @PathVariable(name = "poseId") Long poseId) {
        try {
            attitudeService.plusCountOfPose(attitudeId, poseId);
            return ResponseEntity.ok(DefaultResponse.res(OK, UPDATE_SUCCESS));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body(DefaultResponse.res(BAD_REQUEST, UPDATE_FAIL));
        }
    }

    @GetMapping("/{attitudeId}/students/{studentId}")
    public ResponseEntity findOneByStudent(@PathVariable("attitudeId") Long attitudeId,
                                           @PathVariable("studentId") Long studentId) {
        try {
            final Attitude attitude = attitudeService.findOneByStudent(attitudeId, studentId);
            AttitudeDto attitudeDto = new AttitudeDto(attitude);

            return ResponseEntity.ok(DefaultResponse.res(OK, FIND_ATTITUDE, attitudeDto));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body(DefaultResponse.res(BAD_REQUEST, NOT_FOUND_ATTITUDE));
        }
    }
}
