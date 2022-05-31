package app;

import java.awt.Graphics2D;

import controller.ReadImage;

public class Tutorial extends SuperMenu {
    Tutorial(ReadImage rImage) {
        super(rImage, "/images/background/bg7.png");
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, 0, 0, null);
    }
}
