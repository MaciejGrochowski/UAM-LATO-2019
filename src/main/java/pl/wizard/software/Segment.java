package pl.wizard.software;

public class Segment {

    PointWithEq startPoint;
    PointWithEq endPoint;

    public Segment(PointWithEq startPoint, PointWithEq endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public PointWithEq getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(PointWithEq startPoint) {
        this.startPoint = startPoint;
    }

    public PointWithEq getEndPoint() {
        return endPoint;
    }


    public void setEndPoint(PointWithEq endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Segment segment = (Segment) o;

        return segmentLength(segment) == segmentLength(this);
    }

    private double segmentLength(Segment aSegment) {
        return Math.sqrt((aSegment.getStartPoint().getX() - aSegment.getEndPoint().getX()) * (aSegment.getStartPoint().getX() - aSegment.getEndPoint().getX())
                + (aSegment.getStartPoint().getY() - aSegment.getEndPoint().getY()) * (aSegment.getStartPoint().getY() - aSegment.getEndPoint().getY()));
    }

    @Override
    public String toString() {
        return "Segment{" +
                "startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                "length=" + segmentLength(this) +
                '}';
    }

}
