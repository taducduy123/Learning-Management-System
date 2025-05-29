package com.javaweb.training_center_lms.models;


import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public abstract class User {
    protected int user_id;
    protected int account_id;
    protected String first_name;
    protected String middle_name;
    protected String last_name;
    protected int level;
    protected String email;
    protected String phone;
}
