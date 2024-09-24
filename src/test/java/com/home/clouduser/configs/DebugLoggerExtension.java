package com.home.clouduser.configs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class DebugLoggerExtension implements TestWatcher {


    @Override
    public void testSuccessful(ExtensionContext context) {
        log.info("Test successful: {}", context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        log.debug("Test failed: {}", context.getDisplayName(), cause);
    }
}