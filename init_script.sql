--DROP TABLE INGREDIENTS;
--DROP TABLE COMMANDE;
--DROP TABLE SERVEURS;
--DROP TABLE ING_CMD;

CREATE TABLE SERVEURS(ID_SERVEUR INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NOM_SERVEUR TEXT NOT NULL);
CREATE TABLE COMMANDE(ID_COMMANDE INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NUM_COMMANDE INTEGER NOT NULL, STATUS_COMMANDE TEXT NOT NULL, DATE TEXT NOT NULL);
CREATE TABLE ING_CMD (ID_COMMANDE INTEGER, ID_INGREDIENT INTEGER, NUM_ORDRE INTEGER);
CREATE TABLE INGREDIENTS (ID_INGREDIENT INTEGER PRIMARY KEY AUTOINCREMENT , LIBELLE TEXT, STOCK INTEGER, TYPE_INGREDIENT TEXT);


INSERT INTO SERVEURS(NOM_SERVEUR) VALUES
  ('Kilian'),
  ('Antoine'),
  ('Emma'),
  ('Justine'),
  ('Simon'),
  ('Camille'),
  ('Charlotte'),
  ('Marceau'),
  ('Lucas'),
  ('Johann'),
  ('Ilan'),
  ('Maxence'),
  ('Soka'),
  ('Damien'),
  ('Alej'),
  ('Paul'),
  ('Antoine'),
  ('Alex');

INSERT INTO INGREDIENTS (LIBELLE, STOCK, TYPE_INGREDIENT) VALUES
  ('Coca-Cola',10,'Boisson'),
  ('Ice Tea',10,'Boisson'),
  ('7up',10,'Boisson'),
  ('Schweppes Agrumes',10,'Boisson'),
  ('Oasis Tropical',10,'Boisson');
INSERT INTO INGREDIENTS (LIBELLE, STOCK, TYPE_INGREDIENT) VALUES
  ('Grenadine',10,'Boisson'),
  ('Algérienne',10,'Sauce'),
  ('Barbecue',10,'Sauce'),
  ('Burger',10,'Sauce'),
  ('Curry',10,'Sauce'),
  ('Kebab',10,'Sauce'),
  ('Ketchup',10,'Sauce'),
  ('Mayo',10,'Sauce'),
  ('Moutarde',10,'Sauce'),
  ('Oignon-poivron',10,'Sauce'),
  ('Pomme Frite',10,'Sauce'),
  ('Salade',10,'Sauce'),
  ('Samourai',10,'Sauce'),
  ('Tartare',10,'Sauce');
INSERT INTO INGREDIENTS (LIBELLE, STOCK, TYPE_INGREDIENT) VALUES
  ('Bueno',10,'Dessert'),
  ('M&Ms',10,'Dessert'),
  ('Mars',10,'Dessert'),
  ('Country',10,'Dessert'),
  ('Délice',10,'Dessert'),
  ('Kit Kat',10,'Dessert'),
  ('Kinder Maxi',10,'Dessert'),
  ('Skittles normaux',10,'Dessert'),
  ('Skittles acidulés',10,'Dessert'),
  ('Lion',10,'Dessert'),
  ('Crêpe',10,'Dessert'),
  ('Panini nut',10,'Dessert'),
  ('Beignet Chocolat',10,'Dessert'),
  ('Beignet Framboise',10,'Dessert'),
  ('Beignet Pomme',10,'Dessert'),
  ('Donut Chocolat',10,'Dessert'),
  ('Donut Sucre',10,'Dessert'),
  ('Muffin',10,'Dessert');
INSERT INTO INGREDIENTS (LIBELLE, STOCK, TYPE_INGREDIENT) VALUES
  ('',10,''),
  ('',10,''),
  ('',10,'');

SELECT * FROM INGREDIENTS;
SELECT * FROM SERVEURS;