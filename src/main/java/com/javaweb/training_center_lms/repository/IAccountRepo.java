package com.javaweb.training_center_lms.repository;

import com.javaweb.training_center_lms.models.Account;

public interface IAccountRepo {
    Account getAccountByUsername(String username);
    Account getAccountByRole_Username_Password(String role, String username, String password);
    Account getManagerAccountByUsername_Email(String username, String email);
    Account getInstructorAccountByUsername_Email(String username, String email);
    Account getStudentAccountByUsername_Email(String username, String email);
    Account getAccountByAccountID(int accountID);
    void saveAccount(Account account);
    int getMaxAccountID();
    void createAccount(Account account);
    void resetPassword(Account account, String newPassword);
}
