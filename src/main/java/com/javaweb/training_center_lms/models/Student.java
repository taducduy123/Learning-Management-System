package com.javaweb.training_center_lms.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@ToString(callSuper=true)
public class Student extends User{
    private boolean isBlocked;
}




