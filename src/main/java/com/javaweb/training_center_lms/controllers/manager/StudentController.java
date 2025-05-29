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
        if(uri == null || uri.isEmpty()){
            uri = "";
        }

        switch (uri) {
            case "":
                handleRequest_ViewAllStudents(req, resp);
                break;
            case "/search":
                handleRequest_SearchStudents(req, resp);
                break;
            case "/block":
                handleRequest_BlockStudent(req, resp);
                break;
            case "/un-block":
                handleRequest_UnBlockStudent(req, resp);
                break;
            case "/edit":
                handleRequest_EditStudent(req, resp);
                break;
            case "/edit/save":
                handleRequest_EditSave(req, resp);
                break;
            case "/edit/back":
                handleRequest_EditBack(req, resp);
                break;
            case "/create":
                handleRequestGet_CreateStudent(req, resp);
                break;
            case "/create/back":
                handleRequestGet_CreateBackStudent(req, resp);
                break;
            default:
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getPathInfo();
        System.out.println("doPost URI = " + uri);

        switch (uri) {
            case "/edit/save":
                handleRequest_EditSave(req, resp);
                break;
            case "/create":
                handleRequestPost_CreateStudent(req, resp);
                break;
            default:
                break;
        }
    }


    public void handleRequest_ViewAllStudents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("requestType", "view_all");
        req.setAttribute("allStudents", studentService.getAllStudents());
        DashboardController.loadContentPane("/views/manager/student.jsp", req, resp);
    }

    public void handleRequest_SearchStudents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        req.setAttribute("searchedStudents", studentService.getStudentsByName(keyword));

        req.setAttribute("requestType", "search");
        DashboardController.loadContentPane("/views/manager/student.jsp", req, resp);
    }


    public void handleRequest_BlockStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_to_block = Integer.parseInt(req.getParameter("student_id"));
        studentService.blockStudentBy_ID(id_to_block);

        req.setAttribute("requestType", "view_all");
        req.setAttribute("allStudents", studentService.getAllStudents());
        DashboardController.loadContentPane("/views/manager/student.jsp", req, resp);
    }

    public void handleRequest_UnBlockStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_to_unblock = Integer.parseInt(req.getParameter("student_id"));
        studentService.unblockStudentBy_ID(id_to_unblock);

        req.setAttribute("requestType", "view_all");
        req.setAttribute("allStudents", studentService.getAllStudents());
        DashboardController.loadContentPane("/views/manager/student.jsp", req, resp);
    }


    public void handleRequest_EditStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get request from view
        int student_id = Integer.parseInt(req.getParameter("student_id"));

        // Access database
        Student student = studentService.getStudentById(student_id);
        Account account = accountService.getAccountByID(student.getUser_id());

        // Send information to view
        req.setAttribute("student", student);
        req.setAttribute("account", account);

        // Load view
        DashboardController.loadContentPane("/views/manager/student-edit.jsp", req, resp);
    }

    public void handleRequest_EditBack(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("requestType", "view_all");
        req.setAttribute("allStudents", studentService.getAllStudents());
        DashboardController.loadContentPane("/views/manager/student.jsp", req, resp);
    }


    public void handleRequest_EditSave(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Get data from view
        Student student2Save = Student.builder()
                .user_id(Integer.parseInt(req.getParameter("student_id")))
                .account_id(Integer.parseInt(req.getParameter("account_id")))
                .first_name(req.getParameter("first-name"))
                .middle_name(req.getParameter("middle-name"))
                .last_name(req.getParameter("last-name"))
                .email(req.getParameter("email"))
                .phone(req.getParameter("phone"))
                .build();

        Account account2Save = accountService.getAccountByID(student2Save.getAccount_id());

        // Update database
        accountService.saveAccount(account2Save);
        studentService.saveStudent(student2Save);

        // Back to a student list
        responseRedirectTo("/controllers/manager/StudentController/", req, resp);
    }


    public void handleRequestGet_CreateStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Show create view
        DashboardController.loadContentPane("/views/manager/student-create.jsp", req, resp);
    }


    public void handleRequestPost_CreateStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get data from view
        int account_id = accountService.getMaxAccountID() + 1;
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String first_name = req.getParameter("first-name");
        String middle_name = req.getParameter("middle-name");
        String last_name = req.getParameter("last-name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        Account account = Account.builder()
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
        if(accountService.checkAccountExistence(account)){
            responseRedirectTo("/controllers/manager/StudentController/create", req, resp);
            return;
        }

        // Update database
        accountService.createAccount(account);
        studentService.createStudent(student);

        // Redirect view
        responseRedirectTo("/controllers/manager/StudentController/", req, resp);
    }


    public void handleRequestGet_CreateBackStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("requestType", "view_all");
        req.setAttribute("allStudents", studentService.getAllStudents());
        DashboardController.loadContentPane("/views/manager/student.jsp", req, resp);
    }
}


