package com.home.clouduser.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


// this
@Configuration
@PropertySource(value = "file:${external-custom-config1-path}", ignoreResourceNotFound = true)
public class ExternalConfig {

    // define
    @Bean("customConfig1")
    public String customConfig() {
        return getCustomConfig();
    }

    @Value("${external.custom.config1:not defined1}")
    private String customConfig;

    // to
    private String getCustomConfig() {
        return this.customConfig;
    }

}
