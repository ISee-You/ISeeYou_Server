package com.csfive.hanium.iseeyou.service;

import com.csfive.hanium.iseeyou.domain.parent.Parent;
import com.csfive.hanium.iseeyou.domain.parent.ParentRepository;
import com.csfive.hanium.iseeyou.domain.student.Student;
import com.csfive.hanium.iseeyou.domain.student.StudentRepository;
import com.csfive.hanium.iseeyou.dto.parent.*;
//import com.csfive.hanium.iseeyou.dto.parent.ParentAddChildRequestDto;
import com.csfive.hanium.iseeyou.dto.student.StudentDetailResDto;
import com.csfive.hanium.iseeyou.utils.ErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class ParentService {


    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;


    public void signup(ParentSavetRequestDto requestDto){
        parentRepository.save(requestDto.toEntity());
    }

    public void studentRegistration(Long parentId, StudentRegistrationReqDto studentRegistrationReqDto) throws IllegalArgumentException,ErrorException {
        String studentName = studentRegistrationReqDto.getName();
        String studentEmail = studentRegistrationReqDto.getEmail();

        Student student = studentRepository.findByNameAndEmail(studentName,studentEmail)
            .orElseThrow(()->new IllegalArgumentException(String.format("존재하지않는 Name : %s, 혹은 Email : %s",studentName,studentEmail)));
        Parent parent = parentRepository.findById(parentId)
            .orElseThrow(()->new IllegalArgumentException(String.format("존재하지 않는 사용자입니다 ID : %d",parentId)));

        if(checkStudent(studentRegistrationReqDto, parent)){
            parent.addStudent(student);
        }else{
            throw new ErrorException(String.format("이미 존재하는 학생입니다. (%s)",student.getName()));
        }
    }

    public void deleteStudent(Long parentId, ParentDeleteStudentReqDto parentDeleteStudentReqDto)throws IllegalArgumentException{
        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(()-> new IllegalArgumentException(String.format("존재하지 않는 회원번호 : %d 입니다. ", parentId)));
        Student student = studentRepository.findByEmail(parentDeleteStudentReqDto.getEmail())
                .orElseThrow(()-> new IllegalArgumentException(String.format("존재하지 않는 Email : %s 입니다.",parentDeleteStudentReqDto.getEmail())));
        parent.deleteStudent(student);
    }

    public Long update(Long parentId, ParentUpdateRequestDto updateRequestDto)throws IllegalArgumentException{
        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(()-> new IllegalArgumentException(String.format("존재하지않는 회원 번호 %d 입니다.",parentId)));
        parent.update(updateRequestDto.getName(),updateRequestDto.getPassword(), updateRequestDto.getEmail(),updateRequestDto.getGender());

        return parent.getId();
    }

    public List<StudentDetailResDto> findStudents(Long id) throws IllegalArgumentException,ErrorException{
        Parent parent = parentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(String.format("유효하지않는 회원 번호 %d 입니다. ", id)));
        List<StudentDetailResDto> studentDetailsResDtos = new ArrayList<>();
        for(Student student : parent.getStudents()){
            studentDetailsResDtos.add(new StudentDetailResDto(student));
        }
        if(studentDetailsResDtos.size() == 0){
            throw new ErrorException("자식이 추가되어있지 않습니다");
        }

        return studentDetailsResDtos;
    }

    public ParentLoginResDto login(LoginRequestDto loginRequestDto)throws IllegalArgumentException{
        String parentEmail = loginRequestDto.getEmail();
        String parentPW = loginRequestDto.getPassword();

        Parent parent = parentRepository.findByEmailAndPassword(parentEmail,parentPW)
                .orElseThrow(()->new IllegalArgumentException(String.format("존재하지 않는 ID,혹은 PW")));

        ParentLoginResDto parentLoginResDto = new ParentLoginResDto(parent);
        return parentLoginResDto;
    }

    public Boolean checkStudent(StudentRegistrationReqDto studentRegisterResDto, Parent parent){
        for(Student student : parent.getStudents()){
            if(student.getEmail().equals(studentRegisterResDto.getEmail())){
                return false;
            }
        }
        return true;
    }
}
