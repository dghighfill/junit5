package com.jug.junit5;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class AssertGenerationTest {
	
	// To see the Assertion messages, change the value to "b" and run the tests
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
		// Comment out the first one to show the difference.
		assertThat( A ).as("Making sure my strings match").isEqualTo( "a" );
		assertThat( A ).as("Making sure my strings match").contains( "a" );
	}
	@Test
	void thirdGenerationList() {
		List<String> ingredients = Arrays.asList("blueberries", "strawberries" );
		assertThat( ingredients ).size().isEqualTo(2);
		assertThat( ingredients ).contains("strawberries");
		assertThat( ingredients ).doesNotContain("nuts");
	}

}
