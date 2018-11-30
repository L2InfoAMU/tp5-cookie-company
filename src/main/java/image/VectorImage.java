package image;

import javafx.scene.paint.Color;

import java.util.List;

public class VectorImage implements Image {
    private List<Shape> shapes;
    private int width;
    private int height;

    public final static Color backgroundColor = Color.WHITE;

    public VectorImage(List<Shape> shapes, int width, int height) {
        this.shapes = shapes;
        this.width = width;
        this.height = height;
    }

    private Color getPixelColor(Point point) {
        for(Shape shape : shapes)
            if(shape.contains(point))
                return shape.getColor();
        return backgroundColor;
    }
    @Override
    public Color getPixelColor(int x, int y) {
        return getPixelColor(new Point(x, y));
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
