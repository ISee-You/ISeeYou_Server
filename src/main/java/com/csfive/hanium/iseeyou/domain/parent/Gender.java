package com.csfive.hanium.iseeyou.domain.parent;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.NoSuchElementException;


@RequiredArgsConstructor
public enum Gender {

    MALE("남자"),
    FEMALE("여자");

    private final String gender;

    public Gender findByToken(String gender){
        return Arrays.asList(values()).stream()
                .filter(token -> token.findBy(gender))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("해당 성별이 없습니다."));
    }

    private boolean findBy(String token){
       return this.gender.equals(token);
    }

    public String getGender(){
        return gender;
    }
}
