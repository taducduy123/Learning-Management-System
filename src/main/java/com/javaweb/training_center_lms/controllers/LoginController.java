package com.javaweb.training_center_lms.controllers;

import com.javaweb.training_center_lms.models.Account;
import com.javaweb.training_center_lms.service.IAccountService;
import com.javaweb.training_center_lms.service.IInstructorService;
import com.javaweb.training_center_lms.service.IManagerService;
import com.javaweb.training_center_lms.service.IStudentService;
import com.javaweb.training_center_lms.service.Impl.AccountService;
import com.javaweb.training_center_lms.service.Impl.InstructorService;
import com.javaweb.training_center_lms.service.Impl.ManagerService;
import com.javaweb.training_center_lms.service.Impl.StudentService;
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
    private final IInstructorService instructorService = new InstructorService();
    private final IStudentService studentService = new StudentService();
    private final SessionUtil sessionUtil = SessionUtil.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginController");
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
                forwardResquestTo("/views/login.jsp", req, resp);
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get data from view
        String role = req.getParameter("role").trim();
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        Account account = accountService.getAccountByRole_Username_Password(role, username, password);

        // Authenticate user
        if (!accountService.checkAccountExistenceByRole_Username_Password(account)) {
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

        if(accountService.checkAccountBlocked(account)){
            //Show alert
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            String json = String.format("""
                    {
                        "login_status": "%s"
                    }
                    """, "blocked");
            out.print(json);
            out.flush();
            out.close();
            return;
        }

        sessionUtil.putValue("account", account, req);
        switch (account.getRole().toLowerCase()) {
            case "manager":
                sessionUtil.putValue("user", managerService.getManagerByAccountID(account.getAccount_id()), req);
                break;
            case "instructor":
                sessionUtil.putValue("user", instructorService.getInstructorByAccountID(account.getAccount_id()), req);
                break;
            case "student":
                sessionUtil.putValue("user", studentService.getStudentByAccountID(account.getAccount_id()), req);
                break;
            default:
                break;
        }


        // Authorize user
        redirectUser(account, req, resp);
    }


    private void redirectUser(Account account, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String url = null;
        String role = account.getRole();
        url = switch (role.toLowerCase()) {
            case "student" -> "/controllers/student/DashboardController";
            case "instructor" -> "/controllers/instructor/DashboardController";
            case "manager" -> "/controllers/manager/DashboardController";
            default -> url;
        };

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
