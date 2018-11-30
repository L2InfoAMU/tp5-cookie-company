package image;

import javafx.scene.paint.Color;

public class BruteRasterImage extends RasterImage<Color> {
    public BruteRasterImage(Color color, int width, int height) {
        setWidth(width);
        setHeight(height);

        createRepresentation();

        fill(color);
    }

    public BruteRasterImage(Color[][] colors) {
        setPixelsColor(colors);
    }

    @Override
    public void setPixelColor(Color color, int x, int y) {
        setRawPixelColor(color, x, y);
    }

    @Override
    public Color getPixelColor(int x, int y) {
        return getRawPixelColor(x, y);
    }
}
