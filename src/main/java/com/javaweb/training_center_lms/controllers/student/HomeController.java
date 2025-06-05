package com.javaweb.training_center_lms.controllers.student;

import com.javaweb.training_center_lms.controllers.BaseController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/controllers/student/HomeController/*"})
public class HomeController extends BaseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = req.getParameter("option");
        if(option == null){
            option = "";
        }

        switch (option) {
            case "":
                responseRedirectTo("/controllers/student/DashboardController", req, resp);
                break;
            case "dashboard":
                responseRedirectTo("/controllers/student/DashboardController", req, resp);
                break;
            case "course":
                responseRedirectTo("/controllers/student/CourseController", req, resp);
                break;
            case "assignment":
                responseRedirectTo("/controllers/student/AssignmentController", req, resp);
                break;
            case "grade":
                responseRedirectTo("/controllers/student/GradeController", req, resp);
                break;
            default:
                break;
        }
    }
}
