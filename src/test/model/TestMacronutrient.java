package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMacronutrient {

    private Macronutrient m;

    @BeforeEach
    public void runBefore() {
        m = new Macronutrient("Protein", 15);
    }

    @Test
    public void testMacronutrientConstructor() {
        assertEquals("Protein", m.getName());
        assertEquals(15, m.getAmount());
    }

    @Test
    public void testGetMacronutrient() {
        assertEquals("Protein", m.getName());
    }

    @Test
    public void testGetAmount() {
        assertEquals(15, m.getAmount());
    }

    @Test
    public void testSetMacronutrient() {
        m.setName("Fat");
        assertEquals("Fat", m.getName());
    }

    @Test
    public void testSetAmount() {
        m.setAmount(20);
        assertEquals(20, m.getAmount());
    }

}
