import util.DBConnect;
import java.sql.SQLException;
import java.sql.Statement;

public class Table {

    public static void createTableGenre() throws SQLException {
        String query = "CREATE TABLE Genre (GenreID INTEGER PRIMARY KEY,Genre TEXT)";
        Statement stm = DBConnect.getConnection().createStatement();
        stm.executeUpdate(query);
        DBConnect.commit();

    }

    public static void createTableMovie() throws SQLException {
        String query = "CREATE TABLE Movie(" +
                "    MovieID INTEGER PRIMARY KEY," +
                "    Title TEXT NOT NULL," +
                "    YEAR INTEGER NOT NULL," +
                "    Type TEXT NOT NULL)";
        Statement stm = DBConnect.getConnection().createStatement();
        stm.executeUpdate(query);
        DBConnect.commit();
    }

    public static void createTableHasGenre() throws SQLException {

        String query = "CREATE TABLE HasGenre(" +
                "    GenreID INTEGER NOT NULL," +
                "    MovieID INTEGER NOT NULL," +
                "    PRIMARY KEY (GenreID, MovieID)," +
                "    FOREIGN KEY (GenreID) REFERENCES Genre(GenreID)," +
                "    FOREIGN KEY (MovieID) REFERENCES Movie(MovieID))";
        Statement stm = DBConnect.getConnection().createStatement();
        stm.executeUpdate(query);
        DBConnect.commit();

    }

    public static void createTableMovieCharacter() throws SQLException {
        String query = "CREATE TABLE MovieCharacter(" +
                "    MovCharID INTEGER PRIMARY KEY," +
                "    Character TEXT NOT NULL," +
                "    Alias TEXT," +
                "    Position INTEGER NOT NULL," +
                "    hasCharacter_MovieID INTEGER NOT NULL," +
                "    plays_PersonID INTEGER NOT NULL," +
                "    FOREIGN KEY (hasCharacter_MovieID) REFERENCES Movie(MovieID)," +
                "    FOREIGN KEY (plays_PersonID) REFERENCES Person(PersonID))";
        Statement stm = DBConnect.getConnection().createStatement();
        stm.executeUpdate(query);
        DBConnect.commit();
    }

    public static void createTablePerson() throws SQLException {
        String query = "CREATE TABLE PERSON(" +
                "    PersonID INTEGER PRIMARY KEY," +
                "    Name TEXT NOT NULL," +
                "    Sex TEXT NOT NULL)";
        Statement stm = DBConnect.getConnection().createStatement();
        stm.executeUpdate(query);
        DBConnect.commit();
    }

    public static void createAllTables() throws SQLException {
        createTableGenre();
        createTableMovie();
        createTablePerson();
        createTableHasGenre();
        createTableMovieCharacter();
    }

    public static void dropAllTables() throws SQLException {
        String query = "DROP TABLE IF EXISTS HasGenre";
        Statement stm = DBConnect.getConnection().createStatement();
        stm.executeUpdate(query);

        query = "DROP TABLE IF EXISTS MovieCharacter";
        stm = DBConnect.getConnection().createStatement();
        stm.executeUpdate(query);
        DBConnect.commit();

        query = "DROP TABLE IF EXISTS Genre";
        stm = DBConnect.getConnection().createStatement();
        stm.executeUpdate(query);

        query = "DROP TABLE IF EXISTS Movie";
        stm = DBConnect.getConnection().createStatement();
        stm.executeUpdate(query);
        DBConnect.commit();

        query = "DROP TABLE IF EXISTS Person";
        stm = DBConnect.getConnection().createStatement();
        stm.executeUpdate(query);
        DBConnect.commit();
    }
}
