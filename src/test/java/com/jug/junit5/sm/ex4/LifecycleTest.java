package com.jug.junit5.sm.ex4;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
	
	@BeforeAll
	void beforeAll( TestInfo info ) {
		log.info(String.format( "%s BeforeAll: majorVersion %d ", info.getDisplayName(), this.majorVersion  ));
	}
	
	@BeforeEach
	void beforeEach( TestInfo info ) {
		log.info(String.format( "%s BeforeEach: majorVersion %d ", info.getDisplayName(), this.majorVersion  ));
	}

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
		
		@BeforeAll  // Only can be used with @Nested class with Lifecycle.PER_CLASS
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
		void testMinorVersion2() {
			assertThat( getVersion()).isEqualTo("2.1");
			minorVersion++;
		}
		
		@Test
		void testMajorMinorVersion2() {
			majorVersion++;
			assertThat( getVersion()).isEqualTo("3.2");
			
		}
		private String getVersion() {
			return String.format( "%d.%d", majorVersion, minorVersion );
		}
	}
}
