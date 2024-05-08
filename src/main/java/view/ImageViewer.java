package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageViewer {
    public static void viewImages(BufferedImage... images) {
        JFrame window = new JFrame();
        window.setTitle("Exibindo imagem");
        window.getContentPane().setLayout(new FlowLayout());

        for (BufferedImage image : images) {
            window.getContentPane().add(
                    new JLabel(new ImageIcon(image))
            );
        }

        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
