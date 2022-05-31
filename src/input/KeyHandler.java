package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import app.Game;

public class KeyHandler implements KeyListener {
    private Game game;
    private boolean enemy = true;

    public KeyHandler(Game game) {
        this.game = game;
    }

    public void setPlay() {
        enemy = true;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(Game.gameState == Game.playState) {
            if(code == KeyEvent.VK_LEFT && !game.getPlayer().LEFT) {
                game.getPlayer().lrChange();
            }
            if(code == KeyEvent.VK_RIGHT && !game.getPlayer().RIGHT) {
                game.getPlayer().lrChange();
            }
            if(code == KeyEvent.VK_SPACE && !game.getPlayer().SPACE) {
                game.getPlayer().sChange();

                if(enemy) {
                    enemy = false;
                    game.startEnemyThread();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
        if(Game.gameState == Game.playState) {
            if(code == KeyEvent.VK_SPACE) {
                game.getPlayer().sChange();
            }
        }
    }
}
