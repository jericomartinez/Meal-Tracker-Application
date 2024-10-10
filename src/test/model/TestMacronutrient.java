package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMacronutrient {

    Macronutrient m;

    @BeforeEach
    void runBefore() {
        m = new Macronutrient("Protein", 15);
    }

    @Test
    void testMacronutrientConstructor() {
        assertEquals("Protein", m.getName());
        assertEquals(15, m.getAmount());
    }

    @Test
    void testGetMacronutrient() {
        assertEquals("Protein", m.getName());
    }

    @Test
    void testGetAmount() {
        assertEquals(15, m.getAmount());
    }

    @Test
    void testSetMacronutrient() {
        m.setName("Fat");
        assertEquals("Fat", m.getName());
    }

    @Test
    void testSetAmount() {
        m.setAmount(20);
        assertEquals(20, m.getAmount());
    }

}
