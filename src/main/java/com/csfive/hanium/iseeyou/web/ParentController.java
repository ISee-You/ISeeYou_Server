package com.csfive.hanium.iseeyou.web;

import com.csfive.hanium.iseeyou.service.ParentService;
import com.csfive.hanium.iseeyou.web.dto.ParentSavetRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ParentController {

    private final ParentService parentService;

    @PostMapping("/api/v1/parents")
    public Long save(@RequestBody ParentSavetRequestDto requestDto){
        System.out.println(">>>>>>>>>>>>>>>"+requestDto.getName());
        return parentService.save(requestDto);
    }
}
