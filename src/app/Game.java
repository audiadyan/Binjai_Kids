package app;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import controller.Controller;
import controller.ReadImage;

import entity.Banana;
import entity.Player;
import input.KeyHandler;
import input.MouseHandler;

public class Game extends JPanel implements Runnable {
    public final int HEIGHT = 600; // tinggi panel
    public final int WIDTH = 500; // lebar panel
    private BufferedImage bg1, bg2;
    private Graphics2D g2;

    // Game State
    public static int gameState;
    public static final int menuState = 0;
    public static final int userState = 1;
    public static final int playState = 2;
    public static final int gameoverState = 3;
    public static final int scoreState = 4;
    public static final int tutorState = 5;

    // Setingan
    private int FPS = 60;
    private int music = 0;

    private KeyHandler keyH = new KeyHandler(this);
    private MouseHandler mouseH = new MouseHandler(this);
    private ReadImage rImage = new ReadImage();
    public static Sound sound = new Sound();
    private Thread gameThread = new Thread(this), scoreThread;
    private UI ui = new UI(this, rImage);
    private Player player = new Player(this, rImage);
    private Banana banana = new Banana(rImage);
    public Controller controller = new Controller(FPS);

    private Menu menu = new Menu(rImage);
    private User uMenu = new User(this, rImage);
    private HighScore highScore = new HighScore(rImage);
    private GameOver gameOver = new GameOver(this, rImage);
    private Tutorial tutorial = new Tutorial(rImage);

    Game() {
        gameState = menuState; // set gameState ke menuState
        initComponents();
    }

    void initComponents() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT)); // set ukurang panel
        this.setDoubleBuffered(true); // meningkatkan performa rendering game
        this.addKeyListener(keyH); // menghandle input keyboard
        this.addMouseListener(mouseH); // menghandle input mouse
        this.setFocusable(true); // focus menerima input jpanel

        bg1 = rImage.readImage("/images/background/bg1.png"); // import bg1
        bg2 = rImage.readImage("/images/background/bg2.png"); // import bg2
    }

    public void startGame() {
        muteVolume();
        controller.reset(); // reset kontroller
        player.setDefaultValues(); // reset setting player
        keyH.setPlay(); // reset setting keyhandler
        gameState = playState; // game playstate
        playSE(10);
    }

    void startGameThread() {
        gameThread.start(); // memulai thread game
        playMusic(); // memutar backsound
    }

    public void startEnemyThread() {
        controller.startControlThread(); // memulai thread controller
    }

    public void gameOverColl() {
        playSE(9);
        gameState = gameoverState; // gameover state
        controller.stopControlThread(); // stop thread controller
        unmuteVolume();

        // add highscore
        highScore.addData(player.getName(), player.getSkor());
        scoreThread = new Thread(highScore);
        scoreThread.start();
    }

    public void gameHighScore() {
        highScore.getData();
    }

    public void buttClick() {
        playSE(5);
    }

    public void keyClick() {
        playSE(6);
    }
    
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                update(); // update informasi karakter jika playState
                repaint();// menjalankan paintComponent
                delta--;
            }
        }
    }

    void update() {
        if(gameState == playState) { 
            player.update(); // update data player
            controller.update(); // update data controller
        } 
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g2 = (Graphics2D) g;

        if(gameState == menuState) {
            drawBackGround(g2);
            menu.draw(g2);
        }
        else if(gameState == userState) {
            drawBackGround(g2);
            uMenu.draw(g2);
        }
        else if(gameState == playState) {
            drawPlayState(g2);
        }
        else if(gameState == scoreState) {
            drawBackGround(g2);
            highScore.draw(g2);
        }
        else if(gameState == gameoverState) {
            drawPlayState(g2);
            drawGameOverState(g2);
        }
        else if(gameState == tutorState) {
            drawTutorState(g2);
        }
        
        g2.dispose();
    }

    void drawBackGround(Graphics2D g2) {
        g2.drawImage(bg1, 0, 0, null);
        g2.drawImage(bg2, 0, 0, null);
    }

    void drawPlayState(Graphics2D g2) {
        g2.drawImage(bg1, 0, 0, null);
        banana.draw(g2);
        controller.draw(g2);
        g2.drawImage(bg2, 0, 0, null);
        player.draw(g2);

        if(gameState != gameoverState) {
            ui.draw(g2);
        }
    }

    void drawGameOverState(Graphics2D g2) {
        gameOver.draw(g2);
    }

    void drawTutorState(Graphics2D g2) {
        g2.drawImage(bg1, 0, 0, null);
        banana.draw(g2);
        g2.drawImage(bg2, 0, 0, null);
        tutorial.draw(g2);
    }

    public void playMusic() {
        sound.setFile(0, music);
        sound.playBS();
        sound.loop();
        if(music++ >= 3) { music = 0; }
    }

    public void changeMusic() {
        stopMusic();
        playMusic();
    }

    public void stopMusic() {
        sound.stop();
    }

    public static void playSE(int i) {
        sound.setFile(1, i);
        sound.play();
    }

    public void upVolume() {
        sound.upVolume();
    }
    
    public void downVolume() {
        sound.downVolume();
    }

    public void muteVolume() {
        sound.mute();
    }

    public void unmuteVolume() {
        sound.unMute();
    }

    public Player getPlayer() {
        return player;
    }

    public void addPlayerName(String name) {
        player.addName(name);
    }
}