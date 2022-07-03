package com.kupercy.service;

import com.kupercy.dto.UserRegisterRequest;
import com.kupercy.model.User;

public interface UserService {

    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);
}
