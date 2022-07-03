package com.kupercy.service.impl;

import com.kupercy.dao.UserDao;
import com.kupercy.dto.UserRegisterRequest;
import com.kupercy.model.User;
import com.kupercy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }
}
