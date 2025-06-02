package com.javaweb.training_center_lms.controllers;

import com.javaweb.training_center_lms.models.Account;
import com.javaweb.training_center_lms.service.IAccountService;
import com.javaweb.training_center_lms.service.IManagerService;
import com.javaweb.training_center_lms.service.Impl.AccountService;
import com.javaweb.training_center_lms.service.Impl.ManagerService;
import com.javaweb.training_center_lms.utils.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = {"/controllers/LoginController/*"})
public class LoginController extends BaseController {

    private final IAccountService accountService = new AccountService();
    private final IManagerService managerService = new ManagerService();
    private final SessionUtil sessionUtil = SessionUtil.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet - LoginController");

        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action) {
            case "login":
                forwardResquestTo("/views/login.jsp", req, resp);
                break;
            case "logout":
                sessionUtil.removeValue("account", req);
                sessionUtil.removeValue("user", req);
                responseRedirectTo("/controllers/LoginController/logout?action=login", req, resp);
                break;
            default:
                break;
        }


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get data from view
        String role = req.getParameter("role");
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        // Authenticate user
        Account account = accountService.getAccountByRole_Username_Password(role, username, password);
        if (!accountService.checkAccountExistenceByRole_Username_Password(account)) {
            System.out.println("call");
            //Show alert
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            String json = String.format("""
                    {
                        "login_status": "%s"
                    }
                    """, "fail");
            out.print(json);
            out.flush();
            out.close();
            return;
        }


        // Authorize user
        sessionUtil.putValue("account", account, req);

        switch (account.getRole().toLowerCase()) {
            case "student":
                break;
            case "instructor":
                break;
            case "manager":
                sessionUtil.putValue("user", managerService.getManagerByAccountID(account.getAccount_id()), req);
                break;
            default:
                break;
        }



        // Redirect user to the correct dashboard
        redirectUser(account, req, resp);
    }


    public void redirectUser(Account account, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String url = null;
        String role = account.getRole();
        switch (role.toLowerCase()) {
            case "student":
                url = "/controllers/student/DashboardController";
                break;
            case "instructor":
                url = "/controllers/instructor/DashboardController";
                break;
            case "manager":
                url = "/controllers/manager/DashboardController";
                break;
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        String json = String.format("""
                {
                    "login_status": "%s",
                    "user_dashboard_url": "%s"
                }
                """, "success", url);
        out.print(json);
        out.flush();
        out.close();
    }



}
