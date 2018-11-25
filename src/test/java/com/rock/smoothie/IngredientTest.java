package com.rock.smoothie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class IngredientTest {

    private Ingredient ingredient;

    @BeforeEach
    void setUp() {
        ingredient = new Ingredient();
    }

    @Test
    void getName() {
        ingredient.setIngredient("banana");
        assertThat( ingredient.getIngredient() ).isEqualTo( "banana");
    }

    @Test
    void getAmount(){
        ingredient.setAmount(200);
        assertThat( ingredient.getAmount() ).isEqualTo( 200 );
    }
}