/*Peter Braband #3418868 */
/* Part A */
CREATE OR REPLACE FUNCTION compute_mean_books
RETURN NUMBER IS
    b_id book_loans.book_id%type;
    card_no book_loans.card_no%type;
    my_sum NUMERIC := 0.0;
    CURSOR my_cursor IS
        SELECT COUNT(book_id), card_no
        FROM book_loans
        GROUP BY card_no;
BEGIN
    OPEN my_cursor;
    LOOP
        FETCH my_cursor INTO b_id, card_no;
        EXIT WHEN my_cursor%NOTFOUND;
        my_sum := my_sum + b_id;
    END LOOP;
    my_sum := my_sum / my_cursor%ROWCOUNT;
    CLOSE my_cursor;
    RETURN my_sum;
END;
/
/* Part B */
CREATE OR REPLACE FUNCTION compute_sd_books
RETURN NUMBER IS
    my_mean NUMERIC:= 0.0;
    my_n INTEGER := 0;
    b_id book_loans.book_id%type;
    card_no book_loans.card_no%type;
    my_running_val NUMERIC := 0.0;
    my_sd NUMERIC := 0.0;
    CURSOR my_cursor IS
        SELECT COUNT(book_id), card_no
        FROM book_loans
        GROUP BY card_no;
BEGIN
    OPEN my_cursor;
    my_mean := compute_mean_books();
    LOOP
        FETCH my_cursor INTO b_id, card_no;
        EXIT WHEN my_cursor%NOTFOUND;
        my_n := my_n + 1;
        my_running_val := my_running_val + POWER(b_id - my_mean, 2);
    END LOOP;
    my_sd := SQRT(my_running_val / (my_n -1));
    CLOSE my_cursor;
    RETURN my_sd;
END;
/
CREATE TYPE numsarray IS VARRAY(2) OF NUMERIC;
/
/* Part C */
CREATE OR REPLACE PROCEDURE 
mean_and_sd(nums OUT numsarray)
IS
    my_sd NUMERIC := 0;
    my_mean NUMERIC := 0;
    nums numsarray := numsarray();
BEGIN
    my_sd := compute_sd_books();
    my_mean := compute_mean_books();
    nums := nums(my_mean, my_sd);
END;

/* Part D */
DECLARE
    nums numsarray := numsarray();
BEGIN
    nums := mean_and_sd();
    dbms_output.put_line('Mean: ' || nums(0));
    dbms_output.put_line('SD: ' || nums(1));
END;