# MovieDB_SQLITE
Hauptproblem : es wird vor dem Testen der testmethoden gar keine Datenbank mit mindesten 5 Einträgen erstellt. 
              --> es werden immer nur die testmethoden erstellt, aber nicht auf einer existierenden Datenbank 
##

##Table.class
  - überprüfen ob try ( prepared statements )
    --> richtige Version für Transaktionen ???
    
  - import util.DBConnect;
import java.sql.SQLException;
import java.sql.Statement;

public class Table {

    public static void createTableGenre() throws SQLException {
        String query = "CREATE TABLE Genre (GenreID INTEGER PRIMARY KEY, Genre TEXT)";
        try (Statement stm = DBConnect.getConnection().createStatement()) {
            stm.executeUpdate(query);
            DBConnect.commit();
        }
    }

    public static void createTableMovie() throws SQLException {
        String query = "CREATE TABLE Movie(" +
                "    MovieID INTEGER PRIMARY KEY," +
                "    Title TEXT NOT NULL," +
                "    Year INTEGER NOT NULL," +
                "    Type TEXT NOT NULL)";
        try (Statement stm = DBConnect.getConnection().createStatement()) {
            stm.executeUpdate(query);
            DBConnect.commit();
        }
    }

    public static void createTableHasGenre() throws SQLException {
        String query = "CREATE TABLE HasGenre(" +
                "    GenreID INTEGER NOT NULL," +
                "    MovieID INTEGER NOT NULL," +
                "    PRIMARY KEY (GenreID, MovieID)," +
                "    FOREIGN KEY (GenreID) REFERENCES Genre(GenreID)," +
                "    FOREIGN KEY (MovieID) REFERENCES Movie(MovieID))";
        try (Statement stm = DBConnect.getConnection().createStatement()) {
            stm.executeUpdate(query);
            DBConnect.commit();
        }
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
        try (Statement stm = DBConnect.getConnection().createStatement()) {
            stm.executeUpdate(query);
            DBConnect.commit();
        }
    }

    public static void createTablePerson() throws SQLException {
        String query = "CREATE TABLE Person(" +
                "    PersonID INTEGER PRIMARY KEY," +
                "    Name TEXT NOT NULL," +
                "    Sex TEXT NOT NULL)";
        try (Statement stm = DBConnect.getConnection().createStatement()) {
            stm.executeUpdate(query);
            DBConnect.commit();
        }
    }

    public static void createAllTables() throws SQLException {
        try {
            DBConnect.getConnection().setAutoCommit(false);  // Begin transaction
            createTableGenre();
            createTableMovie();
            createTablePerson();
            createTableHasGenre();
            createTableMovieCharacter();
            DBConnect.commit();  // Commit transaction
        } catch (SQLException e) {
            DBConnect.rollback();  // Rollback transaction in case of error
            throw e;
        } finally {
            DBConnect.getConnection().setAutoCommit(true);  // Reset autocommit to default
        }
    }

    public static void dropAllTables() throws SQLException {
        String[] tables = {"HasGenre", "MovieCharacter", "Genre", "Movie", "Person"};
        try {
            DBConnect.getConnection().setAutoCommit(false);  // Begin transaction
            try (Statement stm = DBConnect.getConnection().createStatement()) {
                for (String table : tables) {
                    stm.executeUpdate("DROP TABLE IF EXISTS " + table);
                }
            }
            DBConnect.commit();  // Commit transaction
        } catch (SQLException e) {
            DBConnect.rollback();  // Rollback transaction in case of error
            throw e;
        } finally {
            DBConnect.getConnection().setAutoCommit(true);  // Reset autocommit to default
        }
    }
}
