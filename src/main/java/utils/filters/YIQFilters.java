package utils.filters;

import utils.MyImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class YIQFilters {
    private static double handleSumY(double brightness, double brightnessToSum) {
        double total = brightness + brightnessToSum;
        return total > 1 ? 1 : total;
    }

    private static double handleMultiplyY(double brightness, double multiplier) {
        double total = brightness * multiplier;

        if (total > 1 || multiplier == 0) {
            return 1;
        } else if (total < 0) {
            return 0;
        }

        return total;
    }

    public static double getY(int r, int g, int b) {
        return 0.299 * r + 0.587 * g + 0.114 * b;
    }

    public static double getI(int r, int g, int b) {
        return 0.596 * r + 0.274 * g + 0.322 * b;
    }

    public static double getQ(int r, int g, int b) {
        return 0.211 * r + 0.532 * g + 0.312 * b;
    }

    public static int getRed(double y, double i, double q) {
        return (int) Math.min(Math.max(0, (y + 0.956 * i + 0.621 * q)), 255);
    }

    public static int getGreen(double y, double i, double q) {
        return (int) Math.min(Math.max(0, (y - 0.272  * i - 0.647 * q)), 255);
    }

    public static int getBlue(double y, double i, double q) {
        return (int) Math.min(Math.max(0, (y - 1.106 * i + 1.703 * q)), 255);
    }

    public static BufferedImage sumBrightness(BufferedImage image, double brightnessToSum) {
        return MyImage.manipulateImage(image, (newImage, x, y) -> {
            Color originalColor = new Color(image.getRGB(x, y));

            int originalRed = originalColor.getRed();
            int originalGreen = originalColor.getGreen();
            int originalBlue = originalColor.getBlue();

            double originalYChannel = YIQFilters.getY(originalRed, originalGreen, originalBlue);
            double originalIChannel = YIQFilters.getI(originalRed, originalGreen, originalBlue);
            double originalQChannel = YIQFilters.getQ(originalRed, originalGreen, originalBlue);
            double summedYChannel = YIQFilters.handleSumY(originalYChannel, brightnessToSum);

            newImage.setRGB(
                x,
                y,
                new Color(
                    YIQFilters.getRed(summedYChannel, originalIChannel, originalQChannel),
                    YIQFilters.getGreen(summedYChannel, originalIChannel, originalQChannel),
                    YIQFilters.getBlue(summedYChannel, originalIChannel, originalQChannel)
                ).getRGB()
            );
        });
    }

    public static BufferedImage multiplyBrightness(BufferedImage image, double multiplier) {
        return MyImage.manipulateImage(image, (newImage, x, y) -> {
            Color originalColor = new Color(image.getRGB(x, y));

            int originalRed = originalColor.getRed();
            int originalGreen = originalColor.getGreen();
            int originalBlue = originalColor.getBlue();

            double originalYChannel = YIQFilters.getY(originalRed, originalGreen, originalBlue);
            double originalIChannel = YIQFilters.getI(originalRed, originalGreen, originalBlue);
            double originalQChannel = YIQFilters.getQ(originalRed, originalGreen, originalBlue);
            double multipliedYChannel = YIQFilters.handleMultiplyY(originalYChannel, multiplier);

            newImage.setRGB(
                    x,
                    y,
                    new Color(
                            YIQFilters.getRed(multipliedYChannel, originalIChannel, originalQChannel),
                            YIQFilters.getGreen(multipliedYChannel, originalIChannel, originalQChannel),
                            YIQFilters.getBlue(multipliedYChannel, originalIChannel, originalQChannel)
                    ).getRGB()
            );
        });
    }

}
