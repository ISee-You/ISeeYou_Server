package com.csfive.hanium.iseeyou.service;

import com.csfive.hanium.iseeyou.domain.parent.Parent;
import com.csfive.hanium.iseeyou.domain.parent.ParentRepository;
import com.csfive.hanium.iseeyou.domain.student.Student;
import com.csfive.hanium.iseeyou.domain.student.StudentRepository;
import com.csfive.hanium.iseeyou.dto.parent.*;
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

    public Long signup(ParentSavetRequestDto requestDto) throws ErrorException{
        Parent parent = parentRepository.findByEmail(requestDto.getEmail());
        System.out.println(">>>>>>> parent result :"+parent);
        if(parent==null){
            return parentRepository.save(requestDto.toEntity()).getId();
        }else{
            throw new ErrorException(String.format("존재하는 Email입니다"));
        }
    }

    public long studentRegistration(Long parentId, Long studentId) throws IllegalArgumentException,ErrorException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()->new IllegalArgumentException(String.format("존재하지않는 학생 ID(%d) 입니다",studentId)));
        Parent parent = findParent(parentId);

        duplicateStudent(parent, student);
        parent.addStudent(student);
        return student.getId();
    }

    public void deleteStudent(Long parentId, ParentDeleteStudentReqDto parentDeleteStudentReqDto)throws IllegalArgumentException{
        Student student = studentRepository.findByEmail(parentDeleteStudentReqDto.getEmail())
                .orElseThrow(()-> new IllegalArgumentException(String.format("존재하지 않는 Email : %s 입니다.",parentDeleteStudentReqDto.getEmail())));
        Parent parent = findParent(parentId);
        parent.deleteStudent(student);
    }

    public Long update(Long parentId, ParentUpdateRequestDto updateRequestDto)throws IllegalArgumentException{
        Parent parent = findParent(parentId);
        parent.update(updateRequestDto.getName(),updateRequestDto.getPassword(), updateRequestDto.getEmail(),updateRequestDto.getGender());

        return parent.getId();
    }

    public List<StudentDetailResDto> findStudents(Long parentId) throws IllegalArgumentException,ErrorException{
        Parent parent = findParent(parentId);
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

    private void duplicateStudent(Parent parent,Student reqStudent) throws ErrorException{
        for(Student student : parent.getStudents()){
            if(student.getId().equals(reqStudent.getId())){
                throw new ErrorException(String.format("이미 존재하는 학생입니다. (%s)",reqStudent.getName()));
            }
        }
    }

    private Parent findParent(long parentId) throws IllegalArgumentException{
        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(()->new IllegalArgumentException(String.format("존재하지 않는 사용자입니다 ID : %d",parentId)));
        return parent;
    }
}
