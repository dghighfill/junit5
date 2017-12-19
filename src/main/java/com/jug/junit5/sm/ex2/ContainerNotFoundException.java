package com.jug.junit5.sm.ex2;

public class ContainerNotFoundException extends Exception {

	public ContainerNotFoundException(String message) {
		super(message, new Throwable("Wrong number of Ingredients"));
	}

}
