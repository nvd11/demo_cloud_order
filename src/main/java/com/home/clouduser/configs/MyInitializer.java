package com.home.clouduser.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@EnableAsync
public class MyInitializer implements InitializingBean {
    @Autowired
    private Environment environment;

    private void init() {
        log.info("Application started...");
        //setProxy();
        if (environment.getActiveProfiles().length > 0 && "dev".equals(environment.getActiveProfiles()[0])) {
            log.info("Setting proxy for dev environment...");
            setProxy();
        }
        printProxy();
    }

    private void setProxy() {
        System.setProperty("https.proxyHost", "10.0.1.223");
        System.setProperty("https.proxyPort", "7890");
    }

    private void printProxy(){
       log.info("https.proxyHost: " + System.getProperty("https.proxyHost"));
       log.info("https.proxyPort: " + System.getProperty("https.proxyPort"));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.init();
    }
}
