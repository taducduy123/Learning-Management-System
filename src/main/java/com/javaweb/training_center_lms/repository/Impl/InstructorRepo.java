package com.javaweb.training_center_lms.repository.Impl;

import com.javaweb.training_center_lms.models.Instructor;
import com.javaweb.training_center_lms.models.Manager;
import com.javaweb.training_center_lms.repository.IInstructorRepo;
import com.javaweb.training_center_lms.utils.DBConnect;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InstructorRepo implements IInstructorRepo {
    private final DBConnect dbConnect = DBConnect.getInstance();


    @Override
    public Instructor getInstructorByAccountID(int accountID) {
        Instructor instructor = null;

        try {
            dbConnect.openConnection();

            String sql = """
                        select *
                        from instructor 
                        where account_id = ?;
                    """;

            ResultSet rs = dbConnect.executeReturnQuery(sql, accountID);

            while (rs.next()) {
                instructor = Instructor.builder()
                        .user_id(rs.getInt("instructor_id"))
                        .account_id(rs.getInt("account_id"))
                        .first_name(rs.getString("first_name"))
                        .middle_name(rs.getString("middle_name"))
                        .last_name(rs.getString("last_name"))
                        .email(rs.getString("email"))
                        .phone(rs.getString("phone"))
                        .level(rs.getInt("level"))
                        .isBlocked(rs.getBoolean("isBlocked"))
                        .build();
            }

            dbConnect.closeResources();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return instructor;
    }
}
