package com.csfive.hanium.iseeyou.controller;

import com.csfive.hanium.iseeyou.dto.parent.*;
import com.csfive.hanium.iseeyou.dto.student.StudentDetailResDto;
import com.csfive.hanium.iseeyou.service.ParentService;
import com.csfive.hanium.iseeyou.service.ValidateService;
import com.csfive.hanium.iseeyou.utils.DefaultResponse;
import com.csfive.hanium.iseeyou.utils.ErrorException;
import com.csfive.hanium.iseeyou.utils.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.csfive.hanium.iseeyou.utils.ResponseMessage.*;
import static com.csfive.hanium.iseeyou.utils.StatusCode.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/parents")
@RestController
public class ParentController {

    private final ParentService parentService;
    private final ValidateService validateService;

    @PostMapping("/")
    public ResponseEntity signup(@RequestBody @Valid ParentSavetRequestDto saveRequestDto, Errors errors, Model model){
        try{
            if(errors.hasErrors()){
                model.addAttribute("saveRequestDto",saveRequestDto);

                Map<String, String> validatorResult = validateService.validateHandling(errors);
//                for(String key : validatorResult.keySet()){
//                    model.addAttribute(key, validatorResult.get(key));
//                }
                return ResponseEntity.status(StatusCode.BAD_REQUEST).body(validatorResult);
            }

            long parent_id = parentService.signup(saveRequestDto);
            return ResponseEntity.ok(DefaultResponse.res(OK,CREATE_USER,parent_id));
        }catch (Exception e){
            return ResponseEntity.status(StatusCode.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PostMapping("/Login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto){
        try{
            ParentLoginResDto parentDetail = parentService.login(loginRequestDto);
            return ResponseEntity.ok(DefaultResponse.res(OK,LOGIN_SUCCESS,parentDetail));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(StatusCode.BAD_REQUEST).body(DefaultResponse.res(BAD_REQUEST,e.getMessage()));
        }
    }

    @PostMapping("/{parentId}/Registration")
    public ResponseEntity studentRegistration(@PathVariable("parentId") Long parentId,@RequestBody StudentRegistrationReqDto studentRegistrationReqDto){
        try{
            Long reqStudent_id = parentService.studentRegistration(parentId, studentRegistrationReqDto);
            return ResponseEntity.ok(DefaultResponse.res(OK,SAVE_SUCCESS,reqStudent_id));
        }catch (ErrorException e){
            return ResponseEntity.status(e.getERR_CODE()).body(DefaultResponse.res(e.getERR_CODE(),e.getMessage()));
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(StatusCode.BAD_REQUEST).body(DefaultResponse.res(BAD_REQUEST,e.getMessage()));
        }
    }

    @DeleteMapping("/{parentId}/DeletStudent")
    public ResponseEntity deleteStudent(@PathVariable("parentId") Long parentId, @RequestBody ParentDeleteStudentReqDto parentDeleteStudentReqDto){
        try{
            parentService.deleteStudent(parentId, parentDeleteStudentReqDto);
            return ResponseEntity.ok(DefaultResponse.res(OK,DELETE_USER));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(StatusCode.BAD_REQUEST).body(DefaultResponse.res(BAD_REQUEST,e.getMessage()));
        }
    }

    @GetMapping("/{parentId}/Students")
    public ResponseEntity findStudent(@PathVariable("parentId")Long id){
        try{
            List<StudentDetailResDto> studentList = parentService.findStudents(id);
            return ResponseEntity.ok(DefaultResponse.res(OK,FIND_USER,studentList));
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(StatusCode.BAD_REQUEST).body(DefaultResponse.res(BAD_REQUEST,e.getMessage()));
        }catch (ErrorException e){
            return ResponseEntity.status(e.getERR_CODE()).body(DefaultResponse.res(e.getERR_CODE(),e.getMessage()));
        }
    }

    @PutMapping("/{parentId}")
    public ResponseEntity update(@PathVariable("parentId") Long id, @RequestBody ParentUpdateRequestDto updateRequestDto){
        try{
            Long studentid = parentService.update(id, updateRequestDto);
            return ResponseEntity.ok(DefaultResponse.res(OK,UDATE_SUCCESS,studentid));
        }catch (Exception e){
            return ResponseEntity.status(StatusCode.BAD_REQUEST).body(DefaultResponse.res(BAD_REQUEST,e.getMessage()));
        }
    }
}
