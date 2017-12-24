package com.jug.junit5;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TitleSlideTest {

	private String speaker = "Dale Highfill";
	private int slideCount = 35;

	@Test
	@DisplayName("Your presenter is")
	void testForGreatSpeakers() {
		assertThat(speaker).isEqualTo("Dale Highfill");
	}

	// Note this test is made to intentionally fail because I don't plan 
	// to bore them to death with 142 slides.
	@Test
	@DisplayName("Should be a long night")
	void testHowManySlides() {
		assertThat(slideCount).isEqualTo(142);
	}

}
