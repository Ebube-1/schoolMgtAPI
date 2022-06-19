package com.example.learning.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherDto {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String LastName;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String qualification;
    private Date dob;
    @NotEmpty
    private String gender;
}
