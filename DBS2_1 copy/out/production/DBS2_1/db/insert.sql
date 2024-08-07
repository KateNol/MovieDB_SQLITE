-- Einfügen von Daten in die Genre-Tabelle
INSERT INTO Genre (GenreID, Genre) VALUES (1, 'Drama');
INSERT INTO Genre (GenreID, Genre) VALUES (2, 'Romance');
INSERT INTO Genre (GenreID, Genre) VALUES (3, 'Abenteuer');
INSERT INTO Genre (GenreID, Genre) VALUES (4, 'Fantasy');

-- Einfügen von Daten in die Movie-Tabelle
INSERT INTO Movie (MovieID, Title, Year, Type) VALUES (1, 'Die Verlegerin', 2017, 'M');
INSERT INTO Movie (MovieID, Title, Year, Type) VALUES (2, 'La La Land', 2016, 'M');
INSERT INTO Movie (MovieID, Title, Year, Type) VALUES (3, 'Der Herr der Ringe: Die Gefährten', 2001, 'M');

-- Einfügen von Daten in die Person-Tabelle
INSERT INTO Person (PersonID, Name, Sex) VALUES (1, 'Meryl Streep', 'F');
INSERT INTO Person (PersonID, Name, Sex) VALUES (2, 'Emma Stone', 'F');
INSERT INTO Person (PersonID, Name, Sex) VALUES (3, 'Elijah Wood', 'M');

-- Einfügen von Daten in die MovieGenre-Tabelle
INSERT INTO HasGenre (MovieID, GenreID) VALUES (1, 1);
INSERT INTO HasGenre (MovieID, GenreID) VALUES (2, 2);
INSERT INTO HasGenre (MovieID, GenreID) VALUES (3, 3);

-- Einfügen von Daten in die MovieCharacter-Tabelle
INSERT INTO MovieCharacter (MovCharID, Character, Alias, Position, hasCharacter_MovieID, plays_PersonID) VALUES (1, 'Katharine Graham', 'Publisher', 1, 1, 1);
INSERT INTO MovieCharacter (MovCharID, Character, Alias, Position, hasCharacter_MovieID, plays_PersonID) VALUES (2, 'Mia Dolan', 'Aspiring Actress', 2, 1, 2);
INSERT INTO MovieCharacter (MovCharID, Character, Alias, Position, hasCharacter_MovieID, plays_PersonID) VALUES (3, 'Frodo Baggins', 'Ring Bearer', 3, 2, 3);


SELECT Name FROM Person WHERE UPPER(Name) LIKE UPPER('%E%');