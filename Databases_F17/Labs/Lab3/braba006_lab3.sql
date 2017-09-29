/**** Activity 2.a.i ****/
SELECT author_name FROM (

SELECT author_name, book_id FROM book_authors a, 
    (SELECT author_name, book_id FROM book_authors) b
    WHERE a.book_id = b.book_id AND a.author_name < b.author_name
    HAVING COUNT(*) > 1
    GROUP BY book_id) 




SELECT author_name FROM book_authors
GROUP BY book_id
HAVING COUNT(name) > 1;

/**** 2.a.ii ****/
SELECT name FROM borrower 
NATURAL JOIN book_loans
ORDER BY COUNT(card_no) DESC LIMIT 2)
WHERE ROWNUM = 2;

/**** 2.a.iii ****/
SELECT name FROM (
    (SELECT * FROM book_loans
    NATURAL JOIN borrower), s1
    (SELECT * FROM book_loans
    NATURAL JOIN borrow) s2
    WHERE s1.book_id = s2.book_id 
    AND s1.name <> s2.name 
    AND s2.name = 'Lucy');