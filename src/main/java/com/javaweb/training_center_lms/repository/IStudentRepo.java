package com.javaweb.training_center_lms.repository;

import com.javaweb.training_center_lms.models.Instructor;
import com.javaweb.training_center_lms.models.Student;

import java.util.List;

public interface IStudentRepo {
    Student getStudentByAccountID(int accountID);
    Student getStudentBy_UsernameAndPassword(String username, String password);
    List<Student> getAllStudents();
    List<Student> getStudentsBy_Name(String name);
    Student getStudentById(int id);
    int blockStudentBy_ID(int id);
    int unblockStudentBy_ID(int id);
    void save(Student student);
    void create(Student student);
}
