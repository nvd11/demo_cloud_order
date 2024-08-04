package com.home.clouduser.controller;

import com.home.clouduser.dto.ApiResponse;
import com.home.clouduser.entities.User;
import com.home.clouduser.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id) {
        return userService.queryById(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<User>> queryByIdResponseEntity(@PathVariable("id") Long id) {
        User user = null;
        try {
            user = userService.queryById(id);
            ApiResponse<User> response = new ApiResponse<>();
            response.setData(user);
            response.setReturnCode(0);
            response.setReturnMsg("User fetched successfully ...");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error in queryById...", e);
            ApiResponse<User> response = new ApiResponse<>();
            response.setReturnCode(-1);
            response.setReturnMsg("Error in fetching user: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
