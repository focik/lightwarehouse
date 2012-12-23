CREATE TABLE "dbver" (
    "version" INTEGER NOT NULL,
    "update" TEXT NOT NULL
);
CREATE TABLE `operation` (
  id INTEGER PRIMARY KEY ASC AUTOINCREMENT,
  id_product INTEGER,
  price REAL,
  quantity REAL,
  last INTEGER,
  add_date TEXT
);
CREATE TABLE product (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "name" TEXT,
    "um" TEXT,
    "vat" INTEGER,
    "mod_date" TEXT,
    "id_store" INTEGER,
    "quantity" REAL,
    "price" REAL,
    "price_sell" REAL
);
CREATE TABLE store (id INTEGER PRIMARY KEY ASC, name TEXT);
CREATE INDEX id_product  on operation(id_product );
