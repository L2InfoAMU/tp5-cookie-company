package image;

import javafx.scene.paint.Color;
import util.Matrices;

import java.util.Arrays;

public class BruteRasterImage implements Image {
    private int width;
    private int height;
    private Color[][] pixels;

    public BruteRasterImage(Color color, int width, int height) {
        this.width = width;
        this.height = height;
        createRepresentation();
        for(Color[] lines : pixels)
            Arrays.fill(lines, color);
    }

    public BruteRasterImage(Color[][] colors) {
        setPixelsColor(colors);
    }

    public void createRepresentation(){
        assert width>0;
        assert height>0;

        pixels = new Color[height][width];
    }

    public void setPixelColor(int x, int y, Color color) {
        if(x >= width || y >= height || x < 0 || y < 0)
            throw new IndexOutOfBoundsException("pixel not inside picture");

        pixels[y][x] = color;
    }

    public void setPixelsColor(Color[][] colors) {
        Matrices.requiresNonNull(colors);
        Matrices.requiresNonZeroDimensions(colors);
        Matrices.requiresRectangularMatrix(colors);

        pixels = colors;
        height = pixels[0].length;
        width = pixels.length;
        pixels = new Color[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[y][x] = colors[x][y];
            }
        }
    }

    @Override
    public Color getPixelColor(int x, int y) {
        if(x >= width || y >= height || x < 0 || y < 0)
            throw new IndexOutOfBoundsException("pixel not inside picture");

        return pixels[y][x];
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    protected void setWidth(int width) {
        this.width = width;

        for (int y = 0; y < height; y++) {
            if(pixels[y] == null)
                pixels[y] = new Color[width];
            else
                pixels[y] = Arrays.copyOf(pixels[y], width);
        }
    }

    protected void setHeight(int height) {
        this.height = height;

        pixels = Arrays.copyOf(pixels, height);
    }
}
