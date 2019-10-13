package model.display;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Display {
    private Segment[] segments;
    private int posX;
    private int posY;
    private int width;
    private int height;
    private Color color;
    private boolean[][] positions;

    public Display(int posX, int posY, int width, int height, Color color) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.color = color;
        positions = new boolean[10][7];
        positions[0] = new boolean[]{true,true,true,true,true,true,false};
        positions[1] = new boolean[]{false,true,true,false,false,false,false};
        positions[2] = new boolean[]{true,true,false,true,true,false,true};
        positions[3] = new boolean[]{true,true,true,true,false,false,true};
        positions[4] = new boolean[]{false,true,true,false,false,true,true};
        positions[5] = new boolean[]{true,false,true,true,false,true,true};
        positions[6] = new boolean[]{true,false,true,true,true,true,true};
        positions[7] = new boolean[]{true,true,true,false,false,false,false};
        positions[8] = new boolean[]{true,true,true,true,true,true,true};
        positions[9] = new boolean[]{true,true,true,false,false,true,true};

        segments = new Segment[7];
        List<Point> points = new ArrayList<>();
        //Segmento 0
        points.add(new Point(posX + width/10,posY + height/18));
        points.add(new Point(posX + width/5,posY));
        points.add(new Point(posX + 4*width/5,posY));
        points.add(new Point(posX + 9*width/10,posY + height/18));
        points.add(new Point(posX + 4*width/5,posY + height/9));
        points.add(new Point(posX + width/5,posY + height/9));
        segments[0] = new Segment(points);

        //Segmento 1
        points = new ArrayList<>();
        points.add(new Point(posX + 9*width/10,posY + height/18));
        points.add(new Point(posX + width,posY + height/9));
        points.add(new Point(posX + width,posY + 4*height/9));
        points.add(new Point(posX + 9*width/10,posY + height/2));
        points.add(new Point(posX + 4*width/5,posY + 4*height/9));
        points.add(new Point(posX + 4*width/5,posY + height/9));
        segments[1] = new Segment(points);

        //Segmento 2
        points = new ArrayList<>();
        points.add(new Point(posX + 9*width/10,posY + height/2));
        points.add(new Point(posX + width,posY + 5*height/9));
        points.add(new Point(posX + width,posY + 8*height/9));
        points.add(new Point(posX + 9*width/10,posY + 17*height/18));
        points.add(new Point(posX + 4*width/5,posY + 8*height/9));
        points.add(new Point(posX + 4*width/5,posY + 5*height/9));
        segments[2] = new Segment(points);

        //Segmento 3
        points = new ArrayList<>();
        points.add(new Point(posX + width/10,posY + 17*height/18));
        points.add(new Point(posX + width/5,posY + 8*height/9));
        points.add(new Point(posX + 4*width/5,posY + 8*height/9));
        points.add(new Point(posX + 9*width/10,posY + 17*height/18));
        points.add(new Point(posX + 4*width/5,posY+height));
        points.add(new Point(posX + width/5,posY+height));
        segments[3] = new Segment(points);

        //Segmento 4
        points = new ArrayList<>();
        points.add(new Point(posX + width/10,posY + height/2));
        points.add(new Point(posX + width/5,posY + 5*height/9));
        points.add(new Point(posX + width/5,posY + 8*height/9));
        points.add(new Point(posX + width/10,posY + 17*height/18));
        points.add(new Point(posX,posY + 8*height/9));
        points.add(new Point(posX,posY + 5*height/9));
        segments[4] = new Segment(points);

        //Segmento 5
        points = new ArrayList<>();
        points.add(new Point(posX + width/10,posY + height/18));
        points.add(new Point(posX + width/5,posY + height/9));
        points.add(new Point(posX + width/5,posY + 4*height/9));
        points.add(new Point(posX + width/10,posY + height/2));
        points.add(new Point(posX,posY + 4*height/9));
        points.add(new Point(posX,posY + height/9));
        segments[5] = new Segment(points);

        //Segmento 6
        points = new ArrayList<>();
        points.add(new Point(posX + width/10,posY + height/2));
        points.add(new Point(posX + width/5,posY + 4*height/9));
        points.add(new Point(posX + 4*width/5,posY + 4*height/9));
        points.add(new Point(posX + 9*width/10,posY + height/2));
        points.add(new Point(posX + 4*width/5,posY + 5*height/9));
        points.add(new Point(posX + width/5,posY + 5*height/9));
        segments[6] = new Segment(points);
    }

    public Segment[] getSegments() {
        return segments;
    }

    public void setSegments(Segment[] segments) {
        this.segments = segments;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean[][] getPositions() {
        return positions;
    }

    public void setPositions(boolean[][] positions) {
        this.positions = positions;
    }
}
