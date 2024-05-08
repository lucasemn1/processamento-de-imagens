package utils.filters;

import exceptions.ColorNameException;
import utils.MyImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RGBFilters {
    public static BufferedImage getInRedScale(BufferedImage image) {
        return MyImage.manipulateImage(image, (newImage, x, y) -> {
            Color color = new Color(image.getRGB(x, y));
            newImage.setRGB(x, y, new Color(color.getRed(), 0, 0).getRGB());
        });
    }

    private static int sumColorValues(int originalColorValue, int toSum) {
        int colorValue = originalColorValue + toSum;
        
        if (colorValue > 255) {
            return 255;
        } else if (colorValue < 0) {
            return 0;
        }

        return colorValue;
    }


    private static int multiplyColorValue(int originalColorValue, float toMultiply) {
        int colorValue = (int) Math.floor(originalColorValue * toMultiply);

        if (colorValue > 255) {
            return 255;
        } else if (colorValue < 0) {
            return 0;
        } else if(colorValue == 0) {
            return originalColorValue;
        }

        return colorValue;
    }


    public static BufferedImage getWithBoostColor(BufferedImage image, String colorName, int boost) throws ColorNameException {
        if (!colorName.equalsIgnoreCase("red") && !colorName.equalsIgnoreCase("green") && !colorName.equalsIgnoreCase("blue")) {
            throw new ColorNameException("colorName not allowed.");
        }

        return MyImage.manipulateImage(image, (newImage, x, y) -> {
            Color originalColor = new Color(image.getRGB(x, y));
            Color color = new Color(originalColor.getRed(), originalColor.getGreen(), originalColor.getBlue());

            if(colorName.equals("red")){
                newImage.setRGB(x, y, new Color(
                    RGBFilters.sumColorValues(color.getRed(), boost),
                    color.getGreen(),
                    color.getBlue()).getRGB()
                );
            }
            else if(colorName.equals("green")){
                newImage.setRGB(x, y, new Color(
                    color.getRed(),
                    RGBFilters.sumColorValues(color.getGreen(), boost),
                    color.getBlue()).getRGB()
                );
            }
            else {
                newImage.setRGB(x, y, new Color(
                    color.getRed(),
                    color.getGreen(),
                    RGBFilters.sumColorValues(color.getBlue(), boost)).getRGB()
                );
            }
        });
    }

    public static BufferedImage getInGreenScale(BufferedImage image) {
        return MyImage.manipulateImage(image, (newImage, x, y) -> {
            Color color = new Color(image.getRGB(x, y));
            newImage.setRGB(x, y, new Color(0, color.getGreen(), 0).getRGB());
        });
    }

    public static BufferedImage getInBlueScale(BufferedImage image) {
        return MyImage.manipulateImage(image, (newImage, x, y) -> {
            Color color = new Color(image.getRGB(x, y));
            newImage.setRGB(x, y, new Color(0, 0, color.getBlue()).getRGB());
        });
    }

    public static BufferedImage getInInverseScale(BufferedImage image) {
        return MyImage.manipulateImage(image, (newImage, x, y) -> {
            Color color = new Color(image.getRGB(x, y));
            newImage.setRGB(
                    x,
                    y,
                    new Color(
                            255 - color.getRed(),
                            255 - color.getGreen(),
                            255 - color.getBlue()
                    ).getRGB()
            );
        });
    }

    private static double calculateBrightness(int x, int y, int a, int b) {
        double a2 = (double) a / 2;
        double b2 = (double) b / 2;
        final double intenity = 0.5;

        double result = (Math.pow(x - a2, 2) / Math.pow(a2, 2) + Math.pow(y - b2, 2) / Math.pow(b2, 2)) * intenity;
        return result;
    }

    public static BufferedImage getWithVignette(BufferedImage image) {
        return MyImage.manipulateImage(image, (newImage, x, y) -> {
            int height = newImage.getHeight();
            int width = newImage.getWidth();

            double brightness = RGBFilters.calculateBrightness(x, y, width, height);

            Color color = new Color(image.getRGB(x, y));

            newImage.setRGB(
                    x,
                    y,
                    new Color(
                            color.getRed() - (int) (color.getRed() * brightness),
                            color.getGreen() - (int) (color.getGreen() * brightness),
                            color.getBlue() - (int) (color.getBlue() * brightness)
                    ).getRGB()
            );
        });
    }

    public static BufferedImage sumBrightness(BufferedImage image, int brightnessToSum) {
        return MyImage.manipulateImage(image, (newImage, x, y) -> {
            Color originalColor = new Color(image.getRGB(x, y));
            Color color = new Color(originalColor.getRed(), originalColor.getGreen(), originalColor.getBlue());

            newImage.setRGB(
                x,
                y,
                new Color(
                    RGBFilters.sumColorValues(color.getRed(), brightnessToSum),
                    RGBFilters.sumColorValues(color.getGreen(), brightnessToSum),
                    RGBFilters.sumColorValues(color.getBlue(), brightnessToSum)
                ).getRGB()
            );
        });
    }

    public static BufferedImage multiplyBrightness(BufferedImage image, float multiplier) {
        return MyImage.manipulateImage(image, (newImage, x, y) -> {
            Color originalColor = new Color(image.getRGB(x, y));
            Color color = new Color(originalColor.getRed(), originalColor.getGreen(), originalColor.getBlue());

            newImage.setRGB(
                    x,
                    y,
                    new Color(
                        RGBFilters.multiplyColorValue(color.getRed(), multiplier),
                        RGBFilters.multiplyColorValue(color.getGreen(), multiplier),
                        RGBFilters.multiplyColorValue(color.getBlue(), multiplier)
                    ).getRGB()
            );
        });
    }
}
