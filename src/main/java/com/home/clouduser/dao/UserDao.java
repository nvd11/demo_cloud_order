package com.home.clouduser.dao;

import com.home.clouduser.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
   * JpaRepository <T, ID>, JpaRepository provide some default crud methods,
   * T means the Entity Class,
   * ID means the data type of private key , or the attrible which has @Id

 */
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
