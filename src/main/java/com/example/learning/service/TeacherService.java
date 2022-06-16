package com.example.learning.service;

import com.example.learning.dto.TeacherDto;
import org.springframework.http.ResponseEntity;

public interface TeacherService {
    ResponseEntity<TeacherDto> addTeacher (TeacherDto teacherDto);

    ResponseEntity<TeacherDto> editTeacher (TeacherDto teacherDto, Long id);

    ResponseEntity<String> deleteTeacher (Long id);

}
