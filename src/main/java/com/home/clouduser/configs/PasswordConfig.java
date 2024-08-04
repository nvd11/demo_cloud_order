package com.home.clouduser.configs;

// https://juejin.cn/post/7169101834349936654

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordConfig {

    @Autowired
    private StringEncryptor stringEncryptor;

    public String getEncryptedPassword(String password) {
        return this.stringEncryptor.encrypt(password);
    }

}
