package activeRecord;

import util.DBConnect;
import util.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieCharacter {
    private Long movCharID;
    private String character;
    private String alias = "";
    private int position;
    private Long hasCharacter_movieID;
    private Long plays_personID;

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setHascharacter_movieid(Long hascharacter_movieid) {
        this.hasCharacter_movieID = hascharacter_movieid;
    }

    public void setPlays_personid(Long plays_personid) {
        this.plays_personID = plays_personid;
    }

    public void insert() throws SQLException {
        if (movCharID != null) {
            Logger.log("Object already persistent");
            return;
        }

        String sql = "INSERT INTO MovieCharacter (Character, Alias, Position, HasCharacter_MovieID, Plays_PersonID) VALUES (?, ?, ?, ?, ?);";

        PreparedStatement statement = DBConnect.getConnection().prepareStatement(sql);
        statement.setString(1, character);
        statement.setString(2, alias);
        statement.setInt(3, position);
        statement.setLong(4, hasCharacter_movieID);
        statement.setLong(5, plays_personID);
        statement.executeUpdate();
        statement.close();

        sql = "SELECT last_insert_rowid() AS id";
        statement = DBConnect.getConnection().prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        rs.next();
        movCharID = rs.getLong(1);
        statement.close();
        DBConnect.commit();
    }
}
