package com.example.learning.controller;

import com.example.learning.dto.TeacherDto;
import com.example.learning.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<TeacherDto> addTeacher(@RequestBody TeacherDto teacherDto){
        return teacherService.addTeacher(teacherDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> editTeacher (@RequestBody TeacherDto teacherDto, @PathVariable("id") Long id){
        return teacherService.editTeacher(teacherDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher (@PathVariable("id") Long id){
        return teacherService.deleteTeacher(id);
    }
}
