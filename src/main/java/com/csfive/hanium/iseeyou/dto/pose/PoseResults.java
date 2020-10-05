package com.csfive.hanium.iseeyou.dto.pose;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class PoseResults {
    private final List<Double> percentage;
    private final int totalTime;
}
