package controller;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

public class ReadImage {
    public BufferedImage readImage(String path) {
        BufferedImage gambar = null;

        try {
            gambar = ImageIO.read(getClass().getResource(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gambar;
    }
}
