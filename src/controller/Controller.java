package controller;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import java.awt.Graphics2D;

import entity.Enemy;

public class Controller implements Runnable {
    private LinkedList<Enemy> enemy = new LinkedList<Enemy>();

    private Thread controlThread;
    private Enemy currEnemy, tempEnemy;

    private Random random = new Random();
    private String tempdirection, direction;
    private int FPS;
    private int delayPlayGame = 100;

    public Controller(int FPS) {
        this.FPS = FPS;

        if(ThreadLocalRandom.current().nextBoolean()) {
            tempdirection = "left";
        } else {
            tempdirection = "right";
        }
    }

    public void startControlThread() {
        controlThread.start();
    }

    public void stopControlThread() {
        controlThread = null;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(delayPlayGame);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(controlThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                updatePoci();
                delta--;
            }
        }
    }

    void updatePoci() {
        try {
            if(ThreadLocalRandom.current().nextBoolean()) {
                direction = "left";
            } else {
                direction = "right";
            }

            if(!tempdirection.equals(direction)) {
                Thread.sleep(500);
            }
            Thread.sleep(random.nextInt(750 - 100) + 100);
            
            tempdirection = direction;

            tempEnemy = new Enemy(direction);
            addEnemy(tempEnemy);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void update() {
        for(int i = enemy.size()-1; i >= 0; i--) {
            currEnemy = enemy.get(i);

            if(currEnemy.getY() >= 600) {
                removeEnemy(currEnemy);
            } else {
                currEnemy.update();
            }
        }
    }

    public void draw(Graphics2D g2) {
        for(int i = enemy.size()-1; i >= 0; i--) {
            currEnemy = enemy.get(i);
            currEnemy.draw(g2);
        }
    }

    public void addEnemy(Enemy enemy) {
        this.enemy.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        this.enemy.remove(enemy);
    }

    public void reset() {
        enemy.clear();
        controlThread = new Thread(this);
    }

    public LinkedList<Enemy> getEnemy() {
        return enemy;
    }
}
