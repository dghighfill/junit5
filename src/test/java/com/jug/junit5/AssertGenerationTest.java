package com.jug.junit5;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AssertGenerationTest {
	
	private String A = "a";
	@Test
	void firstGeneration() {
		assert( "a".equalsIgnoreCase( A ) );
	}
	
	@Test
	void secondGeneration() {
		assertEquals( "a", A );
	}
	
	@Test
	void thirdGeneration() {
		assertThat( A ).isEqualTo( "a" );
	}

}
