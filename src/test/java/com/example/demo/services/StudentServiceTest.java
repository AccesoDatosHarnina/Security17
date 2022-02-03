package com.example.demo.services;

import com.example.demo.modelo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    StudentService studentService;

    @Test
    void findStudentById() {
        assertNotNull(studentService.findStudentById(1));
    }

    @Test
    void deleteStudentById() {
        studentService.deleteStudentById(2);
        assertEquals(studentService.getStudents().size(),1);
    }

    @Test
    void updateStudent() {
        String manolo = "Manolo";
        studentService.updateStudent(new Student(1, manolo));
        assertEquals(manolo,studentService.findStudentById(1).getName());
    }
}