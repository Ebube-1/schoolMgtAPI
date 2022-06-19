package com.example.learning.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String LastName;
    @NotEmpty
    private int age;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String level;
    private Date dob;
    @NotEmpty
    private String gender;
}
