package image;

import javafx.scene.paint.Color;

import java.util.List;

public class VectorImage extends AbstractDimensionImage {
    private List<Shape> shapes;

    public final static Color backgroundColor = Color.WHITE;

    public VectorImage(List<Shape> shapes, int width, int height) {
        this.shapes = shapes;
        this.width = width;
        this.height = height;
    }

    private Color getPixelColor(Point point) {
        checkPixelBound(point.x, point.y);
        for(Shape shape : shapes)
            if(shape.contains(point))
                return shape.getColor();
        return backgroundColor;
    }
    @Override
    public Color getPixelColor(int x, int y) {
        return getPixelColor(new Point(x, y));
    }
}
