package com.jug.junit5;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class TitleSlideTest {

	private String speaker = "Dale Highfill";
	private int slideCount = 44;

	@Test
	void testForPresenter() {
		assertThat(speaker).isEqualTo("Dale Highfill");
	}

	// Note this test is made to intentionally fail because I don't plan 
	// to bore them to death with 142 slides.
	@Test
	void testShouldBeALongPresentation() {
		assertThat(slideCount).isEqualTo(44);
	}

}
