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

//    public ResponseEntity<StudentDto> registerStudent(StudentDto studentDto) {
//        Student studentByEmail = studentsRepository.findStudentByEmail(studentDto.getEmail());
//        Student newStudent;
//        if (studentByEmail == null) {
//            Student student;
//            student = mapper.map(studentDto, Student.class);
//            student.setRegistrationDate(LocalDateTime.now());
//            newStudent = studentsRepository.save(student);
//        } else {
//            throw new UserNotFoundException("Student with " + studentDto.getEmail() + " already exists!");
//        }
//        return new ResponseEntity<>(mapper.map(newStudent, StudentDto.class), HttpStatus.CREATED);
//    }

    @Override
    public ResponseEntity<TeacherDto> editTeacher(TeacherDto teacherDto, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteTeacher(Long id) {
        return null;
    }
}
