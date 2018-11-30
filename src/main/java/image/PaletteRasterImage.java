package image;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class PaletteRasterImage extends RasterImage<Byte> {
    private Map<Byte, Color> byteToColor;
    private Map<Color, Byte> colorToByte;

    public PaletteRasterImage(Color color, int width, int height) {

        createRepresentation();

        byteToColor.put((byte)0, color);
        colorToByte.put(color, (byte)0);

        fill((byte)0);
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
    public void setPixelColor(Color color, int x, int y){
        checkPixelBound(x, y);
        if(colorToByte.containsKey(color)){
            setRawPixelColor(colorToByte.get(color), x, y);
        }
        else {
            setRawPixelColor(addNewColor(color), x, y);
        }
    }

    protected byte addNewColor(Color color) {
        byte value = (byte)colorToByte.size();
        colorToByte.put(color, value);
        byteToColor.put(value, color);
        return value;
    }

    @Override
    public Color getPixelColor(int x, int y) {
        return byteToColor.get(getRawPixelColor(x, y));
    }
}
