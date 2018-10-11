package ru.javawebinar.topjava.controller;

import org.slf4j.Logger;
import ru.javawebinar.topjava.Data.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.MealServlet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealControllerImpl implements MealController {

    private static final Logger log = getLogger(MealServlet.class);
    private static List<Meal> meals = MealTestData.getNewList();

    @Override
    public void delete(Long id) {
        Meal meal = this.findById(id);
        List<Meal> mealArray = new ArrayList<>(meals);
        mealArray.remove(meal);
        meals = mealArray;
    }

    @Override
    public void save(Long id, String dateTimeStr, String description, int calories) {
        if(this.findById(id) != null){
            this.delete(id);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
        this.newMeal(id, dateTime, description, calories);
    }

    @Override
    public Meal findById(Long id) {
        if(id == null) return null;
        for (Meal m : meals
                ) {
            if (id.equals(m.getId())) {
                return m;
            }
        }
        return null;
    }

    @Override
    public List<Meal> findAll() {
        return meals;
    }

    @Override
    public void newMeal(Long id, LocalDateTime dateTime, String description, int calories) {
        meals = MealTestData.newMeal(meals, id, dateTime, description, calories);
    }
}
