package com.home.clouduser.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InfoService {

        public String getHostName() {
            return "unknown";
        }

        public HashMap<String, String> getSystemVariables() {
            Map<String, String> envVariables = System.getenv();

            //envVariables.forEach((key, value) -> systemVariables.put(key, value));
            return new HashMap<>(envVariables);
        }
}
