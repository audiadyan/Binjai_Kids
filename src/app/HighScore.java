package app;

import java.awt.Font;
import java.awt.Graphics2D;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.ReadImage;

public class HighScore extends SuperMenu implements Runnable {
    private String sql;
    private ResultSet res;
    private Font hSkor;
    private String username[] = new String[5], skor[] = new String[5], user;
    private int data, score, x, y;

    HighScore(ReadImage rImage) {
        super(rImage, "/images/background/bg4.png");
        hSkor = new Font("COURIER", Font.BOLD, 30);
    }

    void getData() {
        try{
            sql = "SELECT * FROM binjai_skor ORDER BY skor DESC LIMIT 5";
            res = Config.selQuery(sql);

            data = 0;
            while(res.next()) {
                username[data] = res.getString("username");
                skor[data] = res.getString("skor");
                data++;
            }
        } catch(SQLException e) {
            System.out.println("Error: "+ e.getMessage());
        }
    }

    public void addData(String username, int skor) {
        user = username;
        score = skor;
    }

    public void add() {
        sql = "INSERT INTO binjai_skor(username, skor) VALUES('%s', '%d')";
        sql = String.format(sql, user, score);
        Config.eksQuery(sql);

        sql = "DELETE FROM binjai_skor WHERE no NOT IN(SELECT no FROM binjai_skor ORDER BY skor DESC LIMIT 5)";
        Config.eksQuery(sql);
    }

    @Override
    public void run() {
        add();
    }

    public void draw(Graphics2D g2) {
        x = 120;
        y = 190;

        g2.drawImage(image, 0, 0, null);
        g2.setFont(hSkor);
        for(int i = 0; i < data; i++) {
            g2.drawString(username[i], x, y);
            g2.drawString(skor[i], x+180, y);
            y += 50;
        }
    }
}
