package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    private MealService service;


    public List<Meal> getAllbyId(int userId) {
        return service.getAllbyId(userId);
    }


    public Meal get(int id, int userId) {
        return service.get(id, userId);
    }


    public Meal create(Meal meal) {
        return service.create(meal);
    }


    public void delete(int id, int userId) {
        service.delete(id, userId);
    }


    public void update(Meal meal, int userId) {
        service.update(meal, userId);
    }

}