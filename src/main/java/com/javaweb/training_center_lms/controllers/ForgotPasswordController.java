package com.javaweb.training_center_lms.controllers;

import com.javaweb.training_center_lms.models.Account;
import com.javaweb.training_center_lms.service.IAccountService;
import com.javaweb.training_center_lms.service.Impl.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/controllers/ForgotPasswordController"})
public class ForgotPasswordController extends BaseController {
    private final IAccountService accountService = new AccountService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardResquestTo("/views/forgot-password.jsp", req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get data from view
        String role = req.getParameter("role").trim();
        String username = req.getParameter("username").trim();
        String email = req.getParameter("email").trim();
        Account account = Account.builder()
                .role(role)
                .username(username)
                .build();

        // Validate input data
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        if (!accountService.checkAccountExistenceByUsername(account)) {
            // Show alert
            String json = String.format("""
                    {
                        "login_status": "%s",
                        "errorType": "%s"
                    }
                    """, "fail", "role_username");
            out.print(json);
            out.flush();
            out.close();
            return;
        }
        else if (!accountService.checkAccountExistenceByRole_Username_Email(account, email)) {
            // Show alert
            String json = String.format("""
                    {
                        "login_status": "%s",
                        "errorType": "%s"
                    }
                    """, "fail", "username_email");
            out.print(json);
            out.flush();
            out.close();
            return;
        }


        // Update database (reset corresponding user password)
        accountService.resetPassword(account, email);


        // Show alert & Redirect user to login page
        String redirect_url = "/controllers/LoginController";
        String json = String.format("""
                {
                    "login_status": "%s",
                    "redirect_url": "%s"
                }
                """, "success", redirect_url);
        out.print(json);
        out.flush();
        out.close();
    }
}
