package pl.wizard.software;

public class PointWithEqAndSelfcopyConstructor extends PointWithEq{


    public PointWithEqAndSelfcopyConstructor(int aX, int aY) {
        super(aX, aY);
    }

    public PointWithEqAndSelfcopyConstructor(PointWithEq aPoint) {
        super(aPoint.getX(), aPoint.getY());
    }
}
