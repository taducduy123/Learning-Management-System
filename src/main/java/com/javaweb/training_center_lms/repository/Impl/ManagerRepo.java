package com.javaweb.training_center_lms.repository.Impl;

import com.javaweb.training_center_lms.models.Manager;
import com.javaweb.training_center_lms.repository.IManagerRepo;
import com.javaweb.training_center_lms.utils.DBConnect;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerRepo implements IManagerRepo {
    private final DBConnect dbConnect = DBConnect.getInstance();


    @Override
    public Manager getManagerByUsername_Password(String username, String password) {
        Manager manager = null;

        try {
            dbConnect.openConnection();

            String sql = """
                        select m.*
                        from manager m
                        join account a
                        on m.account_id = a.account_id
                        where (a.username, a.password) = (?, ?);
                    """;

            ResultSet rs = dbConnect.executeReturnQuery(sql, username, password);

            while (rs.next()) {
                manager = Manager.builder()
                        .user_id(rs.getInt("manager_id"))
                        .account_id(rs.getInt("account_id"))
                        .first_name(rs.getString("first_name"))
                        .middle_name(rs.getString("middle_name"))
                        .last_name(rs.getString("last_name"))
                        .email(rs.getString("email"))
                        .phone(rs.getString("phone"))
                        .build();
            }

            dbConnect.closeResources();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return manager;
    }


    @Override
    public Manager getManagerByID(int id) {
        Manager manager = null;

        try {
            dbConnect.openConnection();

            String sql = """
                        select *
                        from manager 
                        where manager_id = ?;
                    """;

            ResultSet rs = dbConnect.executeReturnQuery(sql, id);

            while (rs.next()) {
                manager = Manager.builder()
                        .user_id(rs.getInt("manager_id"))
                        .account_id(rs.getInt("account_id"))
                        .first_name(rs.getString("first_name"))
                        .middle_name(rs.getString("middle_name"))
                        .last_name(rs.getString("last_name"))
                        .email(rs.getString("email"))
                        .phone(rs.getString("phone"))
                        .build();
            }

            dbConnect.closeResources();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return manager;
    }

    @Override
    public Manager getManagerByAccountID(int accountID) {
        Manager manager = null;

        try {
            dbConnect.openConnection();

            String sql = """
                        select *
                        from manager 
                        where account_id = ?;
                    """;

            ResultSet rs = dbConnect.executeReturnQuery(sql, accountID);

            while (rs.next()) {
                manager = Manager.builder()
                        .user_id(rs.getInt("manager_id"))
                        .account_id(rs.getInt("account_id"))
                        .first_name(rs.getString("first_name"))
                        .middle_name(rs.getString("middle_name"))
                        .last_name(rs.getString("last_name"))
                        .email(rs.getString("email"))
                        .phone(rs.getString("phone"))
                        .build();
            }

            dbConnect.closeResources();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return manager;
    }


    @Override
    public void edit(Manager manager) {
        if (manager == null) {
            throw new IllegalArgumentException("Manager cannot be null");
        }

        try {
            dbConnect.openConnection();

            String sql = """
                    update manager
                    set first_name = ?,
                        middle_name = ?,
                        last_name = ?,
                        email = ?,
                        phone = ?
                    where manager_id = ?;
                    """;

            dbConnect.executeVoidQuery(sql,
                    manager.getFirst_name(),
                    manager.getMiddle_name(),
                    manager.getLast_name(),
                    manager.getEmail(),
                    manager.getPhone(),
                    manager.getUser_id());

            dbConnect.closeResources();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
