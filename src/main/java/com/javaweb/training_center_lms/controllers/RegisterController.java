package com.javaweb.training_center_lms.controllers;

import com.javaweb.training_center_lms.models.Account;
import com.javaweb.training_center_lms.service.IAccountService;
import com.javaweb.training_center_lms.service.Impl.AccountService;
import com.javaweb.training_center_lms.utils.CookieUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/controllers/RegisterController"})
public class RegisterController extends BaseController {
    private final IAccountService accountService = new AccountService();
    private final CookieUtil cookieUtil = CookieUtil.getInstance();
    private final int cookie_expire = 60;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardResquestTo("/views/register.jsp", req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get data from view
        String role = req.getParameter("role");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm_password");
        Account account = Account.builder()
                .role(role)
                .username(username)
                .password(password)
                .build();

        // Validate
        if(accountService.checkAccountExistenceByUsername(account)) {
            // Show alert
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            String json = String.format("""
                    {
                        "login_status": "%s",
                        "errorType":"%s"
                    }
                    """, "fail", "username_exist");
            out.print(json);
            out.flush();
            out.close();
            return;
        }
        if(!password.equals(confirmPassword)) {
            // Show alert
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            String json = String.format("""
                    {
                        "login_status": "%s",
                        "errorType":"%s"
                    }
                    """, "fail", "confirm_password_invalid");
            out.print(json);
            out.flush();
            out.close();
            return;
        }

        // Access database
        accountService.registerAccount(account);

        // Update cookies
        cookieUtil.putValue("role_C", account.getRole(), cookie_expire, resp);
        cookieUtil.putValue("username_C", account.getUsername(), cookie_expire, resp);
        cookieUtil.putValue("password_C", account.getPassword(), cookie_expire, resp);

        // Show alert and redirect user
        String redirect_url = "/controllers/LoginController";
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        String json = String.format("""
                    {
                        "login_status": "%s",
                        "redirect_url":"%s"
                    }
                    """, "success", redirect_url);
        out.print(json);
        out.flush();
        out.close();
    }
}
