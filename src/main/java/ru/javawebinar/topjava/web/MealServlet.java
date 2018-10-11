package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.controller.MealController;
import ru.javawebinar.topjava.controller.MealWithExceedController;
import ru.javawebinar.topjava.controller.MealWithExceedControllerImpl;
import ru.javawebinar.topjava.model.MealWithExceed;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealWithExceedController withExceedController = new MealWithExceedControllerImpl();
    private MealController mealController = withExceedController.getMealController();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug(req.getParameter("id"));
        log.debug(req.getParameter("button"));
        resp.setCharacterEncoding("UTF-8");

        if(req.getParameter("button").equals("edit")) {
            req.setAttribute("headerString", "Редактирование позиции");
            req.setAttribute("meal", mealController.findById(Long.parseLong(req.getParameter("id"))));
            req.getRequestDispatcher("edit.jsp").forward(req, resp);
        }

        else if(req.getParameter("button").equals("add")) {
            req.setAttribute("headerString", "Добавление позиции");

            //       req.setAttribute("meal", mealController.newMeal());
            req.getRequestDispatcher("edit.jsp").forward(req, resp);
        }

        else if(req.getParameter("button").equals("delete")){
            mealController.delete(Long.parseLong(req.getParameter("id")));
            doGet(req, resp);
        }
        else if(req.getParameter("button").equals("save")){
            Long id = null;
            if(!req.getParameter("id").equals("")) id = Long.parseLong(req.getParameter("id"));
            mealController.save(id,
                    req.getParameter("datetime"),
                    req.getParameter("description"),
                    Integer.parseInt(req.getParameter("calories")));
            doGet(req, resp);
        }
        else doGet(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        List<MealWithExceed> meals = withExceedController.getWithExceededNoTime(2000);

        request.setAttribute("mealsList", meals);
        request.getRequestDispatcher("meals.jsp").forward(request, response);

    }
}
