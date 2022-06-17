package com.example.learning.service.serviceImpl;

import com.example.learning.dto.StudentDto;
import com.example.learning.entity.Student;
import com.example.learning.entity.Teacher;
import com.example.learning.exception.UserNotFoundException;
import com.example.learning.repository.StudentsRepository;
import com.example.learning.repository.TeachersRepository;
import com.example.learning.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentsRepository studentsRepository;
    private final TeachersRepository teachersRepository;
    private final ModelMapper mapper;

    @Autowired
    public StudentServiceImpl(StudentsRepository studentsRepository, TeachersRepository teachersRepository, ModelMapper mapper) {
        this.studentsRepository = studentsRepository;
        this.teachersRepository = teachersRepository;
        this.mapper = mapper;
    }


    @Override
    public ResponseEntity<StudentDto> registerStudent(Long id, StudentDto studentDto) {
        Student studentByEmail = studentsRepository.findStudentByEmail(studentDto.getEmail());
        Teacher teacher = teachersRepository.findById(id).orElseThrow(() -> new UserNotFoundException("teacher does not exist"));
        Student newStudent;
        if (studentByEmail == null) {
            Student student;
            student = mapper.map(studentDto, Student.class);
            student.setRegistrationDate(LocalDateTime.now());
            student.setTeacher(teacher);
            newStudent = studentsRepository.save(student);
        } else {
            throw new UserNotFoundException("Student with " + studentDto.getEmail() + " already exists!");
        }
        return new ResponseEntity<>(mapper.map(newStudent, StudentDto.class), HttpStatus.CREATED);
    }


//    public ResponseEntity<List<StudentDto>> getAllStudents() {
//        List<Student> studentList = studentsRepository.findAll();
//        return (ResponseEntity<List<StudentDto>>) studentList.stream().map(student -> mapper.map(studentList, StudentDto.class)).collect(Collectors.toList());
//    }

    @Override
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> newList = new ArrayList<>();
        List<Student> studentList = studentsRepository.findAll();
        for(Student student: studentList){
            newList.add(mapper.map(student, StudentDto.class));
        }
        return new ResponseEntity<>(newList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StudentDto> updateStudent(StudentDto studentDto, Long id) {
        Student student = studentsRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Student not found"));
        student.setRegistrationDate(LocalDateTime.now());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(student.getEmail());
        student.setLevel(student.getLevel());
        student.setAge(student.getAge());
        student.setDob(studentDto.getDob());
        student.setGender(studentDto.getGender());
        student.setPassword(student.getPassword());

        Student student1 = studentsRepository.save(student);
        return new ResponseEntity<>(mapper.map(student1, StudentDto.class), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> deleteStudent(Long id) {
        Student student = studentsRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Student with " + id +  " does not exist"));
        studentsRepository.delete(student);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }


}
