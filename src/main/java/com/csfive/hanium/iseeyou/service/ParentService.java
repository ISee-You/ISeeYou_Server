package com.csfive.hanium.iseeyou.service;

import com.csfive.hanium.iseeyou.domain.parent.Parent;
import com.csfive.hanium.iseeyou.domain.parent.ParentRepository;
import com.csfive.hanium.iseeyou.domain.student.Student;
import com.csfive.hanium.iseeyou.domain.student.StudentRepository;
import com.csfive.hanium.iseeyou.dto.parent.LoginRequestDto;
import com.csfive.hanium.iseeyou.dto.parent.ParentAddChildRequestDto;
import com.csfive.hanium.iseeyou.dto.parent.ParentSavetRequestDto;
import com.csfive.hanium.iseeyou.dto.parent.ParentUpdateRequestDto;
import com.csfive.hanium.iseeyou.dto.student.StudentAcceptanceReqDto;
import com.csfive.hanium.iseeyou.dto.student.StudentDetailResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.csfive.hanium.iseeyou.utils.ResponseMessage.*;

@Transactional
@RequiredArgsConstructor
@Service
public class ParentService {

    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;

    public void signup(ParentSavetRequestDto requestDto){
        parentRepository.save(requestDto.toEntity());
    }

//    public ResponseEntity<String> addChild(Long parentId, ParentAddChildRequestDto parentAddChildRequestDto){
//        String ChildName = parentAddChildRequestDto.getStudentName();
//        String ChildEmail = parentAddChildRequestDto.getStudentEmail();
//        Student student =  studentRepository.findByNameAndEmail(ChildName,ChildEmail)
//                .orElseThrow(()->new IllegalArgumentException(ChildName+" 이나 "+ChildEmail+"이 존재하지 않습니다."));
//
//        Parent parent = parentRepository.findById(parentId)
//                .orElseThrow(()->new IllegalArgumentException(parentId+"는 존재하지 않는 부모ID입니다"));
//
//        parent.addStudent(student);
//
//        return new ResponseEntity<>(SAVE_SUCCESS,HttpStatus.OK);
//    }

    public Long update(Long parentId, ParentUpdateRequestDto updateRequestDto){
        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 Id입니다. "+parentId));
        parent.update(updateRequestDto.getName(),updateRequestDto.getPassword(), updateRequestDto.getEmail(),updateRequestDto.getGender());

        return parent.getId();
    }

    public void delete(Long parentId){
        Parent parent = parentRepository.findById(parentId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 Id입니다."+parentId));
        //parent.deleteChild();
        parentRepository.delete(parent);
    }

    public List<StudentDetailResDto> findStudents(Long id){
        Parent parent = parentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(String.format("%d, 존재하지 않는 Id입니다. ", id)));

        List<StudentDetailResDto> studentDetailsResDtos = new ArrayList<>();
        for(Student student : parent.getStudents()){
            studentDetailsResDtos.add(new StudentDetailResDto(student));
        }

        return studentDetailsResDtos;
    }

    public Long login(LoginRequestDto loginRequestDto){
        String parentEmail = loginRequestDto.getEmail();
        String parentPW = loginRequestDto.getPassword();
        Parent parent = parentRepository.findByEmailAndPassword(parentEmail,parentPW)
                .orElseThrow(()->new IllegalArgumentException(String.format("존재하지 않는 ID,혹은 PW")));
        return parent.getId();
    }

    public void accpetanceStudent(Long parentId, StudentAcceptanceReqDto studentAcceptanceReqDto){
        String studentName = studentAcceptanceReqDto.getName();
        String studentEmail = studentAcceptanceReqDto.getEmail();

        Student student = studentRepository.findByNameAndEmail(studentName,studentEmail)
                .orElseThrow(()->new IllegalArgumentException(String.format("존재하지않는 Name : %d, 혹은 Email : %d",studentName,studentEmail)));
        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(()->new IllegalArgumentException(String.format("존재하지 않는 사용자입니다 ID : %d",parentId)));

        parent.addStudent(student);
    }
}
