package com.home.clouduser.dao;

import com.home.clouduser.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;


@SpringBootTest
@Slf4j
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    @Test
    void findByUsername() {
        log.info("Test findByUsername");
        User liuyan = userDao.findByUsername("柳岩");
        log.info("User: {}", liuyan);
        Assertions.assertNotNull(liuyan);
    }

    @Test
    void findById() {
        log.info("Test findById");
        Optional<User> optional = userDao.findById(2L);
        User user = null;
        if (optional.isPresent()) {
           user = optional.get();
        }
        log.info("User: {}", user);
        Assertions.assertNotNull(user);
    }
}
