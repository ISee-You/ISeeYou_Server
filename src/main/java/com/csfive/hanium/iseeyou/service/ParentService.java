package com.csfive.hanium.iseeyou.service;

import com.csfive.hanium.iseeyou.domain.parent.ParentRepository;
import com.csfive.hanium.iseeyou.web.dto.ParentSavetRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ParentService {

    private final ParentRepository parentRepository;

    @Transactional
    public Long save(ParentSavetRequestDto requestDto){
        System.out.println(">>>>>>>>>>>"+requestDto.getName());
        System.out.println(">>>>>>>>>>>"+requestDto.getGender());
        Long id = parentRepository.save(requestDto.toEntity()).getId();
        System.out.print(id);
        return id;
    }

}
