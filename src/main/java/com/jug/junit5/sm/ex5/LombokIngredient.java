package com.jug.junit5.sm.ex5;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Data
public class LombokIngredient {

	private String name;
	@NonNull private Float ounces;

	public LombokIngredient() {
	}

	public LombokIngredient(String name, Float ounces) {
		this.name = name;
		this.ounces = ounces;
	}
}
