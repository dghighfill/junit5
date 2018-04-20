package com.jug.junit5.sm.ex5;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import com.jug.junit5.MockitoExtension;

@Tag("questions")
class QuestionsTest {
	@Test
	@DisplayName( "Q: Can you chain assertions? A: Yes")
	void testChaining() {
		assertThat( 1 + 1 ).isEqualTo(2)
		                   .isGreaterThan(0)
		                   .isPositive();
	}
	
	@Test
	@ExtendWith(MockitoExtension.class)
	@DisplayName( "Q: Will the test continue if the Exception is not thrown. A: No")
	void getAllDinkyContainers( @Mock ContainerFactory factory ) throws ContainerNotFoundException {
		
		// when(factory.getContainer( null )).thenThrow( new ContainerNotFoundException( "Exception was thrown so step stops"));
		// Uncomment this to verify.
		assertThatExceptionOfType(ContainerNotFoundException.class).isThrownBy(() -> {
			factory.getContainer(null);
		}).withMessage("Exception was thrown so step stops");
		// That test fails with java.lang.AssertionError: Expecting code to raise a throwable.
		
		// Won't get to here because the Exception wasn't thrown so the test stops on previous assert.
		assertThat( 2 + 2 ).isEqualTo(4);
	}
	
	@Nested
	// Does Maven Surefire report on Nested classes.  Surefire seems to lacking in the JUnit 5
	// support arena.  DisplayNames are supported nor will Nested classes build a nice hierarchy for the report.
	@DisplayName("Nested Tests")
	class NestedTests {

		@Test
		void testThatWillAlwaysPass() {
			assertThat( Boolean.TRUE ).isTrue();
		}
	}
}
