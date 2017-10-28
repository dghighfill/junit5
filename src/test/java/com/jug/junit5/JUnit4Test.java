package com.jug.junit5;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class JUnit4Test {

	private int VALUE = 4; 
	@Test
	public void testJUnit4() {
		assertThat( VALUE).isEqualTo( 4 );
	}

}
