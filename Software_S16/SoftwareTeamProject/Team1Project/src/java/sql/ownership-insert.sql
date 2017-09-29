/**
 * Author:  Peter Braband, Michael Ellison, Alexa Chiu, Grant Page, Abigal Reed
 * Created: Apr 20, 2016
 */

INSERT INTO "ownership" ("OWNER", "BOOK")
VALUES(
(SELECT "person"."id" FROM "person"
        WHERE "person"."email" = ?
),

(SELECT "book"."id" FROM "book"
        WHERE "book"."title" = ?
)
)
