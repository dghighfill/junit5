package com.jug.junit5.sm.ex5;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Ingredient {

	@Getter @Setter private String name;
	@Getter @Setter @NonNull private Float ounces;

	public Ingredient() {
	}

	public Ingredient(String name, Float ounces) {
		this.name = name;
		this.ounces = ounces;
	}
}
