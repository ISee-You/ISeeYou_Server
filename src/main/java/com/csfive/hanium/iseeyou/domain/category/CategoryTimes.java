package com.csfive.hanium.iseeyou.domain.category;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CategoryTimes {

    private final long penTime;
    private final long bookTime;
    private final long laptopTime;

    List<ProficiencyTime> proficiencyTimeList = new ArrayList<>();

    private void RefiningtTime(long categoryTime, String settingCategoryName){
        ProficiencyTime proficiencyTime = new ProficiencyTime();
        proficiencyTime.setCategoryname(settingCategoryName);
        proficiencyTime.setProficiency(categoryTime);
        proficiencyTimeList.add(proficiencyTime);
    }

    public List<ProficiencyTime> convertTime() {
        RefiningtTime(this.penTime,"penTime");
        RefiningtTime(this.bookTime,"bookTime");
        RefiningtTime(this.laptopTime,"laptopTime");

        return this.proficiencyTimeList;
    }
}
