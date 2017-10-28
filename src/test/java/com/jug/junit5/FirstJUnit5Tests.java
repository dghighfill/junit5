package com.jug.junit5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class FirstJUnit5Tests {

	@Test
	void test() {
		assertEquals(4, 1 + 3);
	}
	
	@RepeatedTest(name="The Repeat Name", value=10 )
	@DisplayName("This is the name")
	void repeatTest() {
		assertEquals( 3, 2 + 1, "These didn't equal");
		
	}

}
