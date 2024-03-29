package com.peaksoft.service;

import com.peaksoft.entity.Course;
import com.peaksoft.entity.Group;

import java.util.List;

public interface CourseService {
    List<Course>getAllCourse();
    void addCourse(Course course,Long companyId);
    Course getCourseById(Long id);
    void updateCourse(Course course,Long id);
    void deleteCourse(Course course);
    List<Group> getGroupsByCourse(Long courseId);

}
