package com.jug.junit5;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class TitleSlideTest {

	private String speaker = "Dale Highfill";
	private int slideCount = 54;

	@Test
	void testShouldALongNight() {
		assertThat(speaker).isEqualTo("Dale Highfill");
	}

	@Test
	void testShouldBeALongPresentation() {
		assertThat(slideCount).isEqualTo(54);
	}
}
