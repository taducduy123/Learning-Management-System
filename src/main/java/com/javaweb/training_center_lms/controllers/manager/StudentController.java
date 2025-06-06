package com.javaweb.training_center_lms.controllers.manager;

import com.javaweb.training_center_lms.controllers.BaseController;
import com.javaweb.training_center_lms.models.Account;
import com.javaweb.training_center_lms.models.Student;
import com.javaweb.training_center_lms.service.IAccountService;
import com.javaweb.training_center_lms.service.IStudentService;
import com.javaweb.training_center_lms.service.Impl.AccountService;
import com.javaweb.training_center_lms.service.Impl.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/controllers/manager/StudentController/*"})
public class StudentController extends BaseController {

    private final IStudentService studentService = new StudentService();
    private final IAccountService accountService = new AccountService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getPathInfo();
        if (uri == null) {
            uri = "";
        }
        String action = null;

        switch (uri) {
            case "":
                handleRequestGet_ViewAllStudents(req, resp);
                break;
            case "/search":
                handleRequestGet_SearchStudents(req, resp);
                break;
            case "/block":
                handleRequestGet_BlockStudent(req, resp);
                break;
            case "/un-block":
                handleRequestGet_UnBlockStudent(req, resp);
                break;
            case "/create":
                action = req.getParameter("action");
                if (action == null) {
                    action = "";
                }
                switch (action) {
                    case "showForm":
                        handleRequestGet_Create_ShowForm(req, resp);
                        break;
                    case "back":
                        handleRequestGet_Create_Back(req, resp);
                        break;
                    default:
                        break;
                }
                break;
            case "/edit":
                action = req.getParameter("action");
                if (action == null) {
                    action = "";
                }
                switch (action) {
                    case "showForm":
                        handleRequestGet_Edit_ShowForm(req, resp);
                        break;
                    case "back":
                        handleRequestGet_Edit_Back(req, resp);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getPathInfo();
        if (uri == null) {
            uri = "";
        }
        String action = null;

        switch (uri) {
            case "/create":
                action = req.getParameter("action");
                switch (action) {
                    case "save":
                        handleRequestPost_Create_Save(req, resp);
                        break;
                    default:
                        break;
                }
                break;
            case "/edit":
                action = req.getParameter("action");
                switch (action) {
                    case "save":
                        handleRequestPost_Edit_Save(req, resp);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }


    public void handleRequestGet_ViewAllStudents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", studentService.getAllStudents());
        forwardResquestTo("/views/manager/student.jsp", req, resp);
    }

    public void handleRequestGet_Create_ShowForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Show alert and message from previous request
        String alert = req.getParameter("alert");
        String message = req.getParameter("message");
        req.setAttribute("alert", alert);
        req.setAttribute("message", message);

        // Load view
        forwardResquestTo("/views/manager/student-create.jsp", req, resp);
    }

    public void handleRequestGet_Create_Back(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Redirect view
        responseRedirectTo("/controllers/manager/StudentController", req, resp);
    }

    public void handleRequestGet_SearchStudents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        req.setAttribute("students", studentService.getStudentsByName(keyword));
        forwardResquestTo("/views/manager/student.jsp", req, resp);
    }

    public void handleRequestGet_BlockStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get data from view
        int id_to_block = Integer.parseInt(req.getParameter("student_id"));

        // Update database
        studentService.blockStudentBy_ID(id_to_block);

        // transmit data to view
        req.setAttribute("students", studentService.getAllStudents());

        // Load view
        forwardResquestTo("/views/manager/student.jsp", req, resp);
    }

    public void handleRequestGet_UnBlockStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get data from view
        int id_to_unblock = Integer.parseInt(req.getParameter("student_id"));

        // Update database
        studentService.unblockStudentBy_ID(id_to_unblock);

        // transmit data to view
        req.setAttribute("students", studentService.getAllStudents());

        // Load view
        forwardResquestTo("/views/manager/student.jsp", req, resp);
    }

    public void handleRequestGet_Edit_ShowForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Show alert and message from previous request
        String alert = req.getParameter("alert");
        String message = req.getParameter("message");
        req.setAttribute("alert", alert);
        req.setAttribute("message", message);

        // Get data view
        int student_id = Integer.parseInt(req.getParameter("student_id"));

        // Access database
        Student student = studentService.getStudentById(student_id);
        Account account = accountService.getAccountByID(student.getAccount_id());

        // Send data to view
        req.setAttribute("student", student);
        req.setAttribute("account", account);

        // Load view
        forwardResquestTo("/views/manager/student-edit.jsp", req, resp);
    }

    public void handleRequestGet_Edit_Back(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Redirect view
        responseRedirectTo("/controllers/manager/StudentController", req, resp);
    }

    public void handleRequestPost_Create_Save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get data from view
        int account_id = accountService.getMaxAccountID() + 1;
        System.out.println("account_id to insert = " + account_id);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String first_name = req.getParameter("first_name");
        String middle_name = req.getParameter("middle_name");
        String last_name = req.getParameter("last_name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        Account account = Account.builder()
                .account_id(account_id)
                .username(username)
                .password(password)
                .role("student")
                .build();

        Student student = Student.builder()
                .account_id(account_id)
                .first_name(first_name)
                .middle_name(middle_name)
                .last_name(last_name)
                .email(email)
                .phone(phone)
                .build();


        // Check account exists
        String alert, message;
        if (accountService.checkAccountExistenceByUsername(account)) {
            System.out.println("Account already exists!");
            // Show alert
            alert = "danger";
            message = "Username already exists!";

            // Load view
            responseRedirectTo("/controllers/manager/StudentController/create?action=showForm&alert=" + alert + "&message=" + message, req, resp);
            return;
        }

        // Update database
        accountService.createAccount(account);
        studentService.createStudent(student);

        // Show alert
        alert = "success";
        message = "Student created successfully!";

        // Load view
        responseRedirectTo("/controllers/manager/StudentController/create?action=showForm&alert=" + alert + "&message=" + message, req, resp);
    }

    public void handleRequestPost_Edit_Save(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Get data from view
        Student student2Save = Student.builder()
                .user_id(Integer.parseInt(req.getParameter("student_id")))
                .account_id(Integer.parseInt(req.getParameter("account_id")))
                .first_name(req.getParameter("first_name"))
                .middle_name(req.getParameter("middle_name"))
                .last_name(req.getParameter("last_name"))
                .email(req.getParameter("email"))
                .phone(req.getParameter("phone"))
                .build();

        Account account2Save = Account.builder()
                .account_id(Integer.parseInt(req.getParameter("account_id")))
                .role(req.getParameter("role"))
                .username(req.getParameter("username"))
                .password(req.getParameter("password"))
                .build();

        // Update database
        accountService.saveAccount(account2Save);
        studentService.saveStudent(student2Save);

        // Show alert
        String alert = "success";
        String message = "Student edited successfully!";

        // Load view
        responseRedirectTo("/controllers/manager/StudentController/edit?action=showForm&alert=" + alert + "&message=" + message + "&student_id=" + student2Save.getUser_id(), req, resp);
    }

}


