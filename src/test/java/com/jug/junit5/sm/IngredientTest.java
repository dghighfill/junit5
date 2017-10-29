package com.jug.junit5.sm;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IngredientTest {

	Ingredient ingredient;
	
	@BeforeEach
	void beforeEach() {
		ingredient = new Ingredient();
	}
	
	@Test
	void testNotNull() {
		assertThat( ingredient ).isNotNull();
	}
}
