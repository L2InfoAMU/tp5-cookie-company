package image;

import javafx.scene.paint.Color;
import util.Matrices;

import java.util.Arrays;

public abstract class RasterImage<T> extends AbstractDimensionImage {
    private T[][] pixelColors;


    public void createRepresentation(){
        if(width<0 || height<0)
            throw new IndexOutOfBoundsException("invalid picture size");

        pixelColors = (T[][]) new Object[height][width];
    }

    @Override
    public Color getPixelColor(int x, int y) {
        return fromRawPixel(getRawPixelColor(x, y));
    }

    protected void checkPictureBound(Color[][] colors) {
        Matrices.requiresNonNull(colors);
        Matrices.requiresNonZeroDimensions(colors);
        Matrices.requiresRectangularMatrix(colors);
    }

    private void setRawPixelColors(T[][] pixelColors) {
        this.pixelColors = pixelColors;
    }

    private void setRawPixelColor(T color, int x, int y) {
        checkPixelBound(x, y);
        pixelColors[y][x] = color;
    }

    private T getRawPixelColor(int x, int y) {
        return pixelColors[y][x];
    }

    public void setPixelColor(Color color, int x, int y) {
        setRawPixelColor(toRawPixel(color), x, y);
    }

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

    public void setPixelsColor(Color color) {
        setRawPixelsColor(toRawPixel(color));
    }

    private void setRawPixelsColor(T color) {
        for(T[] lines : pixelColors)
            Arrays.fill(lines, color);
    }

    protected abstract T toRawPixel(Color color);
    protected abstract Color fromRawPixel(T raw);
}
