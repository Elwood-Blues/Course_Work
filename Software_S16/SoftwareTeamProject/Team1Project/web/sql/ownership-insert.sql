/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Peter
 * Created: Apr 13, 2016
 */
INSERT INTO "ownership" ("OWNER", "BOOK")
VALUES(
    (SELECT "person"."id" FROM "person"
        WHERE "person"."first_name" = 'John'
        AND "person"."last_name" = 'Doe'),
    (SELECT "book"."id" FROM "book"
        WHERE "book"."title" = 'Where the Wild Things Are')
    );


/*
INSERT INTO "ownership" ( "owner", "book")
VALUES (
    (SELECT "person"."id" FROM "person"
        WHERE  "person"."first_name" = "?"
        AND "person"."last_name" = "?"),
    (SELECT "book"."id" FROM "book"
        WHERE "book"."title" = "?")
);
*/