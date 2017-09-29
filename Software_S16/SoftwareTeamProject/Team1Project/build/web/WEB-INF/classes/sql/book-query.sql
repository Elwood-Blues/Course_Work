/**
 * Author:  Alexa
 * Created: Mar 30, 2016
 */

/*******************************************************************************
Alexa:          This file will be used to search a book by title
                NOTE: This is tentative code. Can this be a book-query?
*******************************************************************************/

/* Could we do it this way to do a general book-query?*/

/*SELECT * FROM "book"
WHERE
"title" = ?
OR "author" = ?
OR "forClass" = ?
*/

SELECT * FROM "book" 
WHERE "author" = ?
OR "title" = ?
OR "forClass" = ?
OR "isbn" = ?
