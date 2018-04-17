package com.jug.junit5.sm.ex5;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import com.jug.junit5.MockitoExtension;
import com.jug.junit5.sm.ex5.ContainerNotFoundException;
import com.jug.junit5.sm.ex5.Dinky;
import com.jug.junit5.sm.ex5.Gargantuan;
import com.jug.junit5.sm.ex5.IContainer;
import com.jug.junit5.sm.ex5.Ingredient;
import com.jug.junit5.sm.ex5.Standard;

@ExtendWith(MockitoExtension.class)
public class ContainerFactoryTest {

	ContainerFactory factory;
	
	@Mock List<Ingredient> mockIngredents;

	@BeforeEach
	void beforeEach() {
		factory = new ContainerFactory();
		
		List<IContainer> containers = new ArrayList<IContainer>();
		Collections.addAll(containers, new Dinky(), new Dinky()
			, new Standard(), new Standard(), new Standard()
			, new Gargantuan(), new Gargantuan());
		factory.setContainers(containers);
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
	void testExceptionWithNoIngredients() {
		List<Ingredient> ingredients = Collections.<Ingredient>emptyList();
		
		assertThatExceptionOfType(ContainerNotFoundException.class).isThrownBy(() -> {
			factory.getContainer(ingredients);
		}).withMessage("Unable to create Smoothie Ninja Container")
		.withCause(new Throwable("Wrong number of Ingredients"));
	}
	
	@Test
	void testHowContainersAvaialable() {
		assertThat( factory.howManyContainersAvailable() ).isEqualTo(7);
	}
	
	@Test
	void testDinkyContainersAvailable() {
		assertThat( factory.areDinkyContainersAvaiable() ).isEqualTo(2);
	}
	
	@Test
	void testStandardContainersAvailable() {
		assertThat( factory.areStandardContainersAvaiable() ).isEqualTo(3);
		
	}
	
	@Test
	void testGargantuanContainersAvailable() {
		assertThat( factory.areGargantuanContainersAvaiable() ).isEqualTo(2);
	}
	
	// Alternatively I could test all the initialization in one test
	@Test
	void testFactoryInitialization() {
		assertAll("Container did not initialize correctly",
				() -> assertThat( factory.howManyContainersAvailable() ).isEqualTo(7),
				() -> assertThat( factory.areDinkyContainersAvaiable() ).isEqualTo(2),
				() -> assertThat( factory.areStandardContainersAvaiable() ).isEqualTo(3),
				() -> assertThat( factory.areGargantuanContainersAvaiable() ).isEqualTo(2));
	}
	
	@Test
	@DisplayName("Should create a Standard Container")
	void getStandardContainer() throws ContainerNotFoundException {
		List<Ingredient> ingredients = Arrays.asList(new Ingredient("A"), new Ingredient("B"),
				new Ingredient("C"), new Ingredient("D"));
		IContainer container = factory.getContainer(ingredients);

		assertThat(container).isInstanceOf(Standard.class);
	}
	@Test
	@DisplayName("Should create a Gargantuan Container")
	void getGargantuanContainer() throws ContainerNotFoundException {
		List<Ingredient> ingredients = Arrays.asList(new Ingredient("A"), new Ingredient("B"),
				new Ingredient("C"), new Ingredient("D"),
				new Ingredient("E"), new Ingredient("F"),
				new Ingredient("G"), new Ingredient("H"));
		IContainer container = factory.getContainer(ingredients);

		assertThat(container).isInstanceOf(Gargantuan.class);
	}

	@Test
	void getAllDinkyContainers() throws ContainerNotFoundException {
		List<Ingredient> ingredients = Arrays.asList(new Ingredient("A"), new Ingredient("B"));
		@SuppressWarnings("unused")
		IContainer container = factory.getContainer(ingredients);
		assertThat( factory.areDinkyContainersAvaiable() ).isEqualTo(1);
		container = factory.getContainer(ingredients);
		assertThat( factory.areDinkyContainersAvaiable() ).isEqualTo(0);
	}
	
	@Test
	void assumeDinkyContainersAreAvailable() throws ContainerNotFoundException {
		List<IContainer> containers = new ArrayList<IContainer>();
		Collections.addAll(containers, new Standard(), new Gargantuan());
		factory.setContainers(containers);
		
		// Proof there is no Dinkys Available.
		assertThat( factory.areDinkyContainersAvaiable() ).isEqualTo(0);

		assumeTrue( factory.areDinkyContainersAvaiable() > 0, "No Dinky Containers Available"  );
		// Rest of the Test will be skipped because we're assuming we can get a dinky container.
		// If there assumeTrue wasn't there, we'd throw a ContainerNotFoundExcpetion
		List<Ingredient> ingredients = Arrays.asList(new Ingredient("A"), new Ingredient("B"));
		factory.getContainer(ingredients);
	}
	
	@Test
	@Tag("gargantuan")
	void testTags() throws ContainerNotFoundException {
		List<Ingredient> ingredients = Arrays.asList(new Ingredient("A"), new Ingredient("B"),
				new Ingredient("C"), new Ingredient("D"));
		IContainer container = factory.getContainer(ingredients);
		assertThat(container).isInstanceOf(Standard.class);
	}
	// Exercise 5
	// Mockito Extension Model
	// JUnit 5.1 feature
	@DisplayName("Testing with mock ingredients")
	// zero based index of parameters
	@ParameterizedTest(name="{1} ingredients yields {2} container")
	@MethodSource
	void testWithMockedMethodSource(List<Ingredient> mockIngredents, int numberOf, Class<IContainer> clazz ) throws ContainerNotFoundException {
		when( mockIngredents.size()).thenReturn(numberOf);
		IContainer actualContainer = factory.getContainer( mockIngredents );
		assertThat(actualContainer).isInstanceOf(clazz);
	}

	@SuppressWarnings("unused")
	private static Stream<Arguments> testWithMockedMethodSource(){
		@SuppressWarnings("unchecked")
		List<Ingredient> mockIngredents = mock(List.class);
		return Stream.of(
				Arguments.of( mockIngredents, 1, Dinky.class ),
				Arguments.of( mockIngredents, 2, Dinky.class ),
				Arguments.of( mockIngredents, 3, Dinky.class ),
				Arguments.of( mockIngredents, 4, Standard.class ),
				Arguments.of( mockIngredents, 5, Standard.class ),
				Arguments.of( mockIngredents, 6, Standard.class ),
				Arguments.of( mockIngredents, 7, Gargantuan.class ),
				Arguments.of( mockIngredents, 8, Gargantuan.class ),
				Arguments.of( mockIngredents, 9, Gargantuan.class ),
				Arguments.of( mockIngredents, 10, Gargantuan.class ),
				Arguments.of( mockIngredents, 11, Gargantuan.class ) );
	}	
	@Test
	void testMockParameter(@Mock ContainerFactory mockFactory ) throws ContainerNotFoundException {
		when( mockFactory.getContainer( null )).thenReturn( new Dinky() );
		IContainer container = mockFactory.getContainer(null);
		assertThat( container ).isInstanceOf( Dinky.class );
	}
	
	// Testing with a mockAttribute of the test class.
	@Test
	void testMockAttribute() throws ContainerNotFoundException {
		when( mockIngredents.size()).thenReturn(4);
		IContainer container = factory.getContainer(mockIngredents);
		assertThat( container ).isInstanceOf( Standard.class );
	}
	
	@Test
	@ExtendWith(IgnoreContainerNotFoundException.class)
	void testIgnoreException() throws ContainerNotFoundException {
		when( mockIngredents.size()).thenReturn(52);
		factory.getContainer(mockIngredents);  // This stops processing of the test
		fail("I should never get here");
	}
	@ExtendWith(ContainerTestExecutionCallback.class)
	@Test
	@DisplayName("Callback testing")
	@Tag("extension")
	void testExtensionPoints() {
		assertThat(true).isEqualTo(true);
	}
}
