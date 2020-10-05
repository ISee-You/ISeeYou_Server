package com.csfive.hanium.iseeyou.dto.pose;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@NoArgsConstructor
public class PosesTimeRequest {

    private LocalDate startDate;
    private String dateType;

    public PosesTimeRequest(final LocalDate startDate, final String dateType) {
        this.startDate = startDate;
        this.dateType = dateType;
    }
}
