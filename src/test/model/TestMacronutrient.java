package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMacronutrient {

    Macronutrient testMacronutrient;

    @BeforeEach
    void runBefore() {
        testMacronutrient = new Macronutrient("Protein", 15);
    }

    @Test
    void testMacronutrientConstructor() {
        assertEquals("Protein", testMacronutrient.getName());
        assertEquals(15, testMacronutrient.getAmount());
    }

    @Test
    void testGetMacronutrient() {
        assertEquals("Protein", testMacronutrient.getName());
    }

    @Test
    void testGetAmount() {
        assertEquals(15, testMacronutrient.getAmount());
    }

    @Test
    void testSetMacronutrient() {
        testMacronutrient.setName("Fat");
        assertEquals("Fat", testMacronutrient.getName());
    }

    @Test
    void testSetAmount() {
        testMacronutrient.setAmount(20);
        assertEquals(20, testMacronutrient.getAmount());
    }

}
