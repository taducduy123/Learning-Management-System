package com.javaweb.training_center_lms.utils;

import java.sql.*;
import java.util.ResourceBundle;

public class DBConnect {

    // Lấy thông tin từ một file .properties có trong folder main/resources
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("database");

    private static final String DRIVER = resourceBundle.getString("DRIVER_NAME");
    private static final String URL = resourceBundle.getString("URL");
    private static final String USERNAME = resourceBundle.getString("USERNAME");
    private static final String PASSWORD = resourceBundle.getString("PASSWORD");;

    private static DBConnect instance = null;
    private static Connection conn = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;

    //----------------------------------------------------------------------------------
    private DBConnect() {
        System.out.println("DBConnect instantiated");
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static DBConnect getInstance() {
        if (instance == null) {
            instance = new DBConnect();
        }
        return instance;
    }


    public void openConnection() {
        try {
            if(conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Show connection details
    public static void showDatabaseDetails() {
        try {
            if (conn != null && !conn.isClosed()) {
                try {
                    DatabaseMetaData metaData = conn.getMetaData();
                    System.out.println("Database Product Name: " + metaData.getDatabaseProductName());
                    System.out.println("Database Product Version: " + metaData.getDatabaseProductVersion());
                    System.out.println("Driver Name: " + metaData.getDriverName());
                    System.out.println("Driver Version: " + metaData.getDriverVersion());
                    System.out.println("URL: " + metaData.getURL());
                    System.out.println("Username: " + metaData.getUserName());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("Cannot access the connection since the connection is closed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ResultSet executeReturnQuery(String sql, Object... params) {
        try {
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public int executeVoidQuery(String sql, Object... params) {
        int rows_changed = -1;
        try {
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            rows_changed = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows_changed;
    }


    public void closeResources() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            if (pstmt != null && !pstmt.isClosed()) {
                pstmt.close();
            }
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
