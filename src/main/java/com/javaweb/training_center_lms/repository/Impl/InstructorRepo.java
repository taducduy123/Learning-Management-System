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

    @Override
    public void edit(Instructor instructor) {
        if (instructor == null) {
            throw new IllegalArgumentException("Instructor cannot be null");
        }

        try {
            dbConnect.openConnection();

            String sql = """
                    update instructor
                    set first_name = ?,
                        middle_name = ?,
                        last_name = ?,
                        email = ?,
                        phone = ?
                    where instructor_id = ?;
                    """;

            dbConnect.executeVoidQuery(sql,
                    instructor.getFirst_name(),
                    instructor.getMiddle_name(),
                    instructor.getLast_name(),
                    instructor.getEmail(),
                    instructor.getPhone(),
                    instructor.getUser_id());

            dbConnect.closeResources();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
