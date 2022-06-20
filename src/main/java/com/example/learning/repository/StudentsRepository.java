package com.example.learning.repository;

import com.example.learning.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentsRepository extends JpaRepository<Student, Long> {
    Student findStudentByEmail(String email);
    int findByTeacher(Long id);
    Optional<Student> findByEmail(String email);
}