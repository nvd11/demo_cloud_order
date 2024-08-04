package com.home.clouduser.monitor.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppVersionInfo implements InfoContributor {

    @Value("${pom.version}") // https://stackoverflow.com/questions/3697449/retrieve-version-from-maven-pom-xml-in-code
    private String appVersion;

    @Autowired
    private String hostname;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Override
    public void contribute(Info.Builder builder) {
        log.info("AppVersionInfo: contribute ...");
        builder.withDetail("app", "Cloud User API")
                .withDetail("version", appVersion)
                .withDetail("hostname",hostname)
                .withDetail("dbUrl", dbUrl)
                .withDetail("description", "This is a simple Spring Boot application to demonstrate the use of BigQuery in GCP.");
    }



}