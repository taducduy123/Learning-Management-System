package com.javaweb.training_center_lms.repository;

import com.javaweb.training_center_lms.models.Manager;

public interface IManagerRepo {
    Manager getManagerByUsername_Password(String username, String password);
    Manager getManagerByID(int id);
    Manager getManagerByAccountID(int accountID);
    void edit(Manager manager);
}
