package com.home.clouduser.service;


import com.home.clouduser.dao.UserDao;
import com.home.clouduser.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User queryById(Long id) {
        // if optional.isPresent() is false, it will throw NoSuchElementException
        return userDao.findById(id).get();
    }


}