package com.csfive.hanium.iseeyou.service;

import com.csfive.hanium.iseeyou.domain.category.Category;
import com.csfive.hanium.iseeyou.domain.category.CategoryRepository;
import com.csfive.hanium.iseeyou.domain.category.CategoryTimes;
import com.csfive.hanium.iseeyou.domain.student.Student;
import com.csfive.hanium.iseeyou.domain.student.StudentRepository;
import com.csfive.hanium.iseeyou.dto.category.CategoryDetailReqDto;
import com.csfive.hanium.iseeyou.dto.category.CategoryDetailResDto;
import com.csfive.hanium.iseeyou.domain.category.ProficiencyTime;
import com.csfive.hanium.iseeyou.utils.ErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final StudentRepository studentRepository;

    public List<CategoryDetailResDto> findCategory(Long student_id, CategoryDetailReqDto categoryDetailReqDto) throws ErrorException {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        log.info(">>>testLocalDate : "+date);
        log.info(">>>testgetDayofYear : "+date.getDayOfYear());
        log.info(">>>testgetDayofMomth : "+date.getDayOfMonth());
        log.info(">>>testgetDayofWeek : "+date.getDayOfWeek());
        log.info(">>>testgetMonth : "+date.getMonth());
        log.info(">>>testLocalTime : "+time);
        log.info(">>>testLocalDateTime : "+localDateTime);

        Student student = findStudent(student_id);
        List<Category> categoryList = categoryRepository.findByYearAndMonthAndDay(categoryDetailReqDto.getYear(), categoryDetailReqDto.getMonth(), categoryDetailReqDto.getDay(),student);
        if (categoryList.isEmpty()) {
            throw new ErrorException(String.format("해당 일의 category가 존재하지 않습니다"));
        } else {
            List<CategoryDetailResDto> resDtos = new ArrayList<>();
            for(Category category : categoryList){
                resDtos.add(new CategoryDetailResDto(category));
            }
            return resDtos;

//            List categoryDetailResDtos = categoryList.stream()
//                    .map(CategoryDetailResDto::new)
//                    .collect(Collectors.toList());
        }
    }

    public List<ProficiencyTime> categoryProficiency(Long student_id) throws ErrorException {
        Student student = findStudent(student_id);
        List<Category> categoryList = categoryRepository.findByStudent(student);
        validateEmpty(categoryList);

        CategoryTimes categoryTimes = createCategoryTimes(categoryList);
        List<ProficiencyTime> convertTimeList = categoryTimes.convertTime();
        return convertTimeList;
    }

    private CategoryTimes createCategoryTimes(List<Category> categoryList) {
        long penTime = 0;
        long bookTime = 0;
        long laptopTime = 0;

        for(Category category : categoryList){
            penTime += category.getPenTime();
            bookTime += category.getBookTime();
            laptopTime += category.getLaptopTime();
        }
        log.info(">>>>> pentime : {}",penTime);
        log.info(">>>>> booktime : {}",bookTime);
        log.info(">>>>> laptoptime : {}",laptopTime);

        return new CategoryTimes(penTime, bookTime, laptopTime);
    }

    private void validateEmpty(List<Category> list) throws ErrorException{
        if(list.isEmpty()){
            throw new ErrorException("학생의 카테고리 정보가 존재하지 않습니다");
        }
    }

    private Student findStudent(Long student_id)throws ErrorException{
        Student student = studentRepository.findById(student_id)
                .orElseThrow(() -> new ErrorException("잘못된 학생id번호로의 접근입니다"));
        return student;
    }
}
