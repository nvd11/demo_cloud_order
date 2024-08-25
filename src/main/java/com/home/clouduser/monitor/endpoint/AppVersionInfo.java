package com.home.clouduser.monitor.endpoint;

import com.home.clouduser.configs.DynamicExternalConfig;
import com.home.clouduser.service.InfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppVersionInfo implements InfoContributor {

    @Value("${pom.version}") // https://stackoverflow.com/questions/3697449/retrieve-version-from-maven-pom-xml-in-code
    private String appVersion;

    @Autowired
    private String hostname;

    @Autowired
    private InfoService infoservice;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.profiles.active}")
    private String appEnvProfile;

    @Autowired
    private String customConfig1;

    @Autowired
    private DynamicExternalConfig dynamicExternalConfig;


    @Override
    // https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html#production-ready-endpoints-info
    public void contribute(Info.Builder builder) {
        log.info("AppVersionInfo: contribute ...");
        builder.withDetail("app", "Cloud Order Service")
                .withDetail("appEnvProfile", appEnvProfile)
                .withDetail("version", appVersion)
                .withDetail("hostname",hostname)
                .withDetail("dbUrl", dbUrl)
                .withDetail("description", "This is a simple Spring Boot application to for cloud order...")
                .withDetail("customConfig1", customConfig1)
                .withDetail("customConfig2", dynamicExternalConfig.getCustomConfig())
                .withDetail("SystemVariables", infoservice.getSystemVariables());

    }
}