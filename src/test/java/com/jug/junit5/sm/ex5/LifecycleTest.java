package com.jug.junit5.sm.ex5;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@TestInstance(Lifecycle.PER_CLASS)
public class LifecycleTest {
	
	int majorVersion = 0;

	@Test
	void testCount1() {
		majorVersion++;
		assertThat(majorVersion).isEqualTo(1);
	}

	@Test
	void testCount2() {
		majorVersion++;
		assertThat(majorVersion).isEqualTo(2);
	}
	
	@Nested
	@DisplayName("Nested Class")
	@TestInstance(Lifecycle.PER_CLASS)
	class MinorVersion {
		int minorVersion = 0;
		
		@BeforeAll
		void minorBeforeAll(TestInfo info)
		{
			log.info("BeforeAll " + info.getDisplayName() );
		}
		
		@Test
		void testMinorVersion() {
			assertThat( getVersion()).isEqualTo("2.0");
		}
		
		@Test
		void testMiorVersion2() {
			minorVersion++;
			assertThat( getVersion()).isEqualTo("2.1");
		}
		
		private String getVersion() {
			return String.format( "%d.%d", majorVersion, minorVersion );
		}
	}
}
