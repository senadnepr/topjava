package ru.javawebinar.topjava.controller;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class MealWithExceedControllerImpl implements MealWithExceedController {

    MealController mealController = new MealControllerImpl();

    List<Meal> meals;

    @Override
    public List<MealWithExceed> getWithExceededNoTime(int calories) {
        meals = mealController.findAll();

        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
                );
        return meals.stream()
                .map(meal -> {
                    boolean exceeded = caloriesSumByDate.get(meal.getDate()) > calories;
                    return new MealWithExceed(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceeded);
                })
                .collect(toList());

    }

    @Override
    public MealController getMealController() {
        return mealController;
    }
}
