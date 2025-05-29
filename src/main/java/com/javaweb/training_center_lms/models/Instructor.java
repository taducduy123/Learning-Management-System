package com.javaweb.training_center_lms.models;

import lombok.*;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Getter
@Setter
@ToString(callSuper=true)
public class Instructor extends User{
    private int level;
    private boolean isBlocked;
}
