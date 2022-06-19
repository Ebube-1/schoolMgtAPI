package com.example.learning.service.serviceImpl;

import com.example.learning.dto.StudentDto;
import com.example.learning.dto.TeacherDto;
import com.example.learning.entity.Student;
import com.example.learning.entity.Teacher;
import com.example.learning.exception.UserNotFoundException;
import com.example.learning.repository.StudentsRepository;
import com.example.learning.repository.TeachersRepository;
import com.example.learning.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final ModelMapper mapper;
    private final TeachersRepository teachersRepository;
    private final StudentsRepository studentsRepository;

    @Override
    public ResponseEntity<TeacherDto> addTeacher(TeacherDto teacherDto) {
        Teacher teacherEmail = teachersRepository.findByEmail(teacherDto.getEmail());
        Teacher newTeacher;
        if (teacherEmail == null) {
            Teacher teacher;
            teacher = mapper.map(teacherDto, Teacher.class);
            teacher.setRegistrationDate(LocalDateTime.now());
            newTeacher = teachersRepository.save(teacher);
        } else {
            throw new UserNotFoundException("Teacher with " + teacherDto.getEmail() + " already exists");
        }
        return new ResponseEntity<>(mapper.map(newTeacher, TeacherDto.class), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TeacherDto> editTeacher(TeacherDto teacherDto, Long id) {
        Teacher teacher = teachersRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Teach with id " + id + " does not exist"));
        teacher.setDob(teacherDto.getDob());
        teacher.setEmail(teacher.getEmail());
        teacher.setGender(teacherDto.getGender());
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setPassword(teacher.getPassword());
        teacher.setQualification(teacher.getQualification());
        teacher.setRegistrationDate(LocalDateTime.now());
        teacher.setLastName(teacherDto.getLastName());
        Teacher newTeacher = teachersRepository.save(teacher);

        return new ResponseEntity<>(mapper.map(newTeacher, TeacherDto.class), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<String> deleteTeacher(Long id) {
        Teacher teacher = teachersRepository.findById(id).orElseThrow(() -> new UserNotFoundException("not found"));
        teachersRepository.delete(teacher);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        List<TeacherDto> newList = new ArrayList<>();
        List<Teacher> teachers = teachersRepository.findAll();
        for(Teacher teacher : teachers){
            newList.add(mapper.map(teacher, TeacherDto.class));
        }
        return new ResponseEntity<>(newList, HttpStatus.OK);
    }
}
