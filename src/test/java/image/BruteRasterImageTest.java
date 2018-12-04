package image;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class BruteRasterImageTest {
    private final Color color = Color.RED;
    private final int width = 10;
    private final int height = 10;
    public RasterImageTest testBasic = new RasterImageTest(width, height, color, BruteRasterImage.class);

    public BruteRasterImageTest() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    }

    @Test
    public void check_color() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        testBasic.check_color();
    }

    @Test
    public void check_size() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        testBasic.check_size();
    }

    @Test
    public void check_dynamic_colors() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        testBasic.check_dynamic_colors();
    }
}