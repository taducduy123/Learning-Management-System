package com.javaweb.training_center_lms.service;

import com.javaweb.training_center_lms.models.Instructor;

public interface IInstructorService {
    Instructor getInstructorByAccountID(int account_id);
    void editProfile(Instructor instructor);
}
