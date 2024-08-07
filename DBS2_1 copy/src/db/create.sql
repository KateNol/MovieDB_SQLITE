-- Erstellen der Genre-Tabelle
CREATE TABLE Genre (
    GenreID INTEGER PRIMARY KEY,
    Genre TEXT
);

-- Erstellen der Movie-Tabelle
CREATE TABLE Movie (
    MovieID INTEGER PRIMARY KEY,
    Title TEXT,
    Year INTEGER,
    Type CHAR
);

-- Erstellen der MovieCharacter-Tabelle
CREATE TABLE MovieCharacter (
    MovCharID INTEGER PRIMARY KEY,
    Character TEXT,
    Alias TEXT,
    Position INTEGER,
    hasCharacter_MovieID INTEGER,
    plays_PersonID INTEGER,
    FOREIGN KEY (hasCharacter_MovieID) REFERENCES Movie(MovieID),
    FOREIGN KEY (plays_PersonID) REFERENCES Person(PersonID)
);

-- Erstellen der Person-Tabelle
CREATE TABLE Person (
    PersonID INTEGER PRIMARY KEY,
    Name TEXT,
    Sex CHAR
);

-- Erstellen der MovieGenre-Beziehungstabelle
CREATE TABLE HasGenre (
    MovieID INTEGER,
    GenreID INTEGER,
    PRIMARY KEY (MovieID, GenreID),
    FOREIGN KEY (MovieID) REFERENCES Movie(MovieID),
    FOREIGN KEY (GenreID) REFERENCES Genre(GenreID)
);