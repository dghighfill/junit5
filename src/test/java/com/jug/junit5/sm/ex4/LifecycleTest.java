package com.jug.junit5.sm.ex4;

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
@DisplayName("Major Version Test")
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
	@DisplayName("Minor Version Test")
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
			minorVersion++;
		}
		
		@Test
		void testMiorVersion2() {
			assertThat( getVersion()).isEqualTo("2.1");
			minorVersion++;
		}
		
		private String getVersion() {
			return String.format( "%d.%d", majorVersion, minorVersion );
		}
	}
}
