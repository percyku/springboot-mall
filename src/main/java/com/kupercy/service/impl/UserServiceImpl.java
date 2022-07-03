package com.kupercy.service.impl;

import com.kupercy.dao.UserDao;
import com.kupercy.dto.UserLoginRequest;
import com.kupercy.dto.UserRegisterRequest;
import com.kupercy.model.User;
import com.kupercy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;


    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        //check register email
        User user =userDao.getUserByEmail(userRegisterRequest.getEmail());

        if(user != null){
            log.warn("This email {} had been registered.{}",userRegisterRequest.getEmail(),"USER");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // using MD5 product password Hash
        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashedPassword);

        //create user account
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user =userDao.getUserByEmail(userLoginRequest.getEmail());
        //check user is existing or not
        if(user == null){
            log.warn("This email {} is not registered.{}",userLoginRequest.getEmail(),"USER");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // using MD5 product password Hash
        String hashedPassword =  DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());

        //check password is correct or not
        if(user.getPassword().equals(hashedPassword)){
            return user;
        }
        else{
            log.warn("This email's password {} is not correct.{}",userLoginRequest.getEmail(),"USER");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


    }
}
