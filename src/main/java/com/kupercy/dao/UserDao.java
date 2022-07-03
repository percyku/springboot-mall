package com.kupercy.dao;

import com.kupercy.dto.UserRegisterRequest;
import com.kupercy.model.User;

public interface UserDao {

    User getUserById(Integer userId);
    Integer createUser(UserRegisterRequest userRegisterRequest);
}
