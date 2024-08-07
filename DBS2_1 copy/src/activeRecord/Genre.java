package activeRecord;

import util.DBConnect;
import util.Logger;

import java.sql.*;

public class Genre {
    private Long genreID = null;
    private String genre;

    public Long getGenreID() {
        return genreID;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void insert() throws SQLException {
        if (genreID != null) {
            Logger.log("Object already persistent");
            return;
        }

        String sql = "INSERT INTO Genre (Genre) VALUES (?)";

        PreparedStatement statement = DBConnect.getConnection().prepareStatement(sql);
        statement.setString(1, genre);
        statement.executeUpdate();
        statement.close();
        sql = "SELECT last_insert_rowid() AS id";
        statement = DBConnect.getConnection().prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        rs.next();
        genreID = rs.getLong(1);
        statement.close();
        DBConnect.commit();
    }
}
