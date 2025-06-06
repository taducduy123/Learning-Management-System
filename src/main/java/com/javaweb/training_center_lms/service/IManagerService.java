package com.javaweb.training_center_lms.service;

import com.javaweb.training_center_lms.models.Manager;

public interface IManagerService {
    int getManagerIdBy_username_and_password(String username, String password);
    boolean isManagerAuthenticated(int id);
    Manager getManagerByAccountID(int account_id);
    void editProfile(Manager manager);
}
