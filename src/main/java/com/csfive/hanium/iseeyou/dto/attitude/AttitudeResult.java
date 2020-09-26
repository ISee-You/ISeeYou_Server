package com.csfive.hanium.iseeyou.dto.attitude;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttitudeResult {

    private List<Integer> counts;
    private long totalSecond;

    public AttitudeResult(final List<Integer> counts, final long totalSecond) {
        this.counts = counts;
        this.totalSecond = totalSecond;
    }
}
