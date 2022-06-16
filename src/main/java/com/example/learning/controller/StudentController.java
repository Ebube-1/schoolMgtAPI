package com.example.learning.controller;

import com.example.learning.dto.StudentDto;
import com.example.learning.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/{id}")
    public ResponseEntity<StudentDto> registerStudents (@PathVariable("id") Long id, @RequestBody StudentDto studentDto){
        return studentService.registerStudent(id, studentDto);
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents (){
        return studentService.getAllStudents();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> editStudent(@RequestBody StudentDto studentDto, @PathVariable("id") Long id){
        return studentService.updateStudent(studentDto,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent (@PathVariable("id") Long id){
        return studentService.deleteStudent(id);
    }


}
