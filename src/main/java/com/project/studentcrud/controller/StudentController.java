package com.project.studentcrud.controller;

import com.project.studentcrud.dao.StudentRepository;
import com.project.studentcrud.entity.Student;
import com.project.studentcrud.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/students")
public class StudentController {

    private final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    // GET /students - List all students
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("batches", studentRepository.findDistinctBatches());
        return "list-students";
    }


    @GetMapping("/batch")
    public String getStudentsByBatch(@RequestParam("batch") String batch, Model model) {
        List<Student> students = studentService.findByBatch(batch);
        List<String> batches = studentRepository.findDistinctBatches();

        model.addAttribute("students", students);
        model.addAttribute("batches", batches); // for dropdown
        model.addAttribute("selectedBatch", batch); // for selected option
        return "list-students";
    }


    // GET /students/add
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    // GET /students/edit/{id}
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "add-student";
    }

    // POST /students/save
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    // GET /students/delete/{id}
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

    // POST /students/deleteAll
    @PostMapping("/deleteAll")
    public String deleteAllStudents(RedirectAttributes redirectAttributes) {
        studentService.deleteAll();
        studentService.resetAutoIncrement();
        redirectAttributes.addFlashAttribute("message", "✅ All students deleted and ID reset.");
        return "redirect:/students";
    }

}

