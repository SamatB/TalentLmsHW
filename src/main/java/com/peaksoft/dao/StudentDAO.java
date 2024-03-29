package com.peaksoft.dao;


import com.peaksoft.entity.Group;
import com.peaksoft.entity.Student;

import java.util.List;

public interface StudentDAO {
    List<Student> getAllStudents();

    void saveStudent(Student student, Long groupId);

    Student getStudentById(Long id);

    void deleteStudent(Student student);

    void updateStudent(Student student);

    List <Student> findStudentByName(String name);

    List<Student> getStudentsByCompany(Long companyId);
}
