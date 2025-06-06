package com.javaweb.training_center_lms.repository;

import com.javaweb.training_center_lms.models.Instructor;

public interface IInstructorRepo {
    Instructor getInstructorByAccountID(int accountID);
    void edit(Instructor instructor);
}
