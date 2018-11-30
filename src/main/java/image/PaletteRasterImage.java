package image;

import javafx.scene.paint.Color;
import util.Matrices;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PaletteRasterImage implements Image {
    private int width;
    private int height;
    private Map<Byte, Color> byteToColor;
    private Map<Color, Byte> colorToByte;
    private byte[][] palette;

    public PaletteRasterImage(Color color, int width, int height) {
        this.width = width;
        this.height = height;
        byteToColor = new HashMap<>();
        colorToByte = new HashMap<>();
        createRepresentation();
        byteToColor.put((byte)0, color);
        colorToByte.put(color, (byte)0);
        for(byte[] lines : palette)
            Arrays.fill(lines, (byte)0);
    }

    public PaletteRasterImage(Color[][] colors) {
        setPixelsColor(colors);
    }

    private void createRepresentation() {
        palette = new byte[height][width];
    }

    public void setPixelsColor(Color[][] pixels) {
        Matrices.requiresNonNull(pixels);
        Matrices.requiresNonZeroDimensions(pixels);
        Matrices.requiresRectangularMatrix(pixels);

        byteToColor = new HashMap<>();
        colorToByte = new HashMap<>();

        height = pixels[0].length;
        width = pixels.length;

        byte counter = 0;

        createRepresentation();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if(colorToByte.containsKey(pixels[x][y]))
                    palette[y][x] = colorToByte.get(pixels[x][y]);
                else {
                    palette[y][x] = counter;
                    colorToByte.put(pixels[x][y], counter);
                    byteToColor.put(counter, pixels[x][y]);
                    counter++;
                }
            }
        }
    }

    public void setPixelColor(Color color, int x, int y){
        if(x >= width || y >= height || x < 0 || y < 0)
            throw new IndexOutOfBoundsException("pixel not inside picture");

        if(colorToByte.containsKey(color)){
            palette[y][x] = colorToByte.get(color);
        }
        else {
            byte value = (byte)colorToByte.size();
            palette[y][x] = value;
            colorToByte.put(color, value);
            byteToColor.put(value, color);
        }
    }

    @Override
    public Color getPixelColor(int x, int y) {
        if(x >= width || y >= height || x < 0 || y < 0)
            throw new IndexOutOfBoundsException("pixel not inside picture");

        return byteToColor.get(palette[y][x]);
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

        for (int i = 0; i < height; i++) {
            if(palette[i] == null)
                palette[i] = new byte[width];
            else
                palette[i] = Arrays.copyOf(palette[i], width);
        }
    }

    public void setHeight(int height) {
        this.height = height;

        palette = Arrays.copyOf(palette, height);
    }
}
