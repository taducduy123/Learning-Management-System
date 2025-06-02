package com.javaweb.training_center_lms.service.Impl;

import com.javaweb.training_center_lms.models.Account;
import com.javaweb.training_center_lms.repository.IAccountRepo;
import com.javaweb.training_center_lms.repository.Impl.AccountRepo;
import com.javaweb.training_center_lms.service.IAccountService;

public class AccountService implements IAccountService {
    private final IAccountRepo accountRepo = new AccountRepo();

    @Override
    public boolean checkAccountExistenceByRole_Username_Password(Account account) {
        if(account == null) {
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
        if(!checkAccountExistenceByUsername(account)) {
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
        if(checkAccountExistenceByRole_Username_Password(account)) {
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
}
