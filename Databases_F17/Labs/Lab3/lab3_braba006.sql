/**** Activity 2.a.i ****/
SELECT DISTINCT author_name FROM (

	SELECT author_name, book_id FROM book_authors a, 
    	(SELECT author_name, book_id FROM book_authors b
    	WHERE a.book_id = b.book_id AND a.author_name < b.author_name
    	HAVING COUNT(*)
    	GROUP BY book_id
	)
    HAVING COUNT(*)
)

/**** 2.a.ii ****/
SELECT name FROM borrower 
NATURAL JOIN book_loans
ORDER BY COUNT(card_no) DESC LIMIT 2)
WHERE ROWNUM = 2;


/**** 2.a.iii ****/
SELECT name FROM (
    (SELECT * FROM book_loans
    NATURAL JOIN borrower
    WHERE "name" = 'Lucy') lucy_set
    /
    (SELECT * FROM book_loans
    NATURAL JOIN borrow
    );