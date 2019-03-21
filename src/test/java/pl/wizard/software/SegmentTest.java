package pl.wizard.software;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SegmentTest {

    @Test
    void relationExample(){
        PointWithEq startPoint = new PointWithEq(0, 0);
        PointWithEq endPoint = new PointWithEq(3, 3);
        Segment segment1 = new Segment(new PointWithEq(startPoint.getX(),startPoint.getY()), endPoint);
        Segment segment2 = new Segment(startPoint, endPoint);
        assertEquals(segment1, segment2);

        segment1.getStartPoint().setX(5);
        segment2.getStartPoint().setX(5);
        assertEquals(segment1, segment2);

        segment2.getEndPoint().setX(5);
        assertEquals(segment1,segment2);
    }

    @Test
    void relationExample2(){
        PointWithEq startPoint = new PointWithEq(0, 0);
        PointWithEq endPoint = new PointWithEq(3, 3);
        Segment segment1 = new Segment(new PointWithEqAndSelfcopyConstructor(startPoint), new PointWithEqAndSelfcopyConstructor(endPoint));
        Segment segment2 = new Segment(new PointWithEqAndSelfcopyConstructor(startPoint), new PointWithEqAndSelfcopyConstructor(endPoint));

        assertEquals(segment1, segment2);

        segment1.getStartPoint().setX(5);
        segment1.getStartPoint().setX(5);
        assertEquals(segment1, segment2);

        segment2.getEndPoint().setX(5);
        assertEquals(segment1,segment2);
    }
}