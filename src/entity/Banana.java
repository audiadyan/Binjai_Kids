package entity;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

import controller.ReadImage;

public class Banana extends Entity {
    private ReadImage rImage;
    private BufferedImage bananaTree;

    public Banana(ReadImage rImage) {
        this.rImage = rImage;

        setDefaultValues();
        getImage();
    }

    protected void setDefaultValues() {
        x = 112;
        y = 20;
    }

    protected void getImage() {
        bananaTree = rImage.readImage("/images/object/banana.png");
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(bananaTree, x, y, null);
    }
}
