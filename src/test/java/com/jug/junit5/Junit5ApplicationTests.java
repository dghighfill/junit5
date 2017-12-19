package com.jug.junit5;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//Comment this out until we're ready for the Spring tests.
//@RunWith(SpringRunner.class) // This is a JUnit4 Runner.  You don't need this
//generated code to run with a JUnit5 runner.
@SpringBootTest
public class Junit5ApplicationTests {

	@Disabled
	@Test
	public void contextLoads() {
		assertThat("true").isEqualTo("true");
	}
}
