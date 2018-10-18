package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private MealRestController restController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            restController = appCtx.getBean(MealRestController.class);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = SecurityUtil.authUserId(request.getParameter("userid"));
        request.setAttribute("userid", Integer.parseInt(request.getParameter("userid")));

        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("formName").equals("updateNew")) {
            String id = request.getParameter("id");

            Meal meal = new Meal((id.isEmpty() ? null : Integer.valueOf(id)),
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.parseInt(request.getParameter("calories")),
                    Integer.parseInt(request.getParameter("userId")));

            log.info(meal.isNew() ? "Create {}" : "Update {}", meal);
            restController.create(meal);
            response.sendRedirect("meals");
        }
        if (request.getParameter("formName").equals("filter")) {

            String strFrom = request.getParameter("fromDate") + " " + request.getParameter("fromTime");
            String strTo = request.getParameter("toDate") + " " + request.getParameter("toTime");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            LocalTime localTimeFrom = LocalTime.parse(strFrom, formatter);
            LocalTime localTimeTo = LocalTime.parse(strTo, formatter);
            LocalDate localDateFrom = LocalDate.parse(strFrom, formatter);
            LocalDate localDateTo = LocalDate.parse(strTo, formatter);

                    request.setAttribute("meals",
                    MealsUtil.getFilteredWithExceeded(restController.getAllbyId(userId),
                            MealsUtil.DEFAULT_CALORIES_PER_DAY, localDateFrom, localDateTo, localTimeFrom, localTimeTo));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
        else doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int userId = SecurityUtil.authUserId(request.getParameter("userid"));

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                restController.delete(id, userId);
                request.setAttribute("userid", Integer.parseInt(request.getParameter("userid")));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000, userId) :
                        restController.get(getId(request), userId);
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                request.setAttribute("userid", userId);

                request.setAttribute("meals",
                        MealsUtil.getWithExceeded(restController.getAllbyId(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
