CREATE TABLE "dbver" (
    "version" INTEGER NOT NULL,
    "update" TEXT NOT NULL
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
