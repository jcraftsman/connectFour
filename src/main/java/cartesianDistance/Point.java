package cartesianDistance;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point anotherPoint) {
        if (anotherPoint == null) {
            throw new IllegalArgumentException("Ca va pas non!");
        }
        return sqrt(square((anotherPoint.x - this.x)) + square((anotherPoint.y - this.y)));
    }

    private double square(int a) {
        return pow(a, 2);
    }
}
