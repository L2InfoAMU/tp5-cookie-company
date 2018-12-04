package image;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class PaletteRasterImage extends RasterImage<Byte> {
    private Map<Byte, Color> byteToColor;
    private Map<Color, Byte> colorToByte;

    public PaletteRasterImage(Color color, int width, int height) {

        createRepresentation();

        setPixelsColor(color);
    }

    public PaletteRasterImage(Color[][] colors) {
        setPixelsColor(colors);
    }

    @Override
    public void createRepresentation() {
        super.createRepresentation();

        byteToColor = new HashMap<>();
        colorToByte = new HashMap<>();
    }

    @Override
    protected Byte toRawPixel(Color color) {
        if(colorToByte.containsKey(color))
            return colorToByte.get(color);
        return addNewColor(color);
    }

    @Override
    protected Color fromRawPixel(Byte raw) {
        return byteToColor.get(raw);
    }

    private byte addNewColor(Color color) {
        byte value = (byte)colorToByte.size();
        colorToByte.put(color, value);
        byteToColor.put(value, color);
        return value;
    }
}
