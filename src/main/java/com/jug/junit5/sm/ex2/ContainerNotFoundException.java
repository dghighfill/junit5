package com.jug.junit5.sm.ex2;

public class ContainerNotFoundException extends Exception {

	private static final long serialVersionUID = -9091979421578398150L;

	public ContainerNotFoundException(String message) {
		super(message, new Throwable("Wrong number of Ingredients"));
	}

}
