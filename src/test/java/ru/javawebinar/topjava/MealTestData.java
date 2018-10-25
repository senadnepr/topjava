package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
//    public static final Meal MEAL;
    public static final Map<Integer, List<Meal>> MEALS = new ConcurrentHashMap<>();

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    static{
        MEALS.put(USER_ID, new ArrayList<>(Arrays.asList(
                new Meal(LocalDateTime.parse("2018-12-31 09:00", formatter),"Завтрак", 500),
                new Meal(LocalDateTime.parse("2018-12-31 14:00", formatter),"Обед", 1000),
                new Meal(LocalDateTime.parse("2018-12-31 21:00", formatter),"Ужин", 500))));
        MEALS.put(ADMIN_ID, new ArrayList<>(Arrays.asList(
                new Meal(LocalDateTime.parse("2018-12-30 09:00", formatter),"Завтрак", 400),
                new Meal(LocalDateTime.parse("2018-12-30 14:00", formatter),"Обед", 1100),
                new Meal(LocalDateTime.parse("2018-12-30 21:00", formatter),"Ужин", 600))));

    }
//static {
//    MEAL = new Meal(LocalDateTime.parse("2018-12-31 21:00", formatter),"Ужин", 500);
//}

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "id");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(expected);
    }
}

