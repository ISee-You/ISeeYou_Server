package com.csfive.hanium.iseeyou.controller;

import com.csfive.hanium.iseeyou.domain.category.ProficiencyTime;
import com.csfive.hanium.iseeyou.dto.category.CategoryDetailReqDto;
import com.csfive.hanium.iseeyou.dto.category.CategoryDetailResDto;
import com.csfive.hanium.iseeyou.service.CategoryService;
import com.csfive.hanium.iseeyou.utils.DefaultResponse;
import com.csfive.hanium.iseeyou.utils.ErrorException;
import static com.csfive.hanium.iseeyou.utils.ResponseMessage.*;
import static com.csfive.hanium.iseeyou.utils.StatusCode.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/{student_id}/category")
    public ResponseEntity findCategory(@PathVariable("student_id")Long id, @RequestBody CategoryDetailReqDto categoryDetailReqDto){
        try{
            List<CategoryDetailResDto> categoryList = categoryService.findCategory(id,categoryDetailReqDto);
            return ResponseEntity.ok(DefaultResponse.res(OK, FIND_CATEGORY,categoryList));
        }catch (ErrorException e){
            return ResponseEntity.status(e.getERR_CODE()).body(DefaultResponse.res(e.getERR_CODE(),e.getMessage()));
        }
    }

    @GetMapping("/{student_id}/category")
    public ResponseEntity proficiencyCategory(@PathVariable("student_id")Long id){
        try{
            List<ProficiencyTime> categoryProficiencyResDtoList= categoryService.categoryProficiency(id);
            return ResponseEntity.ok(DefaultResponse.res(OK,FIND_CATEGORY,categoryProficiencyResDtoList));
        }catch (ErrorException e){
            return ResponseEntity.status(e.getERR_CODE()).body(DefaultResponse.res(e.getERR_CODE(),e.getMessage()));
        }
    }
}
