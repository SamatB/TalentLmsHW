package com.peaksoft.service.impl;

import com.peaksoft.dao.StudentDAO;
import com.peaksoft.entity.Student;
import com.peaksoft.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentDAO studentDAO;

    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    @Override
    public void saveStudent(Student student, Long groupId) {
        studentDAO.saveStudent(student, groupId);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentDAO.getStudentById(id);
    }

    @Override
    public void deleteStudent(Student student) {
        studentDAO.deleteStudent(student);
    }

    @Override
    public void updateStudent(Student student, long id) {
        studentDAO.updateStudent(student);
    }

    @Override
    public List<Student> findStudentByName(String name) {
        return studentDAO.findStudentByName(name);
    }

    @Override
    public List<Student> getStudentsByCompany(Long companyId) {
        return studentDAO.getStudentsByCompany(companyId);
    }
}
