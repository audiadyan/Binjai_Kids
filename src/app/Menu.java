package app;

import java.awt.Graphics2D;

import controller.ReadImage;

public class Menu extends SuperMenu {
    Menu(ReadImage rImage) {
        super(rImage, "/images/background/bg3.png");
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, 0, 0, null);
    }
}
