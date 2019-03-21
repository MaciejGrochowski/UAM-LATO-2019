package pl.wizard.software;

public class Point {

    private int x;
    private int y;

    public Point(int aX, int aY) {
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


}
