package utils.filters;

import utils.MyImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BinaryFilter {
    public static BufferedImage getBinary(BufferedImage image, int limit) {
        BufferedImage greyImage = GrayFilters.getInAverageGreyScale(image);

        return MyImage.manipulateImage(greyImage, ((newImage, x, y) -> {
             int colorValue = new Color(greyImage.getRGB(x, y)).getRed() > limit ? 255 : 0;
             newImage.setRGB(x, y, new Color(colorValue, colorValue, colorValue).getRGB());
        }));
    }
}
