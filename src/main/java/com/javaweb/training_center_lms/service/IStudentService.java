package com.javaweb.training_center_lms.service;

import com.javaweb.training_center_lms.models.Student;

import java.util.List;

public interface IStudentService {
    Student getStudentByAccountID(int account_id);
    List<Student> getAllStudents();
    List<Student> getStudentsByName(String name);
    Student getStudentById(int id);
    void blockStudentBy_ID(int id);
    void unblockStudentBy_ID(int id);
    void saveStudent(Student student);
    void createStudent(Student student);
}
