package pl.wizard.software;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test1 {

    @Test
    public void primitiveAndObjects(){
        int primitive1 = 1;
        int primitive2 = 1;
        Integer object1 = new Integer(1);
        Integer object2 = new Integer(1);

        assertTrue(primitive1 == primitive2);
//        assertTrue(primitive1.equals(object1);
        assertTrue(object1.equals(primitive1));
        assertTrue(object1.equals(object2));
        assertEquals(object1,object2);
        assertFalse(object1 == object2);
        assertTrue(object1.intValue() == object2.intValue());
    }
}
