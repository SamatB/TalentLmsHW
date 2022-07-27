package com.peaksoft.service;

import com.peaksoft.entity.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeachers();
    void saveTeacher(Teacher teacher, Long courseId);
    Teacher getTeacherById(Long id);
    void deleteTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher, long id);
}
