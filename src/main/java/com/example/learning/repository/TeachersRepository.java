package com.example.learning.repository;

import com.example.learning.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachersRepository extends JpaRepository<Teacher, Long> {
    Teacher findByEmail (String email);
}

