package pl.wizard.software;

public class PointWithEq {

    private int x;
    private int y;

    public PointWithEq(int aX, int aY) {
        x = aX;
        y = aY;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public int getX() {
        return x;
    }

    public void setX(int aX) {
        x = aX;
    }

    public int getY() {
        return y;
    }

    public void setY(int aY) {
        y = aY;
    }

    @Override
    public boolean equals(Object aO) {
        if (this == aO) return true;
        if (aO == null || getClass() != aO.getClass()) return false;

        PointWithEq that = (PointWithEq) aO;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
