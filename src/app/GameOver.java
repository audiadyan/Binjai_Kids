package app;

import java.awt.Font;
import java.awt.Graphics2D;

import controller.ReadImage;

public class GameOver extends SuperMenu {
    private Game game;
    private Font skor;
    private int x;

    GameOver(Game game, ReadImage rImage) {
        super(rImage, "/images/background/bg5.png");
        this.game = game;
        skor = new Font("COURIER", Font.BOLD, 160);
    }

    public void draw(Graphics2D g2) {
        int pScor = game.getPlayer().getSkor();
        String tScor = Integer.toString(pScor);
        x = 235 - (tScor.length()*45);

        g2.drawImage(image, 0, 0, null);
        g2.setFont(skor);
        g2.drawString(tScor, x, 270);
    }
}
