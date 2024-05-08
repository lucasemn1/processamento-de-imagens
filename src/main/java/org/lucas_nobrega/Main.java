package org.lucas_nobrega;

import exceptions.ColorNameException;
import utils.MyImage;
import utils.filters.*;
import view.ImageViewer;

import java.awt.image.BufferedImage;
import java.util.logging.Logger;

public class Main {
    private static final String RESOURCE_PATH = "src/main/resources/img";

    public static void showInAllFilters(BufferedImage image) throws ColorNameException {
        BufferedImage inRedScale = RGBFilters.getInRedScale(image);
        BufferedImage withRedBust = RGBFilters.getWithBoostColor(image, "red", 50);
        BufferedImage inGreenScale = RGBFilters.getInGreenScale(image);
        BufferedImage withGreenBust = RGBFilters.getWithBoostColor(image, "green", 50);
        BufferedImage inBlueScale = RGBFilters.getInBlueScale(image);
        BufferedImage withBlueBust = RGBFilters.getWithBoostColor(image,  "blue" ,50);
        BufferedImage inInverseScale = RGBFilters.getInInverseScale(image);
        BufferedImage inGrayScale = GrayFilters.getInAverageGreyScale(image);
//        BufferedImage withVignette = RGBFilters.getWithVignette(image);
        BufferedImage inBinary = BinaryFilter.getBinary(image, 145);
        BufferedImage withBrightnessSummed = RGBFilters.sumBrightness(image, 100);
        BufferedImage withBrightnessMultiplied = RGBFilters.multiplyBrightness(image, 2F);

        BufferedImage withBrightnessSummedWithYIQ = YIQFilters.sumBrightness(image, 0.5);
        BufferedImage withBrightnessMultipliedWithYIQ = YIQFilters.multiplyBrightness(image, 1);
        BufferedImage withoutNoise = ManipulationFilter.removeNoiseByMediana(inBinary, 3);
        BufferedImage withCovaluation = ManipulationFilter.getWithConvolution(
            image, new double[]{0.0625, 0.125, 0.0625, 0.125, 0.25, 0.125, 0.0625, 0.125, 0.0625}
        );


        ImageViewer.viewImages(
            image,
            inRedScale,
            withRedBust,
            inGreenScale,
            withGreenBust,
            inBlueScale,
            withBlueBust,
            inInverseScale,
            inGrayScale,
// //            withVignette,
            inBinary,
            withBrightnessSummed,
            withBrightnessMultiplied,
            withBrightnessSummedWithYIQ,
            withBrightnessMultipliedWithYIQ,
            withoutNoise,
            withCovaluation
        );
    }

    public static void main(String[] args) {
        try {
//            BufferedImage originalImage = MyImage.readImage(Main.RESOURCE_PATH + "/mario.jpeg");
//            BufferedImage image = MyImage.getMinified(originalImage, 1);
//            BufferedImage imageWithNoise = MyImage.getMinified(MyImage.readImage(Main.RESOURCE_PATH + "/with-noise.jpg"), 1);

//            BufferedImage inRedScale = RGBFilters.getInRedScale(image);
//            BufferedImage withRedBust = RGBFilters.getWithBoostColor(image, "red", 50);
//            BufferedImage inGreenScale = RGBFilters.getInGreenScale(image);
//            BufferedImage withGreenBust = RGBFilters.getWithBoostColor(image, "green", 50);
//            BufferedImage inBlueScale = RGBFilters.getInBlueScale(image);
//            BufferedImage withBlueBust = RGBFilters.getWithBoostColor(image,  "blue" ,50);
//            BufferedImage inInverseScale = RGBFilters.getInInverseScale(image);
//            BufferedImage inGrayScale = GrayFilters.getInAverageGreyScale(image);
//            BufferedImage withVignette = RGBFilters.getWithVignette(image);
//            BufferedImage inBinary = BinaryFilter.getBinary(image, 100);
//            BufferedImage withBrightnessSummed = RGBFilters.sumBrightness(image, 100);
//            BufferedImage withBrightnessMultiplied = RGBFilters.multiplyBrightness(image, 2F);
//            BufferedImage withBrightnessSummed = YIQFilters.sumBrightness(image, 0.5);
//            BufferedImage withBrightnessMultipliedWithRGB = RGBFilters.multiplyBrightness(image, 2F);
//            BufferedImage withBrightnessMultipliedWithYIQ = YIQFilters.multiplyBrightness(image, 1);
//            BufferedImage withoutNoise = ManipulationFilter.removeNoiseByMediana(imageWithNoise, 1);
//            BufferedImage withCovaluation = ManipulationFilter.getWithConvolution(
//                    imageWithNoise, new double[]{0.0625, 0.125, 0.0625, 0.125, 0.25, 0.125, 0.0625, 0.125, 0.0625}
//            );

//            ImageViewer.viewImages(
//                image,
//                inRedScale,
//                withRedBust,
//                inGreenScale,
//                withGreenBust,
//                inBlueScale,
//                withBlueBust,
//                inInverseScale,
//                inGrayScale,
//                withVignette,
//                inBinary,
//                withBrightnessMultipliedWithRGB,
//                withBrightnessMultipliedWithYIQ,
//                imageWithNoise,
//                image,
//                withoutNoise,
//                withCovaluation
//            );

            BufferedImage originalImage = MyImage.readImage(Main.RESOURCE_PATH + "/microbiota.jpg");
            BufferedImage reduzedImage = MyImage.getMinified(originalImage, 3);

            Main.showInAllFilters(reduzedImage);
        } catch (Exception error) {
            Logger logger = Logger.getLogger("Main");
            logger.info(error.getMessage());
            logger.info("Houve um erro ao ler a imagem");
        }
    }
}