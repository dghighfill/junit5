package com.jug.junit5.sm.ex4;

public class Ingredient {

	String name;
	float ounces;

	public Ingredient() {
	}

	public Ingredient( String name ) {
		this( name, 0 );
	}
	public Ingredient(String name, float ounces) {
		this.name = name;
		this.ounces = ounces;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public float getOunces() {
		return ounces;
	}

	public void setOunces(float ounces) {
		this.ounces = ounces;
	}
}
