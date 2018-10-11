package ru.javawebinar.topjava.Data;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class MealTestData {
    private static AtomicLong idCounter = new AtomicLong();

    List<Meal> meals;

    public MealTestData() {
        this.meals = getNewList();
    }

    public static List<Meal> getNewList() {

        return Arrays.asList(
                new Meal(idCounter.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 28, 10, 0), "Завтрак", 600),
                new Meal(idCounter.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 28, 13, 0), "Обед", 1000),
                new Meal(idCounter.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 28, 20, 0), "Ужин", 600),
                new Meal(idCounter.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 29, 10, 0), "Завтрак", 900),
                new Meal(idCounter.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 29, 13, 0), "Обед", 500),
                new Meal(idCounter.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 29, 20, 0), "Ужин", 510),
                new Meal(idCounter.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(idCounter.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(idCounter.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(idCounter.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(idCounter.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(idCounter.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );

    }

    public static List<Meal> newMeal(List<Meal> meals1, Long id, LocalDateTime dateTime, String description, int calories){
        if(id == null) id = idCounter.getAndIncrement();
        meals1.add(new Meal(id, dateTime, description, calories));
        return meals1;
    }

}

