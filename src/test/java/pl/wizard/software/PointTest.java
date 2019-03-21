package pl.wizard.software;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    PointWithEq point1;
    PointWithEq point1Copy;
    PointWithEq point2;

    @BeforeEach
    void refreshEqPoints(){
        point1 = new PointWithEq(3, 3);
        point1Copy = point1;
        point2 = new PointWithEq(3, 3);
    }

    @Test
    void pointTest() {
        Point point1 = new Point(3, 3);
        Point point1Copy = point1;
        Point point2 = new Point(3, 3);

        assertTrue(point1 == point1Copy);
        assertEquals(point1, point1Copy);
        assertNotEquals(point1, point2);
        assertTrue(point1 == point2);
    }

    @Test
    void pointWithEqWithoutModificationTest() {
//        assertTrue(point1 == point1Copy);
//        assertTrue(point1 == point2);
        assertEquals(point1, point1Copy);
        assertEquals(point1, point2);
    }

    @Test
    void pointWithEqWithModificationPoint1andAfterPoint2() {
        point1.setX(5);
//        assertEquals(point1, point2);
//        assertEquals(point1, point1Copy);
//        assertEquals(point2,point1Copy);

        point2.setX(5);
        assertEquals(point2, point1);
        assertEquals(point2, point1Copy);
    }

    @Test
    void pointWithEqWithModificationCopyPoint1() {
        point1Copy.setY(5);
        assertEquals(point1,point2);
        assertEquals(point1,point1Copy);
        assertEquals(point2,point1Copy);
    }

    @Test
    void hashcodeMagicExample(){
        PointWithEq point1 = new PointWithEq(3, 3);
        PointWithEq point2 = new PointWithEq(3, 3);
        HashSet<PointWithEq> set = new HashSet<PointWithEq>();

        set.add(point1);
        System.out.println("set contains point 1: " + set.contains(point1));
        System.out.println("set size: " + set.size());

        set.add(point2);
        System.out.println("set contains point 1: " +set.contains(point1));
        System.out.println("set contains point 2: " +set.contains(point2));
        System.out.println("set size: " +set.size());

        point1.setX(5);
        System.out.println("set contains point 1: " + set.contains(point1));
        System.out.println("set contains point 2: " + set.contains(point2));
        System.out.println("set size: " +set.size());

        System.out.println(set.contains(new PointWithEq(3, 3)));
        set.add(point1);
        System.out.println(set.contains(new PointWithEq(5, 3)));
    }
}