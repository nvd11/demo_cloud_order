package com.home.clouduser.configs;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration("dynamicExternalConfig")
@PropertySource(value = "file:${external-custom-config2-path}", ignoreResourceNotFound = true)
@Slf4j
public class DynamicExternalConfig {

    private Properties properties = new Properties();

    // could not add @Bean otherwise the call from AppVersionInfo will not make a refresh call
    @Getter
    @Value("${external.custom.config2:not defined}")
    private String customConfig;

    @Value("${external-custom-config2-path}")
    private String externalConfigPath;


    @Scheduled(fixedRate = 6000) // Refresh every 6 seconds
    public void refreshConfig() {
        try {
            //log.info("trying to refresh configuration customConfig2");
            FileInputStream fis = new FileInputStream(externalConfigPath);
            properties.clear();
            properties.load(fis);
            fis.close();
            customConfig = properties.getProperty("external.custom.config2", "not defined");
        } catch (IOException e) {
            log.error("failed to refresh configuration customConfig2", e);
            //throw new RuntimeException(e);
        }catch (Exception e) {
            log.error("failed to refresh configuration customConfig2, and it's not an IO exception", e);
            throw new RuntimeException(e);
        }
    }
}
