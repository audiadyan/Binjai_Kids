package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import controller.ReadImage;

public class UI {
    private Game game;
    private BufferedImage skorPlat;
    private Font skor;

    UI(Game game, ReadImage rImage) {
        this.game = game;

        skor = new Font("COURIER", Font.BOLD, 40);
        skorPlat = rImage.readImage("/images/object/daun.png");
    }

    void draw(Graphics2D g2) {
        int pScor = game.getPlayer().getSkor();
        String tScor = Integer.toString(pScor);
        int x = 60 - (tScor.length()*10);

        g2.drawImage(skorPlat, -20, 0, null);
        g2.setFont(skor);
        g2.setColor(Color.white);
        g2.drawString(tScor, x, 50);
    }
}
