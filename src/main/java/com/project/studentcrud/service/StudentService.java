package com.project.studentcrud.service;

import com.project.studentcrud.entity.Student;

import java.util.List;

public interface StudentService {

        List<Student> getAllStudents();

        Student getStudentById(Long id);

        List<Student> searchByNameOrId(String query);

        void saveStudent(Student student);

        void deleteStudentById(Long id);

        void deleteAll();

        void resetAutoIncrement();

        List<Student> findByBatch(String batch);

}
