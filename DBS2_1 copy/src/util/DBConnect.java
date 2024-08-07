package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private static Connection conn;
    private static String url;

    public static Connection getConnection() {
        if (url == null) {
            Logger.logError("Failed to connect to database, connection url is not set");
        }
        try {
            if (conn == null || conn.isClosed()) {
                conn = null;
                conn = DriverManager.getConnection(url);
                conn.setAutoCommit(false);
            }
        } catch (SQLException e) {
            Logger.logError("Failed to connect to database: " + e.getMessage());

        }
        return conn;
    }

    public static void commit(){
        try {
            conn.commit();
        } catch (SQLException e) {
            Logger.logError(e.getMessage());
        }
    }

    public static void setConnection(String url){
        DBConnect.url = url;
    }

    public static void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            Logger.logError(e.getMessage());
        }
    }
}