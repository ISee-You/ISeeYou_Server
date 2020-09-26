package com.csfive.hanium.iseeyou.dto.pose;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PoseDto {

    private String name;
    private int count;

    public PoseDto(final String name, final int count) {
        this.name = name;
        this.count = count;
    }
}
