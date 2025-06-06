package com.javaweb.training_center_lms.controllers;

import com.javaweb.training_center_lms.models.Instructor;
import com.javaweb.training_center_lms.models.Manager;
import com.javaweb.training_center_lms.models.Student;
import com.javaweb.training_center_lms.models.User;
import com.javaweb.training_center_lms.service.IInstructorService;
import com.javaweb.training_center_lms.service.IManagerService;
import com.javaweb.training_center_lms.service.IStudentService;
import com.javaweb.training_center_lms.service.Impl.InstructorService;
import com.javaweb.training_center_lms.service.Impl.ManagerService;
import com.javaweb.training_center_lms.service.Impl.StudentService;
import com.javaweb.training_center_lms.utils.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(urlPatterns = {"/controllers/ProfileController"})
public class ProfileController extends BaseController {

    private final IManagerService managerService = new ManagerService();
    private final IInstructorService instructorService = new InstructorService();
    private final IStudentService studentService = new StudentService();
    private final SessionUtil sessionUtil = SessionUtil.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardResquestTo("/views/profile.jsp", req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get data from view
        String role = req.getParameter("role");
        String firstName = req.getParameter("firstName");
        String middleName = req.getParameter("middleName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        User user;
        int current_user_id = ((User)sessionUtil.getValue("user", req)).getUser_id();

        System.out.println("do Post PROFILE");
        switch (role.toLowerCase()) {
            case "manager":
                user = Manager.builder()
                        .user_id(current_user_id)
                        .first_name(firstName)
                        .middle_name(middleName)
                        .last_name(lastName)
                        .email(email)
                        .phone(phone)
                        .build();

                // Update database
                managerService.editProfile((Manager) user);
                break;
            case "instructor":
                user = Instructor.builder()
                        .user_id(current_user_id)
                        .first_name(firstName)
                        .middle_name(middleName)
                        .last_name(lastName)
                        .email(email)
                        .phone(phone)
                        .build();

                // Update database
                instructorService.editProfile((Instructor) user);
                break;
            case "student":
                user = Student.builder()
                        .user_id(current_user_id)
                        .first_name(firstName)
                        .middle_name(middleName)
                        .last_name(lastName)
                        .email(email)
                        .phone(phone)
                        .build();

                // Update database
                studentService.editProfile((Student) user);
                break;
            default:
                break;
        }

        // Update session
        ((User) sessionUtil.getValue("user", req)).setFirst_name(firstName);
        ((User) sessionUtil.getValue("user", req)).setMiddle_name(middleName);
        ((User) sessionUtil.getValue("user", req)).setLast_name(lastName);
        ((User) sessionUtil.getValue("user", req)).setEmail(email);
        ((User) sessionUtil.getValue("user", req)).setPhone(phone);


        // Show alert and redirect user
        String alert = "success";
        String message = "edit_profile_success";
        req.setAttribute("alert", alert);
        req.setAttribute("message", message);
        forwardResquestTo("/views/profile.jsp", req, resp);
    }
}
