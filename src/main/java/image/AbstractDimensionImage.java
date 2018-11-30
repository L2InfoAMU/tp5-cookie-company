package image;

import javafx.scene.paint.Color;
import util.Matrices;

public abstract class AbstractDimensionImage implements Image {
    protected int width;
    protected int height;

    @Override
    public int getWidth() {
        return width;
    }

    protected void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    protected void setHeight(int height) {
        this.height = height;
    }


    protected void checkPixelBound(int x, int y) {
        if(x >= width || y >= height || x < 0 || y < 0)
            throw new ArrayIndexOutOfBoundsException("pixel not inside picture");
    }
}
