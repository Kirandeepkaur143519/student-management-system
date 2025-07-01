package com.project.studentcrud.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Data // Lombok will generate getters, setters, toString, equals, and hashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "roll_no")
    private String rollNo;

    @Column(name = "semester")
    private String semester;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "gender")
    private String gender;

    @Column(name = "category")
    private String category;

    @Column(name = "religion")
    private String religion;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "parent_phone")
    private String parentPhone;

    @Column(name = "address")
    private String address;

    @Column(name = "district")
    private String district;

    @Column(name = "state")
    private String state;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "school10")
    private String school10;

    @Column(name = "board10")
    private String board10;

    @Column(name = "percentage10")
    private Double percentage10;

    @Column(name = "school12")
    private String school12;

    @Column(name = "board12")
    private String board12;

    @Column(name = "percentage12")
    private Double percentage12;

    @Column(name = "degree_college")
    private String degreeCollege;

    @Column(name = "ocpa")
    private Double ocpa;

    @Column(name = "batch")
    private String batch;
}
