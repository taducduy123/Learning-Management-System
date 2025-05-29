package com.javaweb.training_center_lms.service.Impl;

import com.javaweb.training_center_lms.models.Manager;
import com.javaweb.training_center_lms.repository.IManagerRepo;
import com.javaweb.training_center_lms.repository.Impl.ManagerRepo;
import com.javaweb.training_center_lms.service.IManagerService;

public class ManagerService implements IManagerService {
    private final IManagerRepo managerRepo = new ManagerRepo();


    @Override
    public int getManagerIdBy_username_and_password(String username, String password) {
        return managerRepo.getManagerByUsername_Password(username, password).getUser_id();
    }

    @Override
    public boolean isManagerAuthenticated(int id) {
        return managerRepo.getManagerByID(id) != null;
    }

    @Override
    public Manager getManagerByAccountID(int account_id) {
        return managerRepo.getManagerByAccountID(account_id);
    }
}
