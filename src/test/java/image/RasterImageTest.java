package image;

import javafx.scene.paint.Color;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RasterImageTest {

    private final int width;
    private final int height;
    private final Color color;
    private final RasterImage<?> image;
    Constructor<?  extends RasterImage<?>> constructor;

    public RasterImageTest(int width, int height, Color color, Class<? extends RasterImage<?>> imageClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        this.width = width;
        this.height = height;
        this.color = color;
        this.constructor = imageClass.getConstructor(Color.class, int.class, int.class);
        this.image = constructor.newInstance(color, width, height);
    }

    public void check_dynamic_colors() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        RasterImage<?> rasterImage = constructor.newInstance(color, 5, 1);
        rasterImage.setPixelColor(Color.WHITE, 0, 0);
        rasterImage.setPixelColor(Color.BLUE, 1, 0);
        rasterImage.setPixelColor(Color.BLACK, 2, 0);
        rasterImage.setPixelColor(Color.SILVER, 3, 0);
        rasterImage.setPixelColor(Color.MAROON, 4, 0);

        assertEquals(Color.WHITE, rasterImage.getPixelColor(0,0));
        assertEquals(Color.BLUE, rasterImage.getPixelColor(1,0));
        assertEquals(Color.BLACK, rasterImage.getPixelColor(2,0));
        assertEquals(Color.SILVER, rasterImage.getPixelColor(3,0));
        assertEquals(Color.MAROON, rasterImage.getPixelColor(4,0));

        Color[][] colors = new Color[][]{
                new Color[]{Color.MAROON,   Color.ORANGERED},
                new Color[]{Color.BLACK,    Color.CORAL},
                new Color[]{Color.PINK,     Color.ORANGE},
                new Color[]{Color.THISTLE,  Color.MEDIUMSEAGREEN},
                new Color[]{Color.BLUE,     Color.PINK}
        };

        rasterImage.setPixelsColor(colors);

        for (int x = 0; x < colors.length; x++) {
            for (int y = 0; y < colors[y].length; y++) {
                assertEquals(colors[x][y], rasterImage.getPixelColor(x, y));
            }
        }
    }

    public void check_color() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < height; x++) {
                assertEquals(color, image.getPixelColor(x, y));
            }
        }
    }
    public void check_size() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        assertEquals(height, image.getHeight());
        assertEquals(width, image.getWidth());

        RasterImage<?> rasterImage = constructor.newInstance(Color.RED, 5, 8);

        assertEquals(8, rasterImage.getHeight());
        assertEquals(5, rasterImage.getWidth());
    }
}
