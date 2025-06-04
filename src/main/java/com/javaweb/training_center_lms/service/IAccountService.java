package com.javaweb.training_center_lms.service;

import com.javaweb.training_center_lms.models.Account;

public interface IAccountService {
    Account getAccountByUsername(String username);
    Account getAccountByRole_Username_Password(String role, String username, String password);
    boolean checkAccountExistenceByRole_Username_Password(Account account);
    boolean checkAccountExistenceByRole_Username_Email(Account account, String email);
    boolean checkAccountExistenceByUsername(Account account);
    boolean checkAccountBlocked(Account account);
    Account getAccountByID(int accountID);
    void saveAccount(Account account);
    int getMaxAccountID();
    void createAccount(Account account);
    void resetPassword(Account account, String user_email);
}
