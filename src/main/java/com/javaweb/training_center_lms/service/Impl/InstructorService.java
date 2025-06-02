package com.javaweb.training_center_lms.service.Impl;

import com.javaweb.training_center_lms.models.Instructor;
import com.javaweb.training_center_lms.repository.IInstructorRepo;
import com.javaweb.training_center_lms.repository.Impl.InstructorRepo;
import com.javaweb.training_center_lms.service.IInstructorService;

public class InstructorService implements IInstructorService {
    private final IInstructorRepo instructorRepo = new InstructorRepo();

    @Override
    public Instructor getInstructorByAccountID(int account_id) {
        return instructorRepo.getInstructorByAccountID(account_id);
    }
}
