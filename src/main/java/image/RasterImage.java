package image;

import javafx.scene.paint.Color;
import util.Matrices;

import java.util.Arrays;

public abstract class RasterImage<T> implements Image {
    protected int width;
    protected int height;
    protected T[][] pixelColors;


    public void createRepresentation(){
        if(width<0 || height<0)
            throw new IndexOutOfBoundsException("invalid picture size");

        pixelColors = (T[][]) new Object[height][width];
    }

    protected void setRawPixelColors(T[][] pixelColors) {
        this.pixelColors = pixelColors;
    }

    protected void setRawPixelColor(T color, int x, int y) {
        pixelColors[y][x] = color;
    }

    protected T getRawPixelColor(int x, int y) {
        return pixelColors[y][x];
    }

    public abstract void setPixelColor(Color color, int x, int y);

    public void setPixelsColor(Color[][] colors) {
        checkPictureBound(colors);

        setHeight(colors[0].length);
        setWidth(colors.length);

        createRepresentation();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                setPixelColor(colors[x][y], x, y);
            }
        }
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    protected void applyResize() {
        pixelColors = Arrays.copyOf(pixelColors, height);

        for (int i = 0; i < height; i++) {
            if(pixelColors[i] == null)
                pixelColors[i] = (T[]) new Object[this.width];
            else
                pixelColors[i] = Arrays.copyOf(pixelColors[i], this.width);
        }
    }

    protected void setWidth(int width) {
        this.width = width;

    }

    protected void setHeight(int height) {
        this.height = height;
    }

    protected void checkPixelBound(int x, int y) {
        if(x >= width || y >= height || x < 0 || y < 0)
            throw new ArrayIndexOutOfBoundsException("pixel not inside picture");
    }

    protected void checkPictureBound(Color[][] colors) {
        Matrices.requiresNonNull(colors);
        Matrices.requiresNonZeroDimensions(colors);
        Matrices.requiresRectangularMatrix(colors);
    }

    protected void setPixelsColor(T color) {
        for(T[] lines : pixelColors)
            Arrays.fill(lines, color);
    }
}
