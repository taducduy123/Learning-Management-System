package com.javaweb.training_center_lms.controllers.manager;

import com.javaweb.training_center_lms.controllers.BaseController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(urlPatterns = {"/controllers/manager/HomeController/*"})
public class HomeController extends BaseController {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = req.getParameter("option");
        if(option == null){
            option = "";
        }

        switch (option) {
            case "":
                responseRedirectTo("/controllers/manager/DashboardController", req, resp);
                break;
            case "dashboard":
                responseRedirectTo("/controllers/manager/DashboardController", req, resp);
                break;
            case "student":
                responseRedirectTo("/controllers/manager/StudentController", req, resp);
                break;
            case "instructor":
                responseRedirectTo("/controllers/manager/InstructorController", req, resp);
                break;
            case "course":
                responseRedirectTo("/controllers/manager/CourseController", req, resp);
                break;
            default:
                break;

        }
    }

}
