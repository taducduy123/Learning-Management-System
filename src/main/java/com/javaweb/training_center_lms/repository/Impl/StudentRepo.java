package com.javaweb.training_center_lms.repository.Impl;

import com.javaweb.training_center_lms.models.Student;
import com.javaweb.training_center_lms.repository.IStudentRepo;
import com.javaweb.training_center_lms.utils.DBConnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StudentRepo implements IStudentRepo {
    private final DBConnect dbConnect = DBConnect.getInstance();

    //--------------------------------------------------------------------------

    public StudentRepo() {
    }


    public Student getStudentBy_UsernameAndPassword(String username, String password) {
        Student student = null;
        try {
            // 1. Get Connection
            dbConnect.openConnection();

            // 2. Write SQL
            String sql = """
                       SELECT s.*
                       FROM account a, student s
                       WHERE (a.account_id, a.username, a.password) = (s.account_id, ?, ?);
                    """;

            // 3. Execute SQL
            ResultSet rs = dbConnect.executeReturnQuery(sql, username, password);

            // 4. Handle Result From SQL
            int student_id;
            int account_id;
            String first_name;
            String middle_name;
            String last_name;
            String email;
            String phone;
            while (rs.next()) {
                student_id = rs.getInt("student_id");
                account_id = rs.getInt("account_id");
                first_name = rs.getString("first_name");
                middle_name = rs.getString("middle_name");
                last_name = rs.getString("last_name");
                email = rs.getString("email");
                phone = rs.getString("phone");
                student = Student.builder()
                        .user_id(student_id)
                        .account_id(account_id)
                        .first_name(first_name)
                        .middle_name(middle_name)
                        .last_name(last_name)
                        .email(email)
                        .phone(phone)
                        .build();
            }

            // 5. Close Connection and Related Resources
            dbConnect.closeResources();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }


    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new LinkedList<>();
        Student student = null;
        try {
            // 1. Get Connection
            dbConnect.openConnection();

            // 2. Write SQL
            String sql = """
                       SELECT *
                       FROM student
                    """;

            // 3. Execute SQL
            ResultSet rs = dbConnect.executeReturnQuery(sql);

            // 4. Handle Result From SQL
            int student_id;
            int account_id;
            String first_name;
            String middle_name;
            String last_name;
            String email;
            String phone;
            boolean isBlocked;
            while (rs.next()) {
                student_id = rs.getInt("student_id");
                account_id = rs.getInt("account_id");
                first_name = rs.getString("first_name");
                middle_name = rs.getString("middle_name");
                last_name = rs.getString("last_name");
                email = rs.getString("email");
                phone = rs.getString("phone");
                isBlocked = rs.getBoolean("isBlocked");
                student = Student.builder()
                        .user_id(student_id)
                        .account_id(account_id)
                        .first_name(first_name)
                        .middle_name(middle_name)
                        .last_name(last_name)
                        .email(email)
                        .phone(phone)
                        .isBlocked(isBlocked)
                        .build();
                students.add(student);
            }

            // 5. Close Connection and Related Resources
            dbConnect.closeResources();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }


    @Override
    public List<Student> getStudentsBy_Name(String name) {
        List<Student> students = new LinkedList<>();
        Student student = null;
        try {
            // 1. Get Connection
            dbConnect.openConnection();

            // 2. Write SQL
            String sql = """
                       SELECT *
                       FROM student
                       WHERE concat(first_name, ' ', middle_name, ' ', last_name) like ?;
                    """;

            // 3. Execute SQL
            ResultSet rs = dbConnect.executeReturnQuery(sql, name);

            // 4. Handle Result From SQL
            int student_id;
            int account_id;
            String first_name;
            String middle_name;
            String last_name;
            String email;
            String phone;
            while (rs.next()) {
                student_id = rs.getInt("student_id");
                account_id = rs.getInt("account_id");
                first_name = rs.getString("first_name");
                middle_name = rs.getString("middle_name");
                last_name = rs.getString("last_name");
                email = rs.getString("email");
                phone = rs.getString("phone");
                student = Student.builder()
                        .user_id(student_id)
                        .account_id(account_id)
                        .first_name(first_name)
                        .middle_name(middle_name)
                        .last_name(last_name)
                        .email(email)
                        .phone(phone)
                        .build();
                students.add(student);
            }

            // 5. Close Connection and Related Resources
            dbConnect.closeResources();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }


    @Override
    public int blockStudentBy_ID(int id) {
        int row_changed = -1;

        dbConnect.openConnection();

        String sql = """
                update student
                set isBlocked = 1
                where student_id = ?;
                """;

        row_changed = dbConnect.executeVoidQuery(sql, id);

        dbConnect.closeResources();

        return row_changed;
    }

    @Override
    public int unblockStudentBy_ID(int id) {
        int row_changed = -1;

        dbConnect.openConnection();

        String sql = """
                update student
                set isBlocked = 0
                where student_id = ?;
                """;

        row_changed = dbConnect.executeVoidQuery(sql, id);

        dbConnect.closeResources();

        return row_changed;
    }


    @Override
    public Student getStudentById(int id) {
        Student student = null;

        try {
            dbConnect.openConnection();

            String sql = """
                    select *
                    from student
                    where student_id = ?;
                    """;

            ResultSet rs = dbConnect.executeReturnQuery(sql, id);

            int student_id;
            int account_id;
            String first_name;
            String middle_name;
            String last_name;
            String email;
            String phone;
            while (rs.next()) {
                student_id = rs.getInt("student_id");
                account_id = rs.getInt("account_id");
                first_name = rs.getString("first_name");
                middle_name = rs.getString("middle_name");
                last_name = rs.getString("last_name");
                email = rs.getString("email");
                phone = rs.getString("phone");
                student = Student.builder()
                        .user_id(student_id)
                        .account_id(account_id)
                        .first_name(first_name)
                        .middle_name(middle_name)
                        .last_name(last_name)
                        .email(email)
                        .phone(phone)
                        .build();
            }

            // 5. Close Connection and Related Resources
            dbConnect.closeResources();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return student;
    }


    @Override
    public void save(Student student) {
        // Check if student existed
        try {
            boolean exists = false;
            dbConnect.openConnection();
            String sql = """
                    select *
                    from student
                    where student_id = ?;
                    """;
            ResultSet rs = dbConnect.executeReturnQuery(sql, student.getUser_id());
            if (rs.next()) {
                exists = true;
            }
            dbConnect.closeResources();

            // Update if student exist
            if (exists) {
                dbConnect.openConnection();
                sql = """
                        update student
                        set student_id = ?, 
                            account_id = ?, 
                            first_name = ?, 
                            middle_name = ?, 
                            last_name = ?, 
                            email = ?, 
                            phone = ?
                        where student_id = ?;
                        """;
                dbConnect.executeVoidQuery(sql,
                        student.getUser_id(),
                        student.getAccount_id(),
                        student.getFirst_name(),
                        student.getMiddle_name(),
                        student.getLast_name(),
                        student.getEmail(),
                        student.getPhone(),
                        student.getUser_id());
                dbConnect.closeResources();
            } else { // Insert if student not exist
                dbConnect.openConnection();
                sql = """
                        insert into student(student_id, account_id, first_name, middle_name, last_name, email, phone) 
                        values
                        (?, ?, ?, ?, ?, ?, ?);
                        """;
                dbConnect.executeVoidQuery(sql,
                        student.getUser_id(),
                        student.getAccount_id(),
                        student.getFirst_name(),
                        student.getMiddle_name(),
                        student.getLast_name(),
                        student.getEmail(),
                        student.getPhone());
                dbConnect.closeResources();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void create(Student student) {
        if(student == null) {
            return;
        }

        try {
            // Check if student exist
            dbConnect.openConnection();
            String sql = """
                    select *
                    from student
                    where student_id = ?;
                    """;
            ResultSet rs = dbConnect.executeReturnQuery(sql, student.getUser_id());

            if (rs.next()){
                return;
            }
            dbConnect.closeResources();

            // Create new student if not exist
            dbConnect.openConnection();
            sql = """
                    insert into student(account_id, first_name, middle_name, last_name, email, phone)
                    values
                    (?, ?, ?, ?, ?, ?);
                    """;
            dbConnect.executeVoidQuery(sql,
                    student.getAccount_id(),
                    student.getFirst_name(),
                    student.getMiddle_name(),
                    student.getLast_name(),
                    student.getEmail(),
                    student.getPhone());
            dbConnect.closeResources();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        StudentRepo studentRepo = new StudentRepo();
        studentRepo.unblockStudentBy_ID(1);

    }
}
