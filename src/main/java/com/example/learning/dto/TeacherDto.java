package com.example.learning.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherDto {
    private String firstName;
    private String LastName;
    private String email;
    private String password;
    private String qualification;
    private Date dob;
    private String gender;
}
