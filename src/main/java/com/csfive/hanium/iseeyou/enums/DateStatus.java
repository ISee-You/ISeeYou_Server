package com.csfive.hanium.iseeyou.enums;

import java.util.Arrays;

public enum DateStatus {
    DAY("일", 1),
    WEEK("주", 7),
    MONTH("월", 30),
    ALL("전부", 0);

    public final String type;
    public final int days;

    DateStatus(String type, int days) {
        this.type = type;
        this.days = days;
    }

    public static DateStatus findByType(final String dateType) {
        return Arrays.stream(DateStatus.values())
                .filter(d -> d.equalsType(dateType))
                .findFirst()
                .orElse(ALL);
    }

    private boolean equalsType(final String dateType) {
        return this.type.equals(dateType);
    }
}
