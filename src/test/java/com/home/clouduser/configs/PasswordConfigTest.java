package com.home.clouduser.configs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class PasswordConfigTest {

    @Autowired PasswordConfig passwordConfig;
    @Test
    void getEncryptedPassword() {
        String password = "wxsdfsfdss";
        String encryptedPassword = passwordConfig.getEncryptedPassword(password);
        log.info("Encrypted password: {}", encryptedPassword);
        assertNotNull(encryptedPassword);
    }

}