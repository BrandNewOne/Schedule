package com.example.calpjt1.ui.home.decorator;


import android.app.Activity;
import android.content.Context;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class EventDecorator implements DayViewDecorator {

    private int color;
    private final HashSet<CalendarDay> dates;


    public EventDecorator(int color, List<CalendarDay> dates) {
        this.color = color;
        this.dates = new HashSet<>(dates);

    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        System.out.println("이거"+dates.contains(day));
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(5, color)); // 날자밑에 점
    }
}


