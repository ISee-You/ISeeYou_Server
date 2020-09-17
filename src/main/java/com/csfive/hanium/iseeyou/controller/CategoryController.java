package com.csfive.hanium.iseeyou.controller;

import com.csfive.hanium.iseeyou.dto.category.CategoryDetailReqDto;
import com.csfive.hanium.iseeyou.dto.category.CategoryDetailResDto;
import com.csfive.hanium.iseeyou.dto.category.CategoryUpdateReqDtp;
import com.csfive.hanium.iseeyou.service.CategoryService;
import com.csfive.hanium.iseeyou.exception.ErrorException;
import com.csfive.hanium.iseeyou.utils.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/{student_id}/category}") //student의 save메소드 발생시 categorty생성
    public ResponseEntity dontUse_testingCreateCategory(@PathVariable("student_id") Long id){
        try{
            System.out.println("controller : "+id);
            categoryService.create(id);
            return ResponseEntity.ok("category 생성");
        }catch (ErrorException e){
            return ResponseEntity.status(e.getERR_CODE()).body(e.getMessage());
        }

    }

    @PutMapping("/{student_id}/category")
    public ResponseEntity dontUse_testingupdate(@PathVariable("student_id")Long id, @RequestBody CategoryUpdateReqDtp categoryUpdateReqDtp){
        try{
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            categoryUpdateReqDtp.setYear(year);
            categoryUpdateReqDtp.setMonth(month);
            categoryUpdateReqDtp.setDay(day);

            categoryService.updateCategory(id,categoryUpdateReqDtp);
            return ResponseEntity.ok(ResponseMessage.UPDATE_SUCCESS);
        }catch (ErrorException e){
            return ResponseEntity.status(e.getERR_CODE()).body(e.getMessage());
        }
    }

    @PostMapping("/{student_id}/category")
    public ResponseEntity findCategory(@PathVariable("student_id")Long id, @RequestBody CategoryDetailReqDto categoryDetailReqDto){
        try{
            CategoryDetailResDto categoryDetailResDto = categoryService.findCategory(id, categoryDetailReqDto);
            return ResponseEntity.ok(categoryDetailResDto);
        }catch (ErrorException e){
            return ResponseEntity.status(e.getERR_CODE()).body(e.getMessage());
        }
    }
}
