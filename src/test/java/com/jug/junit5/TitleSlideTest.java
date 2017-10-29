package com.jug.junit5;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TitleSlideTest {

	private List<String> speakers = Arrays.asList("Frank Motley", "Dale Highfill");
	private int slideCount = 35;

	@Test
	@DisplayName("Your presenters tonight are")
	void testForGreatSpeakers() {
		assertThat(speakers.get(0)).isEqualTo("Frank Motley");
		assertThat(speakers.get(1)).isEqualTo("Dale Highfill");
	}

	// Note this test is made to intentionally fail because we bore them to death with
	// 142 slides.
	@Test
	@Disabled
	@DisplayName("Should be a long night")
	void testHowManySlides() {
		assertThat(slideCount).isEqualTo(142);
	}

}
