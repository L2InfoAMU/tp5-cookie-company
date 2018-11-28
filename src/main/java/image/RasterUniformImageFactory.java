package image;

import javafx.scene.paint.Color;

public class RasterUniformImageFactory implements ImageFactory {
    private int width;
    private int height;
    private Color color;
    private RasterImageType rasterImageType;

    public RasterUniformImageFactory(int width, int height, Color color, RasterImageType rasterImageType) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.rasterImageType = rasterImageType;
    }

    @Override
    public Image makeImage() {
        switch (rasterImageType){
            case BRUTE:
                return new BruteRasterImage(color, width, height);
            default:
                throw new NotSupportedException(rasterImageType + " is not supported");
        }
    }
}