package com.project.studentcrud.dao;

import com.project.studentcrud.entity.Student;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameContainingIgnoreCase(String name);
    List<Student> findByBatch(String batch);


    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE students AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

    @Query("SELECT DISTINCT s.batch FROM Student s ORDER BY s.batch ASC")
    List<String> findDistinctBatches();


}
