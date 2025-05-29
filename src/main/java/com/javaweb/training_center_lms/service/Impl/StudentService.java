package com.javaweb.training_center_lms.service.Impl;

import com.javaweb.training_center_lms.models.Student;
import com.javaweb.training_center_lms.repository.IStudentRepo;
import com.javaweb.training_center_lms.repository.Impl.StudentRepo;
import com.javaweb.training_center_lms.service.IStudentService;

import java.util.List;

public class StudentService implements IStudentService {

    private final IStudentRepo studentRepo = new StudentRepo();

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.getAllStudents();
    }

    @Override
    public List<Student> getStudentsByName(String name) {
        String nameToSql = "%" + name + "%";
        return studentRepo.getStudentsBy_Name(nameToSql);
    }

    @Override
    public void blockStudentBy_ID(int id) {
        studentRepo.blockStudentBy_ID(id);
    }

    @Override
    public void unblockStudentBy_ID(int id) {
        studentRepo.unblockStudentBy_ID(id);
    }


    @Override
    public Student getStudentById(int id) {
        return studentRepo.getStudentById(id);
    }


    @Override
    public void saveStudent(Student student) {
        studentRepo.save(student);
    }

    @Override
    public void createStudent(Student student) {
        studentRepo.create(student);
    }

    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        System.out.println(studentService.getStudentsByName("Duy"));
    }



}
