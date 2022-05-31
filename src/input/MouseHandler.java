package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import app.Game;

public class MouseHandler implements MouseListener {
    private Game game;

    public MouseHandler(Game game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        // User State Keyboard
        if(Game.gameState == Game.userState) {
            if(my >= 462 && my <= 542) {
                // StartButton
                if(mx >= 20 && mx <= 239) {
                    if(game.getPlayer().getName().length() == 0) {
                        Game.playSE(8);
                    } else {
                        game.startGame();
                        game.buttClick();
                    }
                }

                // BackButton
                if(mx >= 260 && mx <= 479) {
                    Game.gameState = Game.menuState;
                    game.buttClick();
                }
            }

            if(my >= 299 && my <= 334) {
                // Q
                if(mx >= 1 && mx <= 46) {
                    game.addPlayerName("Q");
                    game.keyClick();
                }

                // W
                else if(mx >= 52 && mx <= 97) {
                    game.addPlayerName("W");
                    game.keyClick();
                }

                // E
                else if(mx >= 102 && mx <= 147) {
                    game.addPlayerName("E");
                    game.keyClick();
                }

                // R
                else if(mx >= 153 && mx <= 198) {
                    game.addPlayerName("R");
                    game.keyClick();
                }

                // T
                else if(mx >= 204 && mx <= 249) {
                    game.addPlayerName("T");
                    game.keyClick();
                }

                // Y
                else if(mx >= 256 && mx <= 301) {
                    game.addPlayerName("Y");
                    game.keyClick();
                }

                // U
                else if(mx >= 307 && mx <= 353) {
                    game.addPlayerName("U");
                    game.keyClick();
                }
                // I
                else if(mx >= 357 && mx <= 402) {
                    game.addPlayerName("I");
                    game.keyClick();
                }

                // O
                else if(mx >= 405 && mx <= 450) {
                    game.addPlayerName("O");
                    game.keyClick();
                }

                // P
                else if(mx >= 453 && mx <= 498) {
                    game.addPlayerName("P");
                    game.keyClick();
                }
            }
            else if(my >= 352 && my <= 387) {
                // A
                if(mx >= 26 && mx <= 71) {
                    game.addPlayerName("A");
                    game.keyClick();
                }
                
                // S
                else if(mx >= 77 && mx <= 122) {
                    game.addPlayerName("S");
                    game.keyClick();
                }

                // D
                else if(mx >= 127 && mx <= 172) {
                    game.addPlayerName("D");
                    game.keyClick();
                }

                // F
                else if(mx >= 178 && mx <= 223) {
                    game.addPlayerName("F");
                    game.keyClick();
                }

                // G
                else if(mx >= 229 && mx <= 274) {
                    game.addPlayerName("G");
                    game.keyClick();
                }

                // H
                else if(mx >= 281 && mx <= 326) {
                    game.addPlayerName("H");
                    game.keyClick();
                }

                // J
                else if(mx >= 332 && mx <= 377) {
                    game.addPlayerName("J");
                    game.keyClick();
                }

                // K
                else if(mx >= 382 && mx <= 427) {
                    game.addPlayerName("K");
                    game.keyClick();
                }

                // L
                else if(mx >= 430 && mx <= 475) {
                    game.addPlayerName("L");
                    game.keyClick();
                }
            }
            else if(my >= 405 && my <= 440) {
                // Z
                if(mx >= 26 && mx <= 71) {
                    game.addPlayerName("Z");
                    game.keyClick();
                }

                // X
                else if(mx >= 77 && mx <= 122) {
                    game.addPlayerName("X");
                    game.keyClick();
                }

                // C
                else if(mx >= 127 && mx <= 172) {
                    game.addPlayerName("C");
                    game.keyClick();
                }

                // V
                else if(mx >= 178 && mx <= 223) {
                    game.addPlayerName("V");
                    game.keyClick();
                }

                // B
                else if(mx >= 229 && mx <= 274) {
                    game.addPlayerName("B");
                    game.keyClick();
                }

                // N
                else if(mx >= 281 && mx <= 326) {
                    game.addPlayerName("N");
                    game.keyClick();
                }

                // M
                else if(mx >= 332 && mx <= 377) {
                    game.addPlayerName("M");
                    game.keyClick();
                }

                // DEL
                else if(mx >= 385 && mx <= 476) {
                    game.getPlayer().delName();
                    game.keyClick();
                }
            }
        }

        // Menu State
        else if(Game.gameState == Game.menuState) {
            // tutorButton
            if(my >= 7 && my <= 67) {
                if(mx >= 448 && mx <= 493) {
                    Game.gameState = Game.tutorState;
                    game.buttClick();
                }
            }

            if(my >= 509 && my <= 544) {
                // MusicButton
                if(mx >= 227 && mx <= 272) {
                    game.changeMusic();
                    game.keyClick();
                }
                
                // VolUpButton
                else if(mx >= 284 && mx <= 329) {
                    game.upVolume();
                    game.keyClick();
                }
                
                // VolDownButton
                else if(mx >= 170 && mx <= 215) {
                    game.downVolume();
                    game.keyClick();
                }
            }

            else if(mx >= 60 && mx <= 438) {
                // PlayButton
                if(my >= 306 && my <= 386) {
                    game.getPlayer().resetName();
                    Game.gameState = Game.userState;
                    game.buttClick();
                }

                // HighScoreButton
                if(my >= 408 && my <= 488) {
                    Game.gameState = Game.scoreState;
                    game.gameHighScore();
                    game.buttClick();
                }
            }
        }

        // TutorState
        else if(Game.gameState == Game.tutorState) {
            if(my >= 23 && my <= 65) {
                if(mx >= 434 && mx <= 472) {
                    Game.gameState = Game.menuState;
                    game.buttClick();
                }
            }
        }

        // Menu Highscore, gameover
        else if(mx >= 60 && mx <= 438) {            
            // HighScore State
            if(Game.gameState == Game.scoreState) {
                // BackButton
                if(my >= 465 && my <= 545) {
                    Game.gameState = Game.menuState;
                    game.buttClick();
                }
            }

            // GameOver State
            if(Game.gameState == Game.gameoverState) {
                // RetryButton
                if(my >= 345 && my <= 425) {
                    game.startGame();
                    game.buttClick();
                }

                // MenuButton
                if(my >= 465 && my <= 545) {
                    Game.gameState = Game.menuState;
                    game.buttClick();
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
}
