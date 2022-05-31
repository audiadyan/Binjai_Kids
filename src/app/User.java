package app;

import java.awt.Font;
import java.awt.Graphics2D;

import controller.ReadImage;

public class User extends SuperMenu {
    private Game game;
    private Font username;
    private String userName;

    User(Game game, ReadImage rImage) {
        super(rImage, "/images/background/bg6.png");
        this.game = game;
        username = new Font("COURIER", Font.BOLD, 90);
    }

    public void draw(Graphics2D g2) {
        userName = game.getPlayer().getName();
        int x = 247 - (userName.length()*27);

        g2.drawImage(image, 0, 0, null);
        g2.setFont(username);
        g2.drawString(userName, x, 215);
    }
}
