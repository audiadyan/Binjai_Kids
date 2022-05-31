package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import app.Game;
import controller.ReadImage;

public class Enemy extends Entity {
    private ReadImage rImage = new ReadImage();
    private BufferedImage diam, lompat1, lompat2;
    private BufferedImage[] left = new BufferedImage[5];
    private BufferedImage[] right = new BufferedImage[5];
    private Random random = new Random();

    private String finalDirection;
    private int velY = 0;
    private int speed = 4;
    private int imageCounter = 0;

    public Enemy(String direction) {
        setDefaultValues();
        getImage();
        finalDirection = direction;
    }

    protected void setDefaultValues() {
        x = 185;
        y = 15;
        direction = "center";
    }

    protected void getImage() {
        diam = rImage.readImage("/images/enemy/poci_diam.png");
        lompat1 = rImage.readImage("/images/enemy/poci_kiri_lompat.png");
        lompat2 = rImage.readImage("/images/enemy/poci_kanan_lompat.png");
        left[0] = rImage.readImage("/images/enemy/poci_kiri_jatuh1.png");
        left[1] = rImage.readImage("/images/enemy/poci_kiri_jatuh2.png");
        left[2] = rImage.readImage("/images/enemy/poci_kiri_jatuh3.png");
        left[3] = rImage.readImage("/images/enemy/poci_kiri_jatuh4.png");
        left[4] = rImage.readImage("/images/enemy/poci_kiri_jatuh5.png");
        right[0] = rImage.readImage("/images/enemy/poci_kanan_jatuh1.png");
        right[1] = rImage.readImage("/images/enemy/poci_kanan_jatuh2.png");
        right[2] = rImage.readImage("/images/enemy/poci_kanan_jatuh3.png");
        right[3] = rImage.readImage("/images/enemy/poci_kanan_jatuh4.png");
        right[4] = rImage.readImage("/images/enemy/poci_kanan_jatuh5.png");
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y+40, 135, 30);
    }

    public void update() {
        y += velY;

        imageCounter++;
        if(imageCounter > 20) {
            if(direction.equals("center")) {
                direction = "jump";
            }
            else if(direction.equals("jump")) {
                Game.playSE(7);
                direction = finalDirection;
                setDown(direction);
                setVelY(speed);
            }
            imageCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch(direction) {
            case "center":
                image = diam;
                break;

            case "jump":
                if(finalDirection.equals("left")) {
                    image = lompat1;
                } else {
                    image = lompat2;
                }
                break;

            case "left":
                image = left[random.nextInt(5)];
                break;
        
            case "right":
                image = right[random.nextInt(5)];
                break;
        }

        g2.drawImage(image, x, y, null);
    }

    void setDown(String direction) {
        if(direction.equals("left")) {
            x = 100;
        } else {
            x = 260;
        }
        y = 60;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int getY() {
        return y;
    }
}