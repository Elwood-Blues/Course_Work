/*SELECT 
    "author"."writer" AS "author",
    "title"."name" AS "title",
    "isbn"."number" AS "isbn"
FROM "author", "title", "isbn", "book"
WHERE
    "book"."author" = "author"."writer"
AND "book"."title" = "title"."name"
AND "book"."isbn" = "isbn"."number
*/

SELECT * FROM "book" 