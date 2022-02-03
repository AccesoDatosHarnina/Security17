package com.example.demo.controllers;

import com.example.demo.modelo.Student;
import com.example.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("list")
    public List<Student> getStudents(){
        return  this.studentService.getStudents();
    }

    @GetMapping("student/{id}")
    public Student getStudentById(@PathVariable("id") int id){
        return  this.studentService.findStudentById(id);
    }
    @PostMapping
    public boolean addStudent(Student student){
        return this.studentService.addStudent(student);
    }
}
