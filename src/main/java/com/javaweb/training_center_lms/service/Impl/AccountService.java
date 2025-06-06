package com.javaweb.training_center_lms.service.Impl;

import com.javaweb.training_center_lms.models.Account;
import com.javaweb.training_center_lms.models.Instructor;
import com.javaweb.training_center_lms.models.Student;
import com.javaweb.training_center_lms.repository.IAccountRepo;
import com.javaweb.training_center_lms.repository.IInstructorRepo;
import com.javaweb.training_center_lms.repository.IStudentRepo;
import com.javaweb.training_center_lms.repository.Impl.AccountRepo;
import com.javaweb.training_center_lms.repository.Impl.InstructorRepo;
import com.javaweb.training_center_lms.repository.Impl.StudentRepo;
import com.javaweb.training_center_lms.service.IAccountService;
import com.javaweb.training_center_lms.utils.EmailUtil;
import com.javaweb.training_center_lms.utils.RandomGeneratorUtil;
import com.javaweb.training_center_lms.utils.TimeUtil;

public class AccountService implements IAccountService {
    private static final IAccountRepo accountRepo = new AccountRepo();
    private static final IStudentRepo studentRepo = new StudentRepo();
    private static final IInstructorRepo instructorRepo = new InstructorRepo();
    private static final RandomGeneratorUtil randomGeneratorUtil = RandomGeneratorUtil.getInstance();
    private static final EmailUtil emailUtil = new EmailUtil();
    private static final TimeUtil timeUtil = TimeUtil.getInstance();

    @Override
    public boolean checkAccountExistenceByRole_Username_Password(Account account) {
        if (account == null) {
            return false;
        }
        return accountRepo.getAccountByRole_Username_Password(account.getRole(), account.getUsername(), account.getPassword()) != null;
    }

    @Override
    public Account getAccountByID(int accountID) {
        return accountRepo.getAccountByAccountID(accountID);
    }

    @Override
    public void saveAccount(Account account) {
        if (!checkAccountExistenceByUsername(account)) {
            return;
        }
        accountRepo.saveAccount(account);
    }

    @Override
    public int getMaxAccountID() {
        return accountRepo.getMaxAccountID();
    }

    @Override
    public void createAccount(Account account) {
        if (checkAccountExistenceByRole_Username_Password(account)) {
            return;
        }
        accountRepo.createAccount(account);
    }

    @Override
    public Account getAccountByUsername(String username) {
        return accountRepo.getAccountByUsername(username);
    }

    @Override
    public Account getAccountByRole_Username_Password(String role, String username, String password) {
        return accountRepo.getAccountByRole_Username_Password(role, username, password);
    }

    @Override
    public boolean checkAccountExistenceByUsername(Account account) {
        return accountRepo.getAccountByUsername(account.getUsername()) != null;
    }


    @Override
    public boolean checkAccountBlocked(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        boolean isBlocked = false;
        switch (account.getRole().toLowerCase()) {
            case "instructor":
                Instructor instructor = instructorRepo.getInstructorByAccountID(account.getAccount_id());
                isBlocked = instructor.isBlocked();
                break;
            case "student":
                Student student = studentRepo.getStudentByAccountID(account.getAccount_id());
                isBlocked = student.isBlocked();
                break;
            default:
                break;
        }
        return isBlocked;
    }

    @Override
    public boolean checkAccountExistenceByRole_Username_Email(Account account, String email) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }

        return switch (account.getRole().toLowerCase()) {
            case "manager" -> accountRepo.getManagerAccountByUsername_Email(account.getUsername(), email) != null;
            case "instructor" -> accountRepo.getInstructorAccountByUsername_Email(account.getUsername(), email) != null;
            case "student" -> accountRepo.getStudentAccountByUsername_Email(account.getUsername(), email) != null;
            default -> false;
        };
    }

    @Override
    public void resetPassword(Account account, String user_email) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        // Check if the account exists in the system
        if (!checkAccountExistenceByUsername(account)) {
            throw new IllegalArgumentException("Account does not exist");
        }

        // Update password in database
        String newPassword = randomGeneratorUtil.generateValidPassword();
        accountRepo.resetPassword(account, newPassword);

        // Send new password to user's email
        String subject = "Reset Password";
        String message = String.format("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<body>\n" +
                "    This is new password: <b>%s</b>\n" +
                "<br>\n" +
                "    <a href=\"http://localhost:8080/controllers/LoginController\">Click here to login the system</a>\n" +
                "</body>\n" +
                "</html>", newPassword);
        emailUtil.hostSendMailToUser(user_email, subject, message);
    }


    @Override
    public void changePassword(Account account, String new_password, String user_email) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        if (!checkAccountExistenceByUsername(account)) {
            return;
        }

        // Update database
        accountRepo.changePassword(account, new_password);

        // Send notification
        String timeModified = timeUtil.getCurrentTime();
        String subject = "Password Change Log";
        String content = String.format("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <style>\n" +
                "    body {\n" +
                "      background-color: #f8f9fa;\n" +
                "      font-family: Arial, sans-serif;\n" +
                "      padding-top: 50px;\n" +
                "      margin: 0;\n" +
                "    }\n" +
                "\n" +
                "    .log-card {\n" +
                "      background-color: #ffffff;\n" +
                "      border: 1px solid #ddd;\n" +
                "      border-radius: 6px;\n" +
                "      max-width: 600px;\n" +
                "      margin: 0 auto;\n" +
                "      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);\n" +
                "    }\n" +
                "\n" +
                "    .card-header {\n" +
                "      background-color: #28a745;\n" +
                "      color: white;\n" +
                "      padding: 16px;\n" +
                "      border-top-left-radius: 6px;\n" +
                "      border-top-right-radius: 6px;\n" +
                "    }\n" +
                "\n" +
                "    .card-body {\n" +
                "      padding: 20px;\n" +
                "      color: #333;\n" +
                "    }\n" +
                "\n" +
                "    .text-muted {\n" +
                "      color: #6c757d;\n" +
                "    }\n" +
                "\n" +
                "    h4 {\n" +
                "      margin: 0;\n" +
                "    }\n" +
                "\n" +
                "    p {\n" +
                "      margin: 8px 0;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "  <div class=\"log-card\">\n" +
                "    <div class=\"card-header\">\n" +
                "      <h4>Password Change Notification</h4>\n" +
                "    </div>\n" +
                "    <div class=\"card-body\">\n" +
                "      <p>Your password has been successfully changed. Your new password is <b>%s</b></p>\n" +
                "      <p class=\"text-muted\">\n" +
                "        <strong>Modified on: </strong><span><b>%s</b></span>\n" +
                "      </p>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "\n" +
                "</body>\n" +
                "</html>", new_password, timeModified);
        emailUtil.hostSendMailToUser(user_email, subject, content);
    }
}
