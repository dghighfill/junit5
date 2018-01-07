package com.jug.junit5.sm.ex4;

public class ContainerNotFoundException extends Exception {

	private static final long serialVersionUID = -1591696406515071465L;

	public ContainerNotFoundException(String message) {
		super(message, new Throwable("Wrong number of Ingredients"));
	}

	public ContainerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
