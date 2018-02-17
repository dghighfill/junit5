package com.jug.junit5.sm.ex5;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LombokIngredient {

	private String name;
	@NonNull private Float ounces;
}
