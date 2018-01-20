package com.jug.junit5.sm.ex5;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.fail;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	@Disabled("WIP")
	void skippedTest() {
		// Deactive disabled tests by using this VM argument
		// -Djunit.jupiter.conditions.deactivate=org.junit.*DisabledCondition
		fail("This test is work in progress");
	}
	
	@Test
	@DisplayName("Can set ingredients that taste like 💩")
	void testAwfulIngredients(){
		ingredient.setName("turnip");
		assertThat( ingredient.getName()).isEqualTo("turnip");
	}	
	
	@Test
	void constructWithName() {
		ingredient = new Ingredient("spinach", 4f);
		assertThat(ingredient.getName()).isEqualTo("spinach");
		assertThat(ingredient.getOunces()).isEqualTo(4);
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
			ingredient.setOunces(16f);
			assertThat(ingredient.getOunces()).isEqualTo(16);
		}

		@Test
		@Disabled
		@DisplayName("A Test Info")
		void assertTestInfoWithDiplayName(TestInfo info) {
			assertThat(info.getDisplayName()).isEqualTo("A Test Info");
			assertThat(info.getTestMethod().get().getName()).isEqualTo("assertTestInfoWithDiplayName");
		}

		@Test
		@Disabled
		@DisplayName("Why would I ever do this?")
		void reportSeveralValues(TestReporter reporter) {
			ingredient.setName("Key Ingrediant");
			ingredient.setOunces(16f);
			reporter.publishEntry(ingredient.getName(), Float.toString(ingredient.getOunces()));
		}

		@RepeatedTest(5)
		@Disabled

		void testCreatingSeveralIngredients(RepetitionInfo info) {
			System.out.println(String.format("Repeat %d of %d", info.getCurrentRepetition()
					                                          , info.getTotalRepetitions()));
		}

		@RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME )
		@Disabled
		void testRepeatAnnotation(TestInfo info) {
			System.out.println(String.format(info.getDisplayName() ));
		}
		
		@RepeatedTest(value = 1, name = "{displayName} {currentRepetition}/{totalRepetitions}")
		@Disabled
		@DisplayName("Repeat!")
		void customDisplayName(TestInfo testInfo) {
			assertThat(testInfo.getDisplayName()).isEqualTo( "Repeat! 1/1");
		}
		
	    @TestFactory
	    Collection<DynamicTest> dynamicTests() {
			ingredient.setName("Key Ingredient");
			ingredient.setOunces(16f);
	        return Arrays.asList(
	            dynamicTest("dyanmic testSetName", () -> assertThat( ingredient.getName()).isEqualTo("Key Ingredient")),
	            dynamicTest("dynamic testSetOunces", () -> assertThat( ingredient.getOunces()).isEqualTo( 16 ) ));
	    }
	}
	
}
