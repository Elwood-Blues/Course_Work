/*Peter Braband #3418868 */
/* Part A */
CREATE TABLE writer(
    "id" VARCHAR(120) NOT NULL PRIMARY KEY,
    "name" VARCHAR(120),
    age INTEGER
);
/
CREATE TABLE reviewer(
    "id" VARCHAR(120) NOT NULL PRIMARY KEY,
    reviewer_name VARCHAR(120),
    salary NUMERIC(9,2) NOT NULL,
    CHECK (salary >= 0)
);
/
CREATE TABLE college_department(
    dept_name VARCHAR(120) NOT NULL PRIMARY KEY,
    dept_address VARCHAR(120)
);
/
CREATE TABLE is_reviewer_of(
    writer_id VARCHAR(120) NOT NULL,
    reviewer_id VARCHAR(120) NOT NULL,
    dept_name VARCHAR(120) NOT NULL,
    CONSTRAINT is_reviewer_of_PK PRIMARY KEY (writer_id, reviewer_id, dept_name),
    FOREIGN KEY (reviewer_id) REFERENCES reviewer("id") ON DELETE CASCADE,
    FOREIGN KEY (writer_id) REFERENCES writer("id") ON DELETE CASCADE,
    FOREIGN KEY (dept_name) REFERENCES college_department(dept_name) ON DELETE CASCADE
);
/
/** Part B **/
CREATE OR REPLACE TRIGGER bad_salary_update
BEFORE UPDATE ON reviewer
FOR EACH ROW
BEGIN
    IF :new.salary<:old.salary THEN
        RAISE_APPLICATION_ERROR(-20000,'New salary cannot be less than old salary!');
    END IF;
END;
/
/** Part C **/
CREATE OR REPLACE TRIGGER writer_reviewer_insert_update
BEFORE INSERT OR UPDATE ON is_reviewer_of
FOR EACH ROW
BEGIN
    (SELECT dept_name, COUNT(reviewer_id) FROM is_reviewer_of
        GROUP BY dept_name);
    IF COUNT(reviewer_id) > 1 THEN
        RAISE_APPLICATION_ERROR(-20000,'Cannot have more than one reviewer per dept');
    END IF;
END;
/
INSERT INTO reviewer VALUES ('C34', 'Mike', 50000.00);
INSERT INTO is_reviewer_of
/** Part D **/
DECLARE
     SELECT * FROM reviewer;
BEGIN
    UPDATE reviewer SET salary = 100.00 WHERE "id" = 'C34';
END;
/
/** Part E **/
--DECLARE
    --SELECT * FROM is_reviewer_of;
BEGIN
    INSERT INTO is_reviewer_of VALUES('C34', 'C35', 'CS');
    INSERT INTO is_reviewer_of VALUES('C34', 'C35', 'CS');
END;