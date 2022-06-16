package com.example.learning.repository;

import com.example.learning.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Student, Long> {
    Student findStudentByEmail(String email);
    int findByTeacher(Long id);
}