package com.peaksoft.dao.impl;

import com.peaksoft.dao.UserDao;
import com.peaksoft.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User findByUserName(String userName) {
        User user = (User) entityManager.createQuery("select u from User u where u.userName = ?1")
                .setParameter(1, userName).getSingleResult();
        return user;
    }
}
