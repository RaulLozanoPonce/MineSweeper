package model.display;

import java.awt.*;
import java.util.List;

public class Segment {

    private java.util.List<Point> points;

    public Segment(java.util.List<Point> points) {
        this.points = points;
    }

    public java.util.List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
