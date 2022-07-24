package com.peaksoft.dao.impl;

import com.peaksoft.dao.StudentDAO;
import com.peaksoft.entity.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = entityManager.createQuery("from Student", Student.class).getResultList();
        Comparator<Student> comparator = ((o1, o2) -> (int) (o1.getId() - o2.getId()));
        students.sort(comparator);
        return students;
    }

    @Override
    public void saveStudent(Student student) {
        entityManager.persist(student);

    }

    @Override
    public Student getStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void deleteStudent(Student student) {
        entityManager.remove(entityManager.contains(student) ? student : entityManager.merge(student));
    }

    @Override
    public void updateStudent(Student student) {
        entityManager.merge(student);

    }

    @Override
    public List<Student> findStudentByName(String name) {
        List<Student> students = entityManager.createQuery(
                        "select s from Student s where s.firstName = ?1", Student.class)
                .setParameter(1, name).getResultList();
        return students;
    }

    @Override
    public List<Student> getStudentsByCompany(Long companyId) {
        List<Student>students=entityManager.createQuery(
                        "select s from Student s join Group g on g.id=s.group.id  join g.courses c   join Company com on com.id=c.company.id where com.id=?1",Student.class)
                .setParameter(1,companyId).getResultList();
        return students;
    }

}
