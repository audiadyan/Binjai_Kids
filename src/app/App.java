package app;

import java.sql.SQLException;

import javax.swing.JFrame;

public class App {
    public static final String TITLE = "Binjai Kids 2.1"; // nama aplikasi
    
    public static void main(String[] args) throws SQLException {   
        Config.runDB();
        
        JFrame window = new JFrame(); // buat objek frame
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // setting close operation frame
        window.setTitle(TITLE); // setting nama aplikasi
        window.setResizable(false); // agar frame tidak bisa dirubah ukurannya

        Game game = new Game(); // buat objek game (panel)
        window.add(game); // tambahkan game(panel) ke frame
        window.pack(); // jFrame meneyesuaikan ukuran dengan objek didalamnya

        window.setLocationRelativeTo(null); // posisi frame di tengah layar
        window.setVisible(true); // menampilkan frame

        game.startGameThread();
    }
}