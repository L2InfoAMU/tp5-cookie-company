package image;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class SparseRasterImage implements Image {
    private int width;
    private int height;
    private Map<Point, Color> mapping;

    public SparseRasterImage(Color color, int width, int height) {
        setHeight(height);
        setWidth(width);

        createRepresentation();

        setPixelsColor(color);
    }

    public SparseRasterImage(Color[][] colors) {
        setPixelsColor(colors);
    }

    public void createRepresentation() {

        mapping = new HashMap<>(width*height);
    }

    public void setPixelsColor(Color[][] colors) {

        setHeight(colors[0].length);
        setWidth(colors.length);

        createRepresentation();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                mapping.put(new Point(x, y), colors[x][y]);
            }
        }

    }

    public void setPixelsColor(Color c) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                mapping.put(new Point(x, y), c);
            }
        }
    }

    public void setPixelColor(Color color, Point p) {
        mapping.put(p, color);
    }

    protected Color getPixelColor(Point point) {
        return mapping.get(point);
    }

    @Override
    public Color getPixelColor(int x, int y) {
        return getPixelColor(new Point(x, y));
    }

    protected void setWidth(int width) {
        this.width = width;
    }

    protected void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
