package com.example.demo.controllers;

import com.example.demo.modelo.Student;
import com.example.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("list")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_GUEST')")
    public List<Student> getStudents(){
        return  this.studentService.getStudents();
    }

    @GetMapping("student/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_GUEST')")
    public Student getStudentById(@PathVariable("id") int id){
        return  this.studentService.findStudentById(id);
    }


    @PostMapping("add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean addStudent(@RequestBody Student student){
        return this.studentService.addStudent(student);
    }

    @GetMapping("insert")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean insertStudent(){
        return this.studentService.addStudent(new Student(7,"manolito"));
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean deleteando(@PathVariable("id") int idS){
        System.err.println("te permito borrar");
        studentService.deleteStudentById(idS);
        return true;
    }
}
