package com.example.learning.dto;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private String firstName;
    private String LastName;
    private int age;
    private String email;
    private String password;
    private String level;
    private Date dob;
    private String gender;
}
