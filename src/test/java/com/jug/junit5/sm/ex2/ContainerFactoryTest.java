package com.jug.junit5.sm.ex2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ContainerFactoryTest {

	ContainerFactory factory;

	@BeforeEach
	void beforeEach() {
		factory = new ContainerFactory();
	}
	
	@Test
	@DisplayName("Factory should be initialized")
	void testIsNotNull(){
		assertThat( factory).isNotNull();
	}

	@Test
	@DisplayName("Should create a Dinky Container")
	void getDinkyContainer() throws ContainerNotFoundException {
		// @formatter:off
 		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("peanutButter"), 
				new Ingredient("banana"),
				new Ingredient("orange juice"));
		// @formatter:on
		
		IContainer container = factory.getContainer(ingredients);

		assertThat(container).isInstanceOf(Dinky.class);
	}

	@Test
	@DisplayName("Should NOT be able to create a container if there are no ingredients.")
	void getNullContainer() {
		List<Ingredient> ingredients = Collections.<Ingredient>emptyList();
		//AssertJ Exception Checking
		assertThatExceptionOfType(ContainerNotFoundException.class).isThrownBy(() -> {
			factory.getContainer(ingredients);
		}).withMessage("Unable to create Smoothie Ninja Container")
		  .withCause(new Throwable("Wrong number of Ingredients"));
	}
	
	@Test
	@DisplayName("Should NOT be able to create a container if there are too many ingredients.")
	void getNullContainerWithTooManyIngredients() {
		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("peanutButter"), 
				new Ingredient("banana"),
				new Ingredient("orange juice"),
				new Ingredient("apples"));
		//AssertJ Exception Checking
		assertThatExceptionOfType(ContainerNotFoundException.class).isThrownBy(() -> {
			factory.getContainer(ingredients);
		}).withMessage("Unable to create Smoothie Ninja Container")
		  .withCause(new Throwable("Wrong number of Ingredients"));
	}
}
