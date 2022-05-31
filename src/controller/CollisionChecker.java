package controller;

import java.util.LinkedList;

import entity.Player;
import entity.Enemy;

public class CollisionChecker {
    public static boolean collCheck(Player player, LinkedList<Enemy> enemy) {
        for(int i = 0; i < enemy.size(); i++) {
            if(player.getBounds().intersects(enemy.get(i).getBounds())) {
                return true;
            }
        }
        return false;
    }
}
