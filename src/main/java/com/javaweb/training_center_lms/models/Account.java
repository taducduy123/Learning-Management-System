package com.javaweb.training_center_lms.models;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class Account {
    private int account_id;
    private String role;
    private String username;
    private String password;
}
