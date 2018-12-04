package image;

import javafx.scene.paint.Color;

public class BruteRasterImage extends RasterImage<Color> {
    public BruteRasterImage(Color color, int width, int height) {
        setWidth(width);
        setHeight(height);

        createRepresentation();

        setPixelsColor(color);
    }

    public BruteRasterImage(Color[][] colors) {
        setPixelsColor(colors);
    }

    @Override
    protected Color toRawPixel(Color color) {
        return color;
    }

    @Override
    protected Color fromRawPixel(Color raw) {
        return raw;
    }
}
