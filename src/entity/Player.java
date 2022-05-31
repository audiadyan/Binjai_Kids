package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

import app.Game;
import controller.CollisionChecker;
import controller.ReadImage;

public class Player extends Entity {
    private boolean randImage;
    private ReadImage rImage;
    private BufferedImage left1, left2, left3, right1, right2, right3;

    private String username = "";
    private int SKOR;

    private Game game;

    // player position
    public boolean LEFT;
    public boolean RIGHT;
    public boolean SPACE;

    public Player(Game game, ReadImage rImage) {
        this.game = game;
        this.rImage = rImage;

        setDefaultValues();
        getImage();
    }

    public void setDefaultValues() {
        x = 121;
        y = 442;
        SKOR = 0;
        direction = "left";
        LEFT = true;
        RIGHT = !LEFT;
        SPACE = false;
    }

    protected void getImage() {
        left1 = rImage.readImage("/images/player/diam_kiri.png");
        left2 = rImage.readImage("/images/player/pukul_kiri_1.png");
        left3 = rImage.readImage("/images/player/pukul_kiri_2.png");
        right1 = rImage.readImage("/images/player/diam_kanan.png");
        right2 = rImage.readImage("/images/player/pukul_kanan_1.png");
        right3 = rImage.readImage("/images/player/pukul_kanan_2.png");
    }

    public Rectangle getBounds() {
        return new Rectangle(x+33, y+10, 61, 100);
    }

    public void lrChange() {
        LEFT = !LEFT;
        RIGHT = !RIGHT;

        if(LEFT) {
            x = 121;
        } else {
            x = 248;
        }
    }

    public void sChange() {
        SPACE = !SPACE;
        if(SPACE) {
            Game.playSE(4);
            SKOR++;
            randImage = ThreadLocalRandom.current().nextBoolean();
        }
    }

    public void update() {
        if(SPACE == true) {
            direction = "space";
        } else if(LEFT == true) {
            direction = "left";
        } else if(RIGHT == true) {
            direction = "right";
        }

        if(CollisionChecker.collCheck(this, game.controller.getEnemy())) {
            game.gameOverColl();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch(direction) {
            case "left":
                image = left1;
                break;
        
            case "right":
                image = right1;
                break;

            case "space":
                if(LEFT) {
                    if(randImage) {
                        image = left2;
                    } else {
                        image = left3;
                    }
                } else if(RIGHT) {
                    if(randImage) {
                        image = right2;
                    } else {
                        image = right3;
                    }
                }
                break;
        }

        g2.drawImage(image, x, y, null);
    }

    public int getSkor() {
        return SKOR;
    }

    public String getName() {
        return username;
    }

    public void addName(String name) {
        if(username.length() < 6) {
            username += name;
        }
    }

    public void delName() {
        if(username.length() != 0) {
            username = username.substring(0, username.length()-1);
        }
    }

    public void resetName() {
        username = "";
    }
}
