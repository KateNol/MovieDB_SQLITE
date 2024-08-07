
import activeRecord.*;
import util.DBConnect;
import util.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws SQLException {
        DBConnect.setConnection("jdbc:sqlite:movie.db");

        Table.dropAllTables();
        Table.createAllTables();

        testInsert();

        testGetMovieByID();

        testGetMovieByTitle();

        DBConnect.closeConnection();
    }

    public static void testInsert() throws SQLException {
        boolean ok = false;
        try {

            Movie movie = new Movie();
            movie.setTitle("Harry Potter 3");
            movie.setYear(2012);
            movie.setType('C');
            movie.insert();

            Genre genre = new Genre();
            genre.setGenre("Action");
            genre.insert();

            Person person = new Person();
            person.setName("Peter Pan");
            person.setSex('M');
            person.insert();

            MovieCharacter chr = new MovieCharacter();
            chr.setHascharacter_movieid(movie.getMovieID());
            chr.setPlays_personid(person.getPersonID());
            chr.setCharacter("Hauptrolle");
            chr.setAlias(null);
            chr.setPosition(1);
            chr.insert();

            HasGenre hasGenre = new HasGenre();
            hasGenre.setGenreid(genre.getGenreID());
            hasGenre.setMovieid(movie.getMovieID());
            hasGenre.insert();
            ok = true;
        } finally {
            if (!ok){
                Logger.log("Test failed!");
            } else {
                Logger.log("Insert test successful!");
                Logger.log("");
            }

        }
    }

    public static void testGetMovieByID() throws SQLException {
        Logger.log("Test getMovieByID");
        boolean ok = false;
        try {
            Movie movie = Movie.getMovieById(1);
            Logger.log("Movie: Title: " + movie.getTitle() + " Year: " + movie.getYear() + " Type: " + movie.getType());
            ok = true;
        } finally {
            if (!ok)
                Logger.log("Test failed!");
        }
        Logger.log("");
    }

    public static void testGetMovieByTitle() throws SQLException {
        Logger.log("Test getMovieByTitle");
        boolean ok = false;
        try {
            ArrayList<Movie> movies = Movie.getMovieByTitle("potter");
            for (Movie movie : movies){
                Logger.log("Movie: Title: " + movie.getTitle() + " Year: " + movie.getYear() + " Type: " + movie.getType());
            }
            ok = true;
        } finally {
            if (!ok)
                Logger.log("Test failed!");
        }

    }

}