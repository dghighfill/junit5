package com.jug.junit5.sm.ex2;

import java.util.List;

public class ContainerFactory {

	public IContainer getContainer(List<Ingredient> ingredients) throws ContainerNotFoundException {
		if( ingredients.size() <=3 ) {
			return new Dinky();
		}
		throw new ContainerNotFoundException("Unable to create Smoothie Ninja Container");
	}

}
