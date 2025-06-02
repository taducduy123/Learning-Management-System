package com.javaweb.training_center_lms.service;

import com.javaweb.training_center_lms.models.Account;

public interface IAccountService {
    Account getAccountByUsername(String username);
    Account getAccountByRole_Username_Password(String role, String username, String password);
    boolean checkAccountExistenceByRole_Username_Password(Account account);
    boolean checkAccountExistenceByUsername(Account account);
    Account getAccountByID(int accountID);
    void saveAccount(Account account);
    int getMaxAccountID();
    void createAccount(Account account);
}
