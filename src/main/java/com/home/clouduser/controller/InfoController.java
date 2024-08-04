package com.home.clouduser.controller;

import com.home.clouduser.entities.User;
import com.home.clouduser.service.InfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @GetMapping("/{infoName}")
    public String getInfoValue(@PathVariable("infoName") String id) {
        return infoService.getHostName();
    }
}
