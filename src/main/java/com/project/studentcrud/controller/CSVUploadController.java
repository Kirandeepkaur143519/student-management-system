package com.project.studentcrud.controller;

import com.opencsv.CSVReader;
import com.project.studentcrud.entity.Student;
import com.project.studentcrud.dao.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/students")
public class CSVUploadController {

    private final Logger logger = LoggerFactory.getLogger(CSVUploadController.class);

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/upload-form")
    public String showUploadForm() {
        return "upload-form";
    }

    @PostMapping("/upload-csv")
    public String uploadCSV(@RequestParam("file") MultipartFile file,
                            RedirectAttributes redirectAttributes) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] line;
            List<Student> studentList = new ArrayList<>();
            reader.readNext(); // skip header

            while ((line = reader.readNext()) != null) {
                if (line.length < 24) {
                    logger.warn("Skipping row due to missing columns: {}", Arrays.toString(line));
                    continue;
                }

                Student student = new Student();
                student.setName(safeGet(line, 0));
                student.setRollNo(safeGet(line, 1));
                student.setSemester(safeGet(line, 2));
                student.setEmail(safeGet(line, 3));
                student.setPhone(safeGet(line, 4));
                student.setGender(safeGet(line, 5));
                student.setCategory(safeGet(line, 6));
                student.setReligion(safeGet(line, 7));
                student.setFatherName(safeGet(line, 8));
                student.setMotherName(safeGet(line, 9));
                student.setParentPhone(safeGet(line, 10));
                student.setAddress(safeGet(line, 11));
                student.setDistrict(safeGet(line, 12));
                student.setState(safeGet(line, 13));
                student.setPinCode(safeGet(line, 14));
                student.setSchool10(safeGet(line, 15));
                student.setBoard10(safeGet(line, 16));
                student.setPercentage10(safeDouble(line, 17));
                student.setSchool12(safeGet(line, 18));
                student.setBoard12(safeGet(line, 19));
                student.setPercentage12(safeDouble(line, 20));
                student.setDegreeCollege(safeGet(line, 21));
                student.setOcpa(safeDouble(line, 22));
                student.setBatch(safeGet(line, 23).trim());
                studentList.add(student);
            }

            studentRepository.saveAll(studentList);
            redirectAttributes.addFlashAttribute("success", "✅ File uploaded successfully!");
        } catch (Exception e) {
            logger.error("CSV upload failed", e);
            redirectAttributes.addFlashAttribute("error", "❌ Upload failed. Please check file format.");
        }
        return "redirect:/students/upload-form";
    }

    private String safeGet(String[] arr, int index) {
        return (arr != null && index >= 0 && index < arr.length && arr[index] != null) ? arr[index] : "";
    }

    private Double safeDouble(String[] line, int index) {
        try {
            String value = line[index].trim().replace("%", "");
            return value.isEmpty() ? 0.0 : Double.parseDouble(value);
        } catch (Exception e) {
            logger.warn("Failed to parse double at index {}: {}", index, line[index]);
            return 0.0;
        }
    }
}

