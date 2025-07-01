package com.project.studentcrud.controller;

import com.project.studentcrud.entity.Student;
import com.project.studentcrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // API controller — returns JSON, not HTML
@RequestMapping("/api/students")
public class StudentApiController {

    @Autowired
    private StudentService studentService;

    // GET /api/students - return all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // GET /api/students/{id} - get student by ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // POST /api/students - save new student
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
        return student;
    }

    // PUT /api/students/{id} - update student
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        student.setId(id);
        studentService.saveStudent(student);
        return student;
    }

    // DELETE /api/students/{id} - delete student
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
    }
}
