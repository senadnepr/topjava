package ru.javawebinar.topjava.controller;

import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

public interface MealWithExceedController {
    List<MealWithExceed> getWithExceededNoTime(int calories);
    MealController getMealController();
}
