package app;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import controller.ReadImage;

public abstract class SuperMenu {
    protected BufferedImage image;

    SuperMenu(ReadImage rImage, String path) {
        image = rImage.readImage(path);
    }

    public abstract void draw(Graphics2D g2);
}
