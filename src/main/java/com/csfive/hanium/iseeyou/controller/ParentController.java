package com.csfive.hanium.iseeyou.controller;

import com.csfive.hanium.iseeyou.dto.parent.LoginRequestDto;
import com.csfive.hanium.iseeyou.dto.parent.ParentAddChildRequestDto;
import com.csfive.hanium.iseeyou.dto.parent.ParentUpdateRequestDto;
import com.csfive.hanium.iseeyou.dto.student.StudentAcceptanceReqDto;
import com.csfive.hanium.iseeyou.service.ParentService;
import com.csfive.hanium.iseeyou.dto.parent.ParentSavetRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.csfive.hanium.iseeyou.utils.ResponseMessage.LOGIN_SUCCESS;
import static com.csfive.hanium.iseeyou.utils.ResponseMessage.SAVE_SUCCESS;

@RequiredArgsConstructor
@RestController
public class ParentController {

    private final ParentService parentService;

    @PostMapping("/api/v1/parents")
    public ResponseEntity siginup(@RequestBody ParentSavetRequestDto saveRequestDto){
        System.out.println(saveRequestDto.getName());
        return parentService.save(saveRequestDto);
    }

    @PostMapping("/api/v1/parents/{parentId}/acceptance") //학생의 요청 수락
    public ResponseEntity acceptanceRequest(@PathVariable("parentId") Long parentId,@RequestBody StudentAcceptanceReqDto studentAcceptanceReqDto){
        parentService.accpetanceStudent(parentId, studentAcceptanceReqDto);
        return ResponseEntity.ok(SAVE_SUCCESS);
    }

//    @PostMapping("/api/v1/{parentId}")
//    public ResponseEntity addChild(@PathVariable("parentId") Long parentId, @RequestBody ParentAddChildRequestDto parentAddChildRequestDto){
//        System.out.println(">>>>>"+parentId);
//        System.out.println(parentAddChildRequestDto.getStudentName());
//        return parentService.addChild(parentId, parentAddChildRequestDto);
//    }

    @GetMapping("/api/v1/{parentId}/childs")
    public ResponseEntity findChilds(@PathVariable("parentId")Long id){
        return parentService.findChilds(id);
    }

    @PutMapping("/api/v1/{parentId}")
    public ResponseEntity update(@PathVariable("parentId") Long id, @RequestBody ParentUpdateRequestDto updateRequestDto){
        System.out.println(updateRequestDto.getEmail());
        return parentService.update(id, updateRequestDto);
    }

    @DeleteMapping("/api/v1/{parentId}")
    public ResponseEntity delete(@PathVariable("parentId") Long id){
        return parentService.delete(id);
    }

    @PostMapping("/api/v1/parents/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto){
        Long id = parentService.login(loginRequestDto);

        return ResponseEntity.ok(LOGIN_SUCCESS);
    }


}
