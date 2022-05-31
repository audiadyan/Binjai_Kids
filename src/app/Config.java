package app;

import java.sql.*;

public class Config {
    private static Statement STM;

    public static void runDB() throws SQLException {
        try{
            String url = "jdbc:sqlite:binjaikidsdb.db";
            Connection CONN = DriverManager.getConnection(url);
            STM = CONN.createStatement();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    static void eksQuery(String sql) {
        try {
            STM.executeUpdate(sql);
        } catch(SQLException e) {
            System.out.println("Gagal!" + e.getMessage());
        }
    }

    static ResultSet selQuery(String sql) {
        ResultSet res = null;
        
        try{
            res = STM.executeQuery(sql);
        } catch(SQLException e) {
            System.out.println("Error: "+ e.getMessage());            
        }

        return res;
    }
}