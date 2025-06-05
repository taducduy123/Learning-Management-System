package com.javaweb.training_center_lms.controllers.manager;

import com.javaweb.training_center_lms.controllers.BaseController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/controllers/manager/DashboardController/*"})
public class DashboardController extends BaseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getPathInfo();
        if (uri == null) {
            uri = "";
        }

        switch (uri) {
            case "":
                forwardResquestTo("/views/manager/dashboard.jsp", req, resp);
                break;
            default:
                break;
        }
    }
}
