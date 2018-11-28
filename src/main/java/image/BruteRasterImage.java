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

        if(pixels == null)
            pixels = new Color[height][width];
        else
        {
            pixels = Arrays.copyOf(pixels, height);
            for (int i = 0; i < height; i++) {
                if(pixels[i] == null)
                    pixels[i] = new Color[width];
                else
                    pixels[i] = Arrays.copyOf(pixels[i], width);
            }
        }
    }

    public void setPixelColor(int x, int y, Color color) {
        pixels[y][x] = color;
    }

    public void setPixelsColor(Color[][] colors) {
        Matrices.requiresNonNull(colors);
        Matrices.requiresNonZeroDimensions(colors);
        Matrices.requiresRectangularMatrix(colors);

        pixels = colors;
        height = pixels.length;
        width = pixels[0].length;
    }

    @Override
    public Color getPixelColor(int x, int y) {
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
    }

    protected void setHeight(int height) {
        this.height = height;
    }
}
