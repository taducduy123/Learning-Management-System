package com.javaweb.training_center_lms.controllers.manager;

import com.javaweb.training_center_lms.controllers.BaseController;
import com.javaweb.training_center_lms.service.IManagerService;
import com.javaweb.training_center_lms.service.IStudentService;
import com.javaweb.training_center_lms.service.Impl.ManagerService;
import com.javaweb.training_center_lms.service.Impl.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(urlPatterns = {"/controllers/manager/DashboardController/*"})
public class DashboardController extends BaseController {
    private final IManagerService managerService = new ManagerService();
    private final IStudentService studentService = new StudentService();

    private static String contentPaneURL = null;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getPathInfo();
        System.out.println(uri);
        if(uri == null || uri.isEmpty()){
            uri = "";
        }

        switch (uri) {
            case "":
                forwardResquestTo("/views/manager/dashboard.jsp", req, resp);
                break;
            case "/student-option":
                forwardResquestTo("/controllers/manager/StudentController", req, resp);
                break;
            case "/instructor-option":
                break;
            case "/course-option":
                break;
            default:
                break;

        }


    }


    public static void loadContentPane(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        contentPaneURL = url;
        request.setAttribute("contentPaneURL", contentPaneURL);
        forwardResquestTo("/views/manager/dashboard.jsp", request, response);
    }
}
