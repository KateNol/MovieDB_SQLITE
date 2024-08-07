package activeRecord;

import util.DBConnect;
import util.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HasGenre {
    private Long genreid;
    private Long movieid;

    public void setGenreid(Long genreid) {
        this.genreid = genreid;
    }

    public void setMovieid(Long movieid) {
        this.movieid = movieid;
    }

    public Long getMovieid() {
        return movieid;
    }

    public Long getGenreid() {
        return genreid;
    }

    public void insert() throws SQLException{
        if(genreid == null || movieid == null){
            Logger.log("Attributes not set");
            return;
        }

        String query = "INSERT INTO HasGenre (GenreID, MovieID) VALUES (?, ?)";
        PreparedStatement stmt = DBConnect.getConnection().prepareStatement(query);
        stmt.setLong(1, genreid);
        stmt.setLong(2, movieid);
        stmt.executeUpdate();
        stmt.close();
        DBConnect.commit();
    }
}