package utils.filters;

import utils.MyImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GrayFilters {

    public static BufferedImage getInAverageGreyScale(BufferedImage image) {
        return MyImage.manipulateImage(image, (newImage, x, y) -> {
            Color color = new Color(image.getRGB(x, y));
            int average = (color.getRed() + color.getGreen() + color.getBlue()) / 3;

            newImage.setRGB(
                x,
                y,
                new Color(
                    average,
                    average,
                    average
                ).getRGB()
            );
        });
    }

    public static BufferedImage getInRedGreyScale(BufferedImage image) {
        return MyImage.manipulateImage(image, (newImage, x, y) -> {
            Color color = new Color(image.getRGB(x, y));

            newImage.setRGB(
                    x,
                    y,
                    new Color(
                            color.getRed(),
                            0,
                            0
                    ).getRGB()
            );
        });
    }

    public static BufferedImage getInGreenGreyScale(BufferedImage image) {
        return MyImage.manipulateImage(image, (newImage, x, y) -> {
            Color color = new Color(image.getRGB(x, y));

            newImage.setRGB(
                    x,
                    y,
                    new Color(
                            0,
                            color.getGreen(),
                            0
                    ).getRGB()
            );
        });
    }

    public static BufferedImage getInBlueGreyScale(BufferedImage image) {
        return MyImage.manipulateImage(image, (newImage, x, y) -> {
            Color color = new Color(image.getRGB(x, y));

            newImage.setRGB(
                    x,
                    y,
                    new Color(
                            0,
                            0,
                            color.getBlue()
                    ).getRGB()
            );
        });
    }
}
