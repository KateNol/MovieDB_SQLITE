-- Löschen aller Tabellen

-- Löschen der MovieCharacter-Tabelle
DROP TABLE IF EXISTS MovieCharacter;
-- Löschen der Person-Tabelle
DROP TABLE IF EXISTS Person;
-- Löschen der MovieGenre-Tabelle
DROP TABLE IF EXISTS HasGenre;
-- Löschen der Movie-Tabelle
DROP TABLE IF EXISTS Movie;
-- Löschen der Genre-Tabelle
DROP TABLE IF EXISTS Genre;

-- Löschen aller Daten aus den Tabellen

-- Löschen von Daten aus der MovieCharacter-Tabelle
DELETE FROM MovieCharacter;
-- Löschen von Daten aus der MovieGenre-Tabelle
DELETE FROM HasGenre;
-- Löschen von Daten aus der Person-Tabelle
DELETE FROM Person;
-- Löschen von Daten aus der Movie-Tabelle
DELETE FROM Movie;
-- Löschen von Daten aus der Genre-Tabelle
DELETE FROM Genre;