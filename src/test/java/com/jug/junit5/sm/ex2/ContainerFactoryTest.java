package com.jug.junit5.sm.ex2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Arrays;
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
	@DisplayName("Should create a Dinky Container")
	void getDinkyContainer() throws ContainerNotFoundException {
		List<Ingredient> ingredients = Arrays.asList(new Ingredient("peanutButter"), new Ingredient("banana"),
				new Ingredient("orange juice"));
		IContainer container = factory.getContainer(ingredients);

		assertThat(container).isInstanceOfAny(IContainer.class);
		assertThat(container).isInstanceOf(Dinky.class);
	}

	@Test
	@DisplayName("Should NOT be able to create a container if ingredients are invalid.")
	void getNullContainer() {
		List<Ingredient> ingredients = Arrays.asList(new Ingredient("peanutButter"), new Ingredient("banana"),
				new Ingredient("spinanch"), new Ingredient("orange juice"));
		
		assertThatExceptionOfType(ContainerNotFoundException.class).isThrownBy(() -> {
			factory.getContainer(ingredients);
		}).withMessage("Unable to create Smoothie Ninja Container")
		.withCause(new Throwable("Wrong number of Ingredients"));
	}
}
