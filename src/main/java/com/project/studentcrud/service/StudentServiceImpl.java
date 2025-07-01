package com.project.studentcrud.service;

import com.project.studentcrud.dao.StudentRepository;
import com.project.studentcrud.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Student> searchByNameOrId(String query) {
        try {
            Long id = Long.parseLong(query);
            Optional<Student> student = studentRepository.findById(id);
            return student.map(List::of).orElse(List.of());
        } catch (NumberFormatException e) {
            return studentRepository.findByNameContainingIgnoreCase(query);
        }
    }

    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        studentRepository.deleteAll();
    }

    @Override
    public void resetAutoIncrement() {
        studentRepository.resetAutoIncrement();
    }

    @Override
    public List<Student> findByBatch(String batch) {
        return studentRepository.findByBatch(batch);
    }

}
