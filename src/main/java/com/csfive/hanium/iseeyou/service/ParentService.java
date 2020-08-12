package com.csfive.hanium.iseeyou.service;

import com.csfive.hanium.iseeyou.domain.parent.Parent;
import com.csfive.hanium.iseeyou.domain.parent.ParentRepository;
import com.csfive.hanium.iseeyou.domain.student.Student;
import com.csfive.hanium.iseeyou.domain.student.StudentRepository;
import com.csfive.hanium.iseeyou.dto.parent.LoginRequestDto;
import com.csfive.hanium.iseeyou.dto.parent.ParentAddChildRequestDto;
import com.csfive.hanium.iseeyou.dto.parent.ParentSavetRequestDto;
import com.csfive.hanium.iseeyou.dto.parent.ParentUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.transaction.Transaction;
import java.util.NoSuchElementException;

import static com.csfive.hanium.iseeyou.utils.ResponseMessage.*;

@RequiredArgsConstructor
@Service
public class ParentService {

    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public ResponseEntity<String> save(ParentSavetRequestDto requestDto){
        parentRepository.save(requestDto.toEntity());

        return new ResponseEntity<>(CREATE_USER, HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<String> addChild(Long parentId, ParentAddChildRequestDto parentAddChildRequestDto){
        String ChildName = parentAddChildRequestDto.getStudentName();
        String ChildEmail = parentAddChildRequestDto.getStudentEmail();
        Student student =  studentRepository.findByNameAndEmail(ChildName,ChildEmail)
                .orElseThrow(()->new IllegalArgumentException(ChildName+" 이나 "+ChildEmail+"이 존재하지 않습니다."));

        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(()->new IllegalArgumentException(parentId+"는 존재하지 않는 부모ID입니다"));

        parent.addChild(student);

        return new ResponseEntity<>(SAVE_SUCCESS,HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> update(Long parentId, ParentUpdateRequestDto updateRequestDto){
        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 Id입니다. "+parentId));
        parent.update(updateRequestDto.getName(),updateRequestDto.getPassword(), updateRequestDto.getEmail(),updateRequestDto.getGender());

        return new ResponseEntity<>(UPDATE_USER,HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> delete(Long parentId){
        Parent parent = parentRepository.findById(parentId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 Id입니다."+parentId));
        //parent.deleteChild();
        parentRepository.delete(parent);

        return new ResponseEntity<>(DELETE_USER,HttpStatus.OK);
    }

    public ResponseEntity<String> findChilds(Long id){
        Parent parent = parentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(String.format("%d, 존재하지 않는 Id입니다. ", id)));

        System.out.println(parent.getStudents().get(0).getName());
        return ResponseEntity.ok("찾음");
    }

    @Transactional
    public Long login(LoginRequestDto loginRequestDto){
        String parentEmail = loginRequestDto.getEmail();
        String parentPW = loginRequestDto.getPassword();
        Parent parent = parentRepository.findByEmailAndPassword(parentEmail,parentPW)
                .orElseThrow(()->new IllegalArgumentException(String.format("존재하지 않는 ID,혹은 PW")));
        return parent.getId();
    }
}
