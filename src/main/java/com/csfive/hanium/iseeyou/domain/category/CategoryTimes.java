package com.csfive.hanium.iseeyou.domain.category;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CategoryTimes {

    private final long penTime;
    private final long bookTime;
    private final long laptopTime;

    List<Proficiencytime> proficiencytimeList = new ArrayList<>();

    private void convertPenTime(long penTime){
        Proficiencytime proficiencytime = new Proficiencytime();
        proficiencytime.setCategoryname("penTime");
        proficiencytime.setProficiency(penTime);
        proficiencytimeList.add(proficiencytime);
    }

    private void convertBookTime(long bookTime){
        Proficiencytime proficiencytime = new Proficiencytime();
        proficiencytime.setCategoryname("bookTime");
        proficiencytime.setProficiency(bookTime);
        proficiencytimeList.add(proficiencytime);
    }

    private void convertLaptopTime(long laptopTime){
        Proficiencytime proficiencytime = new Proficiencytime();
        proficiencytime.setCategoryname("laptopTime");
        proficiencytime.setProficiency(laptopTime);
        proficiencytimeList.add(proficiencytime);
    }

    public List<Proficiencytime> convertTime() {
        convertPenTime(this.penTime);
        convertBookTime(this.bookTime);
        convertLaptopTime(this.laptopTime);

        return this.proficiencytimeList;
    }
}
