package com.javaweb.training_center_lms.controllers;

import com.javaweb.training_center_lms.models.Account;
import com.javaweb.training_center_lms.models.User;
import com.javaweb.training_center_lms.service.IAccountService;
import com.javaweb.training_center_lms.service.Impl.AccountService;
import com.javaweb.training_center_lms.utils.SessionUtil;
import com.javaweb.training_center_lms.utils.TimeUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/controllers/ChangePasswordController"})
public class ChangePasswordController extends BaseController {

    private final SessionUtil sessionUtil = SessionUtil.getInstance();
    private final IAccountService accountService = new AccountService();
    private final TimeUtil timeUtil = TimeUtil.getInstance();


    //------------------------------------------------------------------------------------------
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardResquestTo("/views/change-password.jsp", req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ChangePasswordController doPost");
        // Get data from view
        String currentPassword = req.getParameter("current_password");
        String newPassword = req.getParameter("new_password");
        String confirmPassword = req.getParameter("confirm_password");

        // Validate
        Account account = (Account) sessionUtil.getValue("account", req);
        User user = (User) sessionUtil.getValue("user", req);
        String alert, message;
        if (account == null || user == null) {
            throw new IllegalArgumentException("account or user is null");
        }
        if (!currentPassword.equals(account.getPassword())) {
            System.out.println("Current password does not match");
            // Show alert
            alert = "danger";
            message = "current_password_invalid";
            req.setAttribute("alert", alert);
            req.setAttribute("message", message);
            forwardResquestTo("/views/change-password.jsp", req, resp);
            return;
        }
        if (!newPassword.equals(confirmPassword)) {
            alert = "danger";
            message = "confirm_password_invalid";
            req.setAttribute("alert", alert);
            req.setAttribute("message", message);
            forwardResquestTo("/views/change-password.jsp", req, resp);
            return;
        }

        // Update database
        accountService.changePassword(account, newPassword, user.getEmail());

        // Show alert
        alert = "success";
        message = "change_password_success";
        req.setAttribute("alert", alert);
        req.setAttribute("message", message);
        forwardResquestTo("/views/change-password.jsp", req, resp);

    }
}
