package utils.filters;

import utils.LocalMath;
import utils.MyImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class ManipulationFilter {
    private static boolean isBorder(int x, int y, int height, int width, int precision) {
        return x < precision || y < precision ||  x >= width - precision - 1 || y >= height - precision - 1;
    }

    // Arrumar
    private static int[] getPixelRegionInChannel(BufferedImage image, int x, int y, int precision) {
        int[] pixels = new int[(int) Math.pow((precision * 2 + 1), 2)];
        int count = 0;

        for (int i = (precision) * -1; i <= precision; i++) {
            for (int j = (precision) * -1; j <= precision; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                pixels[count++] = new Color(image.getRGB(x + i, y + j)).getRed();
            }
        }

        return pixels;
    }


    public static BufferedImage removeNoiseByMediana(BufferedImage image, int precision) {
        return MyImage.manipulateImage(image, (newImage, x, y) -> {
            if (ManipulationFilter.isBorder(x, y, image.getHeight(), image.getWidth(), precision)) {
                newImage.setRGB(x, y, image.getRGB(x, y));
                return;
            }

            int[] pixelRegion = ManipulationFilter.getPixelRegionInChannel(image, x, y, precision);
            Arrays.sort(pixelRegion);

            newImage.setRGB(
                x,
                y,
                new Color(
                    LocalMath.getMedian(pixelRegion),
                    LocalMath.getMedian(pixelRegion),
                    LocalMath.getMedian(pixelRegion)
                ).getRGB()
            );
        });
    }

    public static BufferedImage getWithConvolution(BufferedImage image, double[] convolutionElements) {
        return MyImage.manipulateImage(image, (newImage, x, y) -> {
            int precision = convolutionElements.length / 2;

            if (ManipulationFilter.isBorder(x, y, image.getHeight(), image.getWidth(), precision)) {
                newImage.setRGB(x, y, image.getRGB(x, y));
                return;
            }

            int[] pixelRegion = ManipulationFilter.getPixelRegionInChannel(image, x, y, precision);
            double[] newRegion = new double[convolutionElements.length];

            for (int i = 0; i < newRegion.length; i++) {
                newRegion[i] = pixelRegion[i] * convolutionElements[i];
            }

            int result = (int) Arrays.stream(newRegion).sum();

            if (result < 0) {
                Color color = new Color(0, 0, 0);
                newImage.setRGB(x, y, color.getRGB());
            } else if (result > 255) {
                Color color = new Color(255, 255, 255);
                newImage.setRGB(x, y, color.getRGB());
            }

            Color color = new Color(result, result, result);
            newImage.setRGB(x, y, color.getRGB());
        });
    }
}
