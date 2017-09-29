CREATE TABLE "book" (
    "id" INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "title" VARCHAR(30) NOT NULL,
    "author" VARCHAR(30) NOT NULL,
    "isbn" VARCHAR(20),
    "edition" VARCHAR(3),
    "owner" VARCHAR(64),
    "forClass" VARCHAR(63),
    "price" VARCHAR(30) NOT NULL
);