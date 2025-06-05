package com.javaweb.training_center_lms.controllers.instructor;

import com.javaweb.training_center_lms.controllers.BaseController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/controllers/instructor/HomeController/*"})
public class HomeController extends BaseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = req.getParameter("option");
        if(option == null){
            option = "";
        }

        switch (option) {
            case "":
                responseRedirectTo("/controllers/instructor/DashboardController", req, resp);
                break;
            case "dashboard":
                responseRedirectTo("/controllers/instructor/DashboardController", req, resp);
                break;
            case "student":
                responseRedirectTo("/controllers/instructor/StudentController", req, resp);
                break;
            case "course":
                responseRedirectTo("/controllers/instructor/CourseController", req, resp);
                break;
            default:
                break;
        }
    }
}
