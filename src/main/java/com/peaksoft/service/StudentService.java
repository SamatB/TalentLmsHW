package com.peaksoft.service;

import com.peaksoft.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    void saveStudent(Student student, Long groupId);
    Student getStudentById(Long id);
    void deleteStudent(Student student);
    void updateStudent(Student student, long id);
    List <Student> findStudentByName(String name);

    List<Student> getStudentsByCompany(Long companyId);
}
