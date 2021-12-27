package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class Vector2dTest {
    @Test void testequals(){
        Vector2d y = new Vector2d(1,2);
        Vector2d x = new Vector2d(1,2);
        Vector2d z = new Vector2d(1,3);
        assertTrue(y.equals(x));
        assertFalse(y.equals(z));
    }
    @Test void testToString(){
        Vector2d y = new Vector2d(1,2);
        assertEquals("(1,2)",y.toString());
    }
    @Test void testprecedes(){
        Vector2d y = new Vector2d(1,2);
        Vector2d x = new Vector2d(2,3);
        assertTrue(y.precedes(x));
    }
    @Test void testfollows(){
        Vector2d y = new Vector2d(1,2);
        Vector2d x = new Vector2d(2,3);
        assertTrue(x.follows(y));
    }
    @Test void testupperRight(){
        Vector2d y = new Vector2d(1,2);
        Vector2d x = new Vector2d(2,3);
        assertEquals(new Vector2d(2,3),y.upperRight(x));
    }
    @Test void testlowerLeft(){
        Vector2d y = new Vector2d(1,2);
        Vector2d x = new Vector2d(2,3);
        assertEquals(new Vector2d(1,2),y.lowerLeft(x));
    }
    @Test void testadd(){
        Vector2d y = new Vector2d(1,2);
        Vector2d x = new Vector2d(2,3);
        assertEquals(new Vector2d(3,5),y.add(x));
    }
    @Test void testsubtract(){
        Vector2d y = new Vector2d(1,2);
        Vector2d x = new Vector2d(2,3);
        assertEquals(new Vector2d(-1,-1),y.subtract(x));
    }
    @Test void testopposite(){
        Vector2d y = new Vector2d(1,2);
        assertEquals(new Vector2d(-1,-2),y.opposite());
    }
}
