package com.jug.junit5.sm.ex5;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class IngredientTest {

	Ingredient ingredient;
	
	@BeforeEach
	void beforeEach() {
		ingredient = new Ingredient();
	}
	
	@Test
	void testIngredient() {
		assertThat( ingredient ).isNotNull();
		log.info( ingredient.toString() );
	}
	
	@Test
	void testSetName() {
		ingredient.setName("lombok");
		assertThat(ingredient.getName()).isEqualTo("lombok");
	}

	@Test
	void testSetOunces() {
		ingredient.setOunces( new Float(16) );
		assertThat(ingredient.getOunces()).isEqualTo(16);
	}
	
	@Test
	void testSetNullOunces() {
		assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> {
			ingredient.setOunces( null );
		}).withMessage("ounces");

	}
}
