package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;
import static ru.javawebinar.topjava.util.Util.orElse;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;

@Controller
public class JspMealController {
    @Autowired
    private MealService service;

    @GetMapping("/meals")
    public String meals(Model model){
        int userId = SecurityUtil.authUserId();
        List<MealTo> mealTos = MealsUtil.getWithExcess(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay());
        model.addAttribute("meals", mealTos);
        return "meals";
    }

    @GetMapping("/meals/create")
    public String create(Model model){
        final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        model.addAttribute("action", "create");
        return "mealForm";
    }

    @GetMapping("/meals/update/{id}")
    public String update(Model model, @PathVariable String id){
        int userId = SecurityUtil.authUserId();
        final Meal meal = service.get(Integer.parseInt(id), userId);
        model.addAttribute("meal", meal);
        model.addAttribute("action", "update");
        return "mealForm";
    }

    @GetMapping("/meals/delete")
    public String delete(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        int userId = SecurityUtil.authUserId();
        service.delete(Integer.parseInt(paramId), userId);
        return "redirect:/meals";
    }

    @PostMapping("/meals/save")
    public String save(HttpServletRequest request){

        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        if (request.getParameter("id").isEmpty()) {
            int userId = SecurityUtil.authUserId();
            service.create(meal, userId);
        } else {
            String paramId = Objects.requireNonNull(request.getParameter("id"));
            int userId = SecurityUtil.authUserId();
            assureIdConsistent(meal, Integer.parseInt(paramId));
            service.update(meal, userId);
        }
        return "redirect:/meals";
    }

    @PostMapping("/meals/filter")
    public String filter(Model model, HttpServletRequest request) {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));

        int userId = SecurityUtil.authUserId();
        List<Meal> mealsDateFiltered = service.getBetweenDates(
                orElse(startDate, DateTimeUtil.MIN_DATE), orElse(endDate, DateTimeUtil.MAX_DATE), userId);
        List<MealTo> mealTos = MealsUtil.getFilteredWithExcess(mealsDateFiltered, SecurityUtil.authUserCaloriesPerDay(),
                orElse(startTime, LocalTime.MIN), orElse(endTime, LocalTime.MAX));

        model.addAttribute("meals", mealTos);

        return "meals";
    }

}
