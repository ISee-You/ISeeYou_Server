package com.csfive.hanium.iseeyou.web;

import com.csfive.hanium.iseeyou.domain.parent.Gender;
import com.csfive.hanium.iseeyou.domain.parent.Parent;
import com.csfive.hanium.iseeyou.domain.parent.ParentRepository;
import com.csfive.hanium.iseeyou.web.dto.ParentSavetRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ParentRepository parentRepository;

    @AfterEach
    public void clear() throws Exception{
        parentRepository.deleteAll();
    }

    @Test
    public void TestSave(){

        //given
        String name = "이현종";
        String email = "ahajongs@naver.com";
        String password = "123456789";
        Gender gender = Gender.FEMALE;

        Parent save = parentRepository.save(Parent.builder()
                .name(name)
                .email(email)
                .password(password)
                .gender(gender)
                .build());


        ParentSavetRequestDto requestDto =
            ParentSavetRequestDto.builder()
                .name(save.getName())
                .email(save.getEmail())
                .password(save.getPassword())
                .gender(save.getGender())
                .build();

        String url = "http://localhost:"+port+"/api/v1/parents";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        //then
      //  assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        //assertThat(responseEntity.getBody()).isGreaterThan(0L);
        //질문 : assertThat이 junit4에서는 org.assertj.core.api에서 받아왔는데 junit5에서는 matchers에서 assserThat을 받아오라고 하지만 import가 되지 않음 왜그럴까..?

        List<Parent> all = parentRepository.findAll();

        System.out.println( all.get(0).getGender().getGender());

       // assertThat(all.get(0).getName()).isEqualTo(name);
      //  System.out.println(all.get(0).getGender());

    }


}
