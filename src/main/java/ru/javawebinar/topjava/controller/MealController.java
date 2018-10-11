package ru.javawebinar.topjava.controller;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealController {
    void delete(Long id);
    void save(Long id, String dateTimeStr, String description, int calories);
    Meal findById(Long id);
    List<Meal> findAll();
    void newMeal(Long id, LocalDateTime dateTime, String description, int calories);
}
