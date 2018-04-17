package com.jug.junit5.sm.ex3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContainerFactoryTest {

	ContainerFactory factory;

	@BeforeEach
	void beforeEach() {
		factory = new ContainerFactory();

		List<IContainer> containers = new ArrayList<IContainer>();
		// @formatter:off
		Collections.addAll(containers, 
				new Dinky(), new Dinky(),
				new Standard(), new Standard(), new Standard(),
				new Gargantuan(), new Gargantuan());
		// @formatter:on

		factory.setContainers(containers);
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
	void testHowContainersAvaialable() {
		assertThat(factory.howManyContainersAvailable()).isEqualTo(7);
	}

	@Test
	void testDinkyContainersAvailable() {
		assertThat(factory.areDinkyContainersAvaiable()).isEqualTo(2);
	}

	@Test
	void testStandardContainersAvailable() {
		assertThat(factory.areStandardContainersAvaiable()).isEqualTo(3);

	}

	@Test
	void testGargantuanContainersAvailable() {
		assertThat(factory.areGargantuanContainersAvaiable()).isEqualTo(2);
	}

	@Test
	@DisplayName("Should create a Standard Container")
	void getStandardContainer() throws ContainerNotFoundException {
		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("A"),
				new Ingredient("B"), 
				new Ingredient("C"),
				new Ingredient("D"));
		IContainer container = factory.getContainer(ingredients);

		assertThat(container).isInstanceOf(Standard.class);
	}

	@Test
	@DisplayName("Should create a Gargantuan Container")
	void getGargantuanContainer() throws ContainerNotFoundException {
		// @formatter:off
		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("A"),
				new Ingredient("B"), 
				new Ingredient("C"),
				new Ingredient("D"), 
				new Ingredient("E"), 
				new Ingredient("F"), 
				new Ingredient("G"), 
				new Ingredient("H"));
		// @formatter:on

		IContainer container = factory.getContainer(ingredients);

		assertThat(container).isInstanceOf(Gargantuan.class);
	}

	@Test
	void getAllDinkyContainers() throws ContainerNotFoundException {
		// @formatter:off
		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("A"), 
				new Ingredient("B"));
		// @formatter:on

		@SuppressWarnings("unused")
		IContainer container = factory.getContainer(ingredients);
		assertThat(factory.areDinkyContainersAvaiable()).isEqualTo(1);
		container = factory.getContainer(ingredients);
		assertThat(factory.areDinkyContainersAvaiable()).isEqualTo(0);
	}

	@Test
	void getADirtyContainer() throws ContainerNotFoundException {
		List<IContainer> containers = new ArrayList<IContainer>();
		Collections.addAll(containers, new Dinky());
		factory.setContainers(containers);
		
		assertThat(factory.areDinkyContainersAvaiable()).isEqualTo(1);
		List<Ingredient> ingredients = Arrays.asList(new Ingredient("A"));
		factory.getContainer(ingredients);
		assertThat(factory.areDinkyContainersAvaiable()).isEqualTo(0);
		
		assertThatExceptionOfType(ContainerNotFoundException.class).isThrownBy(() -> {
			factory.getContainer(ingredients);
		}).withMessage("Unable to create Dinky Smoothie Ninja Container")
		  .withCause(new Throwable("All containers are dirty"));
	}
	
	// Next Slide
	@Test
	@DisplayName("Only run tests if Dinky Containers are available.")
	void assumeDinkyContainersAreAvailable() throws ContainerNotFoundException {
		List<IContainer> containers = new ArrayList<IContainer>();
		Collections.addAll(containers, new Standard(), new Gargantuan());
		factory.setContainers(containers);

		// Proof there is no Dinky containers available.
		assertThat(factory.areDinkyContainersAvaiable()).isEqualTo(0);

		assumeTrue(factory.areDinkyContainersAvaiable() > 0, "No Dinky Containers Available");
		// Rest of the Test will be skipped because we're assuming we can get a dinky
		// container.
		// If the assumeTrue wasn't there, we'd throw a ContainerNotFoundExcpetion
		List<Ingredient> ingredients = Arrays.asList(new Ingredient("A"), new Ingredient("B"));
		factory.getContainer(ingredients);
	}
	// Next Slide

	// Run this test with a JUnit 4 runner in STS  Notice the 
	// Results window changing from JUnit 4 to JUnit5
	@org.junit.Test
	@Test
	@Tag("gargantuan")
	public void testTags() throws ContainerNotFoundException {
		factory = new ContainerFactory();
		List<Ingredient> ingredients = Arrays.asList(new Ingredient("A"), new Ingredient("B"), new Ingredient("C"),
				new Ingredient("D"),new Ingredient("E"),new Ingredient("F"),new Ingredient("G"));
		IContainer container = factory.getContainer(ingredients);
		assertThat(container).isInstanceOf(Gargantuan.class);
	}

	@Test
	@Integration
	void testSlowIntegrationTest() throws Throwable {
		log.info("Starting slow test");
		Thread.sleep(5000);
		assertThat( factory.howManyContainersAvailable() ).isEqualTo(7);
		log.info("Finished slow test");
	}

	// Next Slide
	// Alternatively I could test all the initialization in one test
	@Test
	void testFactoryInitialization() {
		assertAll("Container did not initialize correctly",
				() -> assertThat(factory.howManyContainersAvailable()).isEqualTo(7),
				() -> assertThat(factory.areDinkyContainersAvaiable()).isEqualTo(2),
				() -> assertThat(factory.areStandardContainersAvaiable()).isEqualTo(3),
				() -> assertThat(factory.areGargantuanContainersAvaiable()).isEqualTo(2));
	}
}
