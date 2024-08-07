package activeRecord;

import util.DBConnect;
import util.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Movie {
    private Long movieID = null;
    private String title;
    private int year;
    private char type;

    public Long getMovieID() {
        return movieID;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public char getType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setType(char type) {
        this.type = type;
    }

    public void insert() throws SQLException {
        if (movieID != null) {
            Logger.log("Object already persistent");
            return;
        }

        String sql = "INSERT INTO Movie (Title, Year, Type) VALUES (?, ?, ?);";

        PreparedStatement statement = DBConnect.getConnection().prepareStatement(sql);
        statement.setString(1, title);
        statement.setInt(2, year);
        statement.setString(3, "" + type);
        statement.executeUpdate();
        statement.close();

        sql = "SELECT last_insert_rowid() AS id";
        statement = DBConnect.getConnection().prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        rs.next();
        movieID = rs.getLong(1);
        statement.close();
        DBConnect.commit();
    }

    public void update() throws SQLException {
        if (movieID == null) {
            Logger.log("Object not persistent yet");
            return;
        }

        String sql = "UPDATE Movie SET Title = ?, Year = ?, Type = ? WHERE MovieID = ?;";

        PreparedStatement statement = DBConnect.getConnection().prepareStatement(sql);
        statement.setString(1, title);
        statement.setInt(2, year);
        statement.setString(3, "" + type);
        statement.setLong(4, movieID);
        statement.executeUpdate();
        statement.close();
        DBConnect.commit();
    }

    public void delete() throws SQLException {
        if (movieID == null) {
            Logger.log("Object not persistent yet");
            return;
        }

        String sql = "DELETE FROM Movie WHERE MovieID = ?;";

        PreparedStatement statement = DBConnect.getConnection().prepareStatement(sql);
        statement.setLong(1, movieID);
        statement.executeUpdate();
        statement.close();
        DBConnect.commit();
    }

    public static Movie getMovieById(int id) throws SQLException {
        String sql = "SELECT * FROM Movie WHERE MovieID = ?;";
        PreparedStatement statement = DBConnect.getConnection().prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet rs = statement.executeQuery();
        rs.next();
        Movie movie = new Movie();
        movie.movieID = rs.getLong(1);
        movie.title = rs.getString(2);
        movie.year = rs.getInt(3);
        movie.type = rs.getString(4).charAt(0);
        statement.close();
        return movie;
    }

    public static ArrayList<Movie> getMovieByTitle(String title) throws SQLException {
        String sql = "SELECT * FROM Movie WHERE UPPER(Title) LIKE UPPER('%" + title + "%')";
        Statement statement = DBConnect.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sql);
        ArrayList<Movie> movies = new ArrayList<>();
        while (rs.next()) {
            Movie movie = new Movie();
            movie.movieID = rs.getLong(1);
            movie.title = rs.getString(2);
            movie.year = rs.getInt(3);
            movie.type = rs.getString(4).charAt(0);
            movies.add(movie);
        }
        statement.close();
        return movies;
    }
}
