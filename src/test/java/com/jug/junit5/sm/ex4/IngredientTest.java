package com.jug.junit5.sm.ex4;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

@DisplayName("Ingrediant class tests")
class IngredientTest {

	Ingredient ingredient;

	@BeforeEach
	void beforeEach() {
		ingredient = new Ingredient();
	}

	void testNotNull() {
		assertThat(ingredient).as("Test that ingredient is created!").isNotNull();
	}

	@Test
	@DisplayName("Can set ingredients that taste like 💩")
	void testAwfulIngredients(){
		ingredient.setName("turnip");
		assertThat( ingredient.getName()).isEqualTo("turnip");
	}	
	
	@Test
	void constructWithName() {
		ingredient = new Ingredient("spinach", 4);
		assertThat(ingredient.getName()).isEqualTo("spinach");
		assertThat(ingredient.getOunces()).isEqualTo(4);
	}

	@Nested
	@DisplayName("Ingrediant attribute tests")
	class IngrediantAttributeTest {

		@Test
		@DisplayName("We should be able to set the name of the ingredient")
		void testSetName() {
			ingredient.setName("peanut butter");
			assertThat(ingredient.getName()).isEqualTo("peanut butter");
		}

		@Test
		@DisplayName("We should be able to set the ounces of the ingredient")
		void testSetOunces() {
			ingredient.setOunces(16);
			assertThat(ingredient.getOunces()).isEqualTo(16);
		}

		// Dependency Injection
		@Test
		@DisplayName("A Test Info")
		void assertTestInfoWithDiplayName(TestInfo info) {
			assertThat(info.getDisplayName()).isEqualTo("A Test Info");
			assertThat(info.getTestMethod().get().getName()).isEqualTo("assertTestInfoWithDiplayName");
		}

		@Test
		@DisplayName("Why would I ever do this?")
		void reportSeveralValues(TestReporter reporter) {
			ingredient.setName("Key Ingredient");
			ingredient.setOunces(16);
			reporter.publishEntry(ingredient.getName(), Float.toString(ingredient.getOunces()));
		}

		@RepeatedTest(5)
		void testCreatingSeveralIngredients(RepetitionInfo info) {
			System.out.println(String.format("Repeat %d of %d", info.getCurrentRepetition()
					                                          , info.getTotalRepetitions()));
		}

		@RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME )
		void testRepeatAnnotation(TestInfo info) {
			System.out.println(String.format(info.getDisplayName() ));
		}
		
		@RepeatedTest(value = 1, name = "{displayName} {currentRepetition}/{totalRepetitions}")
		@DisplayName("Repeat!")
		void customDisplayName(TestInfo testInfo) {
			assertThat(testInfo.getDisplayName()).isEqualTo( "Repeat! 1/1");
		}
		
	    @TestFactory
	    Collection<DynamicTest> dynamicTests() {
			ingredient.setName("Key Ingredient");
			ingredient.setOunces(16);
	        return Arrays.asList(
	            dynamicTest("dyanmic testSetName", () -> assertThat( ingredient.getName()).isEqualTo("Key Ingredient")),
	            dynamicTest("dynamic testSetOunces", () -> assertThat( ingredient.getOunces()).isEqualTo( 16 ) ));
	    }
	}

}
