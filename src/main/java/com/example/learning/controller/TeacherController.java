package com.example.learning.controller;

import com.example.learning.dto.StudentDto;
import com.example.learning.dto.TeacherDto;
import com.example.learning.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TeacherDto> addTeacher(@Valid @RequestBody TeacherDto teacherDto){
        return teacherService.addTeacher(teacherDto);
    }


    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllStudents (){
        return teacherService.getAllTeachers();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> editTeacher (@Valid @RequestBody TeacherDto teacherDto, @PathVariable("id") Long id){
        return teacherService.editTeacher(teacherDto, id);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher (@PathVariable("id") Long id){
        return teacherService.deleteTeacher(id);
    }


}
