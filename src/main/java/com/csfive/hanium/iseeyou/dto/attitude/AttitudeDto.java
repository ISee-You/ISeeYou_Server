package com.csfive.hanium.iseeyou.dto.attitude;

import com.csfive.hanium.iseeyou.domain.attitude.Attitude;
import com.csfive.hanium.iseeyou.dto.pose.PosesDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttitudeDto {

    private Long id;

    private PosesDto poses;

    private long totalSecond;

    public AttitudeDto(Attitude attitude) {
        this.id = attitude.getId();
        this.poses = new PosesDto(attitude.getPoses()
                .getPoses());
        this.totalSecond = attitude.getTotalSecond();
    }
}
