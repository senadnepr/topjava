package ru.javawebinar.topjava.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.ADMIN_ID;
import static ru.javawebinar.topjava.MealTestData.USER_ID;
import static ru.javawebinar.topjava.MealTestData.assertMatch;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})

@RunWith(SpringRunner.class)

@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    MealService service;


    @Test(expected = NotFoundException.class)
    public void get() {
//        Meal actual = MealTestData.MEALS.get(USER_ID).get(2);
        Meal expected = service.getAll(USER_ID).get(0);
//        assertMatch(actual, expected);
        service.get(expected.getId(), ADMIN_ID);

    }

    @Test(expected = NotFoundException.class)
    public void delete() throws Exception {
//        Meal expected = service.getAll(USER_ID).get(0);
//        service.delete(expected.getId(), USER_ID);

        Meal expected1 = service.getAll(ADMIN_ID).get(0);
        service.delete(expected1.getId(), USER_ID);

    }

    @Test(expected = NotFoundException.class)
    public void update() {

        Meal expected = service.getAll(USER_ID).get(0);
        service.update(expected, ADMIN_ID);
    }

}