package com.peaksoft.dao;

import com.peaksoft.entity.User;

public interface UserDao {
    User findByUserName(String userName);
}
