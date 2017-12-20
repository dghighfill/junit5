package com.jug.junit5.sm;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContainerFactoryTest {

	ContainerFactory factory = null;
	
	@BeforeEach
	void beforeEach() {
		factory = new ContainerFactory();
	}
	
	@Test
	void testContainerFactoryIsNotNull() {
		assertThat( factory ).isNotNull();
	}
	
	@Test
	void testGetContainer() {
		IContainer container = factory.getContainer( Collections.emptyList() );
		assertThat( container ).isNull();
	}
}
