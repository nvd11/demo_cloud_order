package com.home.clouduser;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

//@SpringBootTest
@Slf4j
@TestPropertySource(locations = "classpath:application-dev.yml")
class DemoCloudUserApplicationTests {

	@Test
	void contextLoads() {
		log.info("contextLoads");
		log.debug("contextLoads");
	}

}
