package com.jug.junit5.sm.ex3;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IngredientTest {

	Ingredient ingredient;

	@BeforeEach
	void beforeEach() {
		ingredient = new Ingredient();
	}

	@Test
	void testNotNull() {
		assertThat(ingredient).as("Test that ingredient is created!").isNotNull();
	}

	@Test
	@Disabled("WIP")
	void skippedTest() {

	}
	
	@Test
	@DisplayName("We should be able to set the name of the ingredient")
	void testSetName() {
		ingredient.setName("peanut butter");
		assertThat( ingredient.getName() ).isEqualTo( "peanut butter");
	}

	@Test
	@DisplayName("Can set ingredients that taste like 💩")
	void testAwfulIngredients(){
		ingredient.setName("turnip");
		assertThat( ingredient.getName()).isEqualTo("turnip");
	}	
	
	@Test
	void constructWithName() {
		ingredient = new Ingredient( "spinach" );
		assertThat( ingredient.getName() ).isEqualTo( "spinach" );
	}
}
