package image;

import javafx.scene.paint.Color;

public class Rectangle implements Shape {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public Rectangle(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    boolean insideBound(int value, int minBound, int maxBound) {
        return minBound <= value && value <= maxBound;
    }

    protected boolean insideY(int value) {
        int y2 = y + height;
        if(y < y2)
            return insideBound(value, y, y2);
        return insideBound(value, y2, y);
    }

    protected boolean insideX(int value) {
        int x2 = x + width;
        if(x < x2)
            return insideBound(value, x, x2);
        return insideBound(value, x2, x);
    }

    public boolean contains(Point point) {
        return insideX(point.x) && insideY(point.y);
    }
}
