package com.peaksoft.dao.impl;

import com.peaksoft.dao.CourseDAO;
import com.peaksoft.dao.TeacherDAO;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class TeacherDaoImpl implements TeacherDAO {
    private final CourseDAO courseDAO;
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public TeacherDaoImpl(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher>teachers=entityManager.createQuery("from Teacher",Teacher.class).getResultList();
        Comparator<Teacher>comparator=((o1, o2) -> (int) (o1.getId()-o2.getId()));
        teachers.sort(comparator);
        return teachers;
    }

    @Override
    public void saveTeacher(Teacher teacher, Long courseId) {
        Course course = courseDAO.getCourseById(courseId);
        teacher.setCourse(course);
        entityManager.merge(teacher);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return entityManager.find(Teacher.class,id);
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        entityManager.remove(entityManager.contains(teacher)?teacher:entityManager.merge(teacher));

    }

    @Override
    public void updateTeacher(Teacher teacher) {
        entityManager.merge(teacher);

    }
}
