package com.csfive.hanium.iseeyou.dto.pose;

import com.csfive.hanium.iseeyou.domain.pose.Pose;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PosesDto {

    private List<PoseDto> poseDtos;

    public PosesDto(final List<Pose> pose) {
        this.poseDtos = createPosesDto(pose);
    }

    private List<PoseDto> createPosesDto(final List<Pose> pose) {
        return pose.stream()
                .map(p -> new PoseDto(p.getName(), p.getCount()))
                .collect(Collectors.toList());
    }
}
