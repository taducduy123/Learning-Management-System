package com.javaweb.training_center_lms.repository.Impl;

import com.javaweb.training_center_lms.models.Account;
import com.javaweb.training_center_lms.repository.IAccountRepo;
import com.javaweb.training_center_lms.utils.DBConnect;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepo implements IAccountRepo {
    private final DBConnect dbConnect = DBConnect.getInstance();


    @Override
    public Account getAccountByRole_Username_Password(String role, String username, String password) {
        Account account = null;

        try {
            dbConnect.openConnection();

            String sql = """
                        select * 
                        from account
                        where (role, username, password) = (?, ?, ?);
                    """;

            ResultSet rs = dbConnect.executeReturnQuery(sql, role, username, password);


            while (rs.next()) {
                account = Account.builder()
                        .account_id(rs.getInt("account_id"))
                        .role(rs.getString("role"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .build();
            }

            dbConnect.closeResources();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return account;
    }


    @Override
    public Account getAccountByAccountID(int accountID) {
        Account account = null;

        try {
            dbConnect.openConnection();

            String sql = """
                        select * 
                        from account
                        where account_id = ?;
                    """;

            ResultSet rs = dbConnect.executeReturnQuery(sql, accountID);


            while (rs.next()) {
                account = Account.builder()
                        .account_id(rs.getInt("account_id"))
                        .role(rs.getString("role"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .build();
            }

            dbConnect.closeResources();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return account;
    }


    @Override
    public void saveAccount(Account account) {
        if (account == null) {
            return;
        }
        // Check if account existed
        try {
            boolean exists = false;
            dbConnect.openConnection();
            String sql = """
                    select *
                    from account
                    where account_id = ?;
                    """;
            ResultSet rs = dbConnect.executeReturnQuery(sql, account.getAccount_id());
            if (rs.next()) {
                exists = true;
            }
            dbConnect.closeResources();

            // Update if student exist
            if (exists) {
                dbConnect.openConnection();
                sql = """
                        update account
                        set account_id = ?,
                            role = ?,
                            username = ?,
                            password = ?
                        where account_id = ?;
                        """;
                dbConnect.executeVoidQuery(sql,
                        account.getAccount_id(),
                        account.getRole(),
                        account.getUsername(),
                        account.getPassword(),
                        account.getAccount_id());
                dbConnect.closeResources();
            } else { // Insert if student not exist
                dbConnect.openConnection();
                sql = """
                        insert into account(account_id, role, username, password)
                        values
                        (?, ?, ?, ?);
                        """;
                dbConnect.executeVoidQuery(sql,
                        account.getAccount_id(),
                        account.getRole(),
                        account.getUsername(),
                        account.getPassword());
                dbConnect.closeResources();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getMaxAccountID() {
        Account account = null;

        try {
            dbConnect.openConnection();

            String sql = """
                        select max(account_id) 
                        from account
                    """;

            ResultSet rs = dbConnect.executeReturnQuery(sql);


            if (rs.next()) {
                return rs.getInt("max(account_id)");
            }

            dbConnect.closeResources();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    @Override
    public void createAccount(Account account) {
        if(account == null) {
            return;
        }

        try {
            // Check if an account exists
            dbConnect.openConnection();
            String sql = """
                    select *
                    from account
                    where account_id = ?;
                    """;
            ResultSet rs = dbConnect.executeReturnQuery(sql, account.getAccount_id());

            if (rs.next()){
                return;
            }
            dbConnect.closeResources();

            // Create a new account if not exist
            dbConnect.openConnection();
            sql = """
                    insert into account(role, username, password)
                    values
                    (?, ?, ?);
                    """;
            dbConnect.executeVoidQuery(sql,
                    account.getRole(),
                    account.getUsername(),
                    account.getPassword());
            dbConnect.closeResources();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Account getAccountByUsername(String username) {
        Account account = null;

        try {
            dbConnect.openConnection();

            String sql = """
                        select * 
                        from account
                        where username = ?;
                    """;

            ResultSet rs = dbConnect.executeReturnQuery(sql, username);


            while (rs.next()) {
                account = Account.builder()
                        .account_id(rs.getInt("account_id"))
                        .role(rs.getString("role"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .build();
            }

            dbConnect.closeResources();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return account;
    }


    @Override
    public Account getManagerAccountByUsername_Email(String username, String email) {
        Account account = null;

        try {
            dbConnect.openConnection();

            String sql = """
                        select account.*
                        from account
                        join manager
                        on manager.account_id = account.account_id
                        where (username, email) = (?, ?);
                    """;

            ResultSet rs = dbConnect.executeReturnQuery(sql, username, email);


            while (rs.next()) {
                account = Account.builder()
                        .account_id(rs.getInt("account_id"))
                        .role(rs.getString("role"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .build();
            }

            dbConnect.closeResources();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return account;
    }


    @Override
    public Account getInstructorAccountByUsername_Email(String username, String email) {
        Account account = null;

        try {
            dbConnect.openConnection();

            String sql = """
                        select account.*
                        from account
                        join instructor
                        on instructor.account_id = account.account_id
                        where (username, email) = (?, ?);
                    """;

            ResultSet rs = dbConnect.executeReturnQuery(sql, username, email);


            while (rs.next()) {
                account = Account.builder()
                        .account_id(rs.getInt("account_id"))
                        .role(rs.getString("role"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .build();
            }

            dbConnect.closeResources();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return account;
    }


    @Override
    public Account getStudentAccountByUsername_Email(String username, String email) {
        Account account = null;

        try {
            dbConnect.openConnection();

            String sql = """
                        select account.*
                        from account
                        join student
                        on student.account_id = account.account_id
                        where (username, email) = (?, ?);
                    """;

            ResultSet rs = dbConnect.executeReturnQuery(sql, username, email);


            while (rs.next()) {
                account = Account.builder()
                        .account_id(rs.getInt("account_id"))
                        .role(rs.getString("role"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .build();
            }

            dbConnect.closeResources();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return account;
    }


    @Override
    public void resetPassword(Account account, String newPassword) {
        try {
            dbConnect.openConnection();

            String sql = """
                    update account
                    set password = ?
                    where username = ?;
                    """;

            dbConnect.executeVoidQuery(sql, newPassword, account.getUsername());

            dbConnect.closeResources();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
