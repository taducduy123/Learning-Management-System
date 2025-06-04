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

public class AccountService implements IAccountService {
    private static final IAccountRepo accountRepo = new AccountRepo();
    private static final IStudentRepo studentRepo = new StudentRepo();
    private static final IInstructorRepo instructorRepo = new InstructorRepo();
    private static final RandomGeneratorUtil randomGeneratorUtil = RandomGeneratorUtil.getInstance();
    private static final EmailUtil emailUtil = new EmailUtil();

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
}
