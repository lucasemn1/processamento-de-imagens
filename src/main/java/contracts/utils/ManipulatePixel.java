package contracts.utils;

import java.awt.image.BufferedImage;

public interface ManipulatePixel {
    void run(BufferedImage newImage, int x, int y);
}
