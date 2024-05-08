package utils;

import contracts.utils.ManipulatePixel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyImage {
    private MyImage() {}

    public static BufferedImage readImage(String path) throws IOException {
        File file = new File(path);
        return ImageIO.read(file);
    }

    public static BufferedImage getMinified(BufferedImage image, int reduce) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage newImage = new BufferedImage(
            width / reduce,
            height / reduce,
            BufferedImage.TYPE_INT_RGB
        );


        for (int h = reduce; h < height / reduce; h++) {
            for(int w = reduce; w < width / reduce; w++) {
                newImage.setRGB(w, h, image.getRGB(w * reduce, h * reduce));
            }
        }

        return newImage;
    }

    public static void writeImage(BufferedImage image, String format, String path) throws IOException {
        ImageIO.write(image, format, new File(path));
    }

    public static BufferedImage manipulateImage(BufferedImage image, ManipulatePixel manipulatePixel) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage newImage = new BufferedImage(width, height,  BufferedImage.TYPE_INT_RGB);

        for (int h = 0; h < height; h++) {
            for(int w = 0; w < width; w++) {
                manipulatePixel.run(newImage, w, h);
            }
        }

        return newImage;
    }
}



