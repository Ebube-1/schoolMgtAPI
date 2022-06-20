package com.example.learning.repository;

import com.example.learning.entity.Student;
import com.example.learning.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeachersRepository extends JpaRepository<Teacher, Long> {
//    Teacher findByEmail (String email);
    Optional<Teacher> findByEmail(String email);
}

