package com.example.learning.service;

import com.example.learning.dto.StudentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    ResponseEntity<StudentDto> registerStudent(Long id, StudentDto studentDto);

     ResponseEntity<List<StudentDto>> getAllStudents();

     ResponseEntity<StudentDto> updateStudent(StudentDto studentDto, Long id);

     ResponseEntity<String> deleteStudent (Long id);
}
