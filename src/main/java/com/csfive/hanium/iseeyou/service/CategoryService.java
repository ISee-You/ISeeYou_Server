package com.csfive.hanium.iseeyou.service;

import com.csfive.hanium.iseeyou.domain.category.Category;
import com.csfive.hanium.iseeyou.domain.category.CategoryRepository;
import com.csfive.hanium.iseeyou.domain.student.Student;
import com.csfive.hanium.iseeyou.domain.student.StudentRepository;
import com.csfive.hanium.iseeyou.dto.category.CategoryDetailReqDto;
import com.csfive.hanium.iseeyou.dto.category.CategoryDetailResDto;
import com.csfive.hanium.iseeyou.dto.category.CategoryUpdateReqDtp;
import com.csfive.hanium.iseeyou.utils.ErrorException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final StudentRepository studentRepository;

    public void create(Long id) throws ErrorException{  //학생이 회원가입했을때 category도 추가(test용)
        Student student = studentRepository.findById(id)
                .orElseThrow(()->new ErrorException("해당 학생이없습니다"));
        Category category = Category.builder()
                .student(student)
                .build();
        categoryRepository.save(category);

    }

    public void updateCategory(Long student_id, CategoryUpdateReqDtp categoryUpdateReqDtp) throws ErrorException{ //AI서버에서 category부분 업데이트해주는 부분(test용)
        Student student = studentRepository.findById(student_id)
                .orElseThrow(()->new ErrorException("해당 학생이없습니다"));
        Category category = categoryRepository.findByStudent(student)
                .orElseThrow(()->new ErrorException(String.format("유효하지 않은 접근입니다.(student_id로 category 찾을수 없음)")));


        category.update(categoryUpdateReqDtp.getYear(),categoryUpdateReqDtp.getMonth(),categoryUpdateReqDtp.getDay(),categoryUpdateReqDtp.getTime_pen(),categoryUpdateReqDtp.getTime_book(),categoryUpdateReqDtp.getTime_laptop());
    }

    public CategoryDetailResDto findCategory(Long student_id, CategoryDetailReqDto categoryDetailReqDto) throws ErrorException {
        checkStudent(student_id);

        Category category = categoryRepository.findByYearAndMonthAndDay(categoryDetailReqDto.getYear(), categoryDetailReqDto.getMonth(), categoryDetailReqDto.getDay());
        if (category == null) {
            throw new ErrorException(String.format("해당 일의 category가 존재하지 않습니다"));
        } else {
            CategoryDetailResDto categoryDetailResDto = new CategoryDetailResDto(category);

            return categoryDetailResDto;
        }
    }

    public void checkStudent(Long student_id)throws ErrorException{
        studentRepository.findById(student_id)
                .orElseThrow(() -> new ErrorException("잘못된 학생id번호로의 접근입니다"));
    }
}
