package com.home.clouduser.controller;

import com.home.clouduser.service.InfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private String hostname;

    @GetMapping("/hostname")
    public ResponseEntity<String> getHostName() {
        if (hostname == null) {
            return ResponseEntity.notFound().build();
        }
        String returnStr = "the service is running on: " + hostname;
        //log.info(returnStr);
        return ResponseEntity.ok("the service is running on: " + hostname);
    }
}
