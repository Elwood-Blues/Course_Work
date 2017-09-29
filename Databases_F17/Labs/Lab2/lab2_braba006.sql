/**** Section 2.A  ****/
CREATE TABLE police_officer(
    officer_id VARCHAR2(120) NOT NULL PRIMARY KEY,
    officer_name VARCHAR2(120) NOT NULL,
    "rank" VARCHAR2(120),
    dept_name VARCHAR2(120) NOT NULL,
    salary NUMERIC(9,2) NOT NULL,
    CHECK (salary >= 0),
    CHECK (LENGTH(officer_id) < 120),
    CHECK (LENGTH(officer_name) < 120),
    CHECK (LENGTH("rank") < 120),
    CHECK (LENGTH(dept_name) < 120)
    );
    
CREATE TABLE "case" (
    case_num INTEGER NOT NULL UNIQUE,
    "date" DATE NOT NULL,
    lead_detective_id VARCHAR2(120) NOT NULL,
    dept_name VARCHAR2(120) NOT NULL,
    CHECK (LENGTH(lead_detective_id) < 120),
    CHECK (LENGTH(dept_name) < 120),
    CONSTRAINT case_PK PRIMARY KEY (case_num, lead_detective_id),
    /*CONSTRAINT case_unique_caseNum UNIQUE (case_num),*/
    FOREIGN KEY (lead_detective_id) REFERENCES police_officer(officer_id) ON DELETE SET NULL
    );

CREATE TABLE suspect(
    suspect_id VARCHAR2(120) NOT NULL PRIMARY KEY,
    "name" VARCHAR2(120) NOT NULL,
    age INTEGER,
    "description" VARCHAR2(1000) NOT NULL,
    CHECK(LENGTH(suspect_id) < 120),
    CHECK(LENGTH("name") < 120),
    CHECK(LENGTH("description") < 1000)
    );

CREATE TABLE is_suspect_of(
    case_num INTEGER NOT NULL,
    suspect_id VARCHAR2(120) NOT NULL,
    CONSTRAINT is_suspect_of_PK PRIMARY KEY(case_num, suspect_id),
    /*CONSTRAINT iso_unique_sus_id UNIQUE (suspect_id),*/
    CONSTRAINT iso_FK_case_num FOREIGN KEY (case_num) REFERENCES "case"(case_num) ON DELETE CASCADE,
    CONSTRAINT iso_FK_sus_id FOREIGN KEY (suspect_id) REFERENCES suspect(suspect_id) ON DELETE CASCADE
);
 
INSERT ALL
    INTO suspect (suspect_id, "name", age, "description") VALUES ('A12', 'Saruman', '1287', 'Tall with white hair and beard and carries a staff')
    INTO suspect (suspect_id, "name", age, "description") VALUES ('B87', 'Azog', '389', 'Pale white orc')
    INTO suspect (suspect_id, "name", age, "description") VALUES ('C34', 'Sauron', '8942', 'Big fiery eye')
    INTO suspect (suspect_id, "name", age, "description") VALUES ('D67', 'Frodo', '34', '3 feet tall')
    INTO police_officer (officer_id, officer_name, "rank", dept_name, salary) VALUES ('7A39','Bilbo','Rookie', 'Duluth PD', 40000.00)
    INTO police_officer (officer_id, officer_name, "rank", dept_name, salary) VALUES ('8B67', 'Sauron', 'Chief', 'UMD PD', 80000.00)
    INTO police_officer (officer_id, officer_name, "rank", dept_name, salary) VALUES ('7X93', 'Legolas', 'Lieutenant', 'UMD PD', 65000.00)
    INTO police_officer (officer_id, officer_name, "rank", dept_name, salary) VALUES ('3E52', 'Lady Galadriel', 'Rookie', 'Duluth PD', 45000.00)
    INTO police_officer (officer_id, officer_name, "rank", dept_name, salary) VALUES ('9A89', 'Gimli', 'Captain', 'Cloquet PD', 60000.00)
    INTO "case" (case_num, "date", lead_detective_id, dept_name) VALUES ('12', TO_DATE('01/02/1987', 'MM/DD/YYYY'), '8B67', 'Duluth PD')
    INTO "case" (case_num, "date", lead_detective_id, dept_name) VALUES ('15', TO_DATE('02/04/1979', 'MM/DD/YYYY'), '8B67', 'UMD PD')
    INTO "case" (case_num, "date", lead_detective_id, dept_name) VALUES ('26', TO_DATE('06/12/2005', 'MM/DD/YYYY'), '3E52', 'Duluth PD')
    INTO "case" (case_num, "date", lead_detective_id, dept_name) VALUES ('22', TO_DATE('09/07/2017', 'MM/DD/YYYY'), '3E52', 'UMD PD')
    INTO "case" (case_num, "date", lead_detective_id, dept_name) VALUES ('17', TO_DATE('04/05/2016', 'MM/DD/YYYY'), '9A89', 'Cloquet PD')
    INTO is_suspect_of (case_num, suspect_id) VALUES ('12', 'D67')
    INTO is_suspect_of (case_num, suspect_id) VALUES ('26', 'B87')
    INTO is_suspect_of (case_num, suspect_id) VALUES ('12', 'A12')
    INTO is_suspect_of (case_num, suspect_id) VALUES ('17', 'C34')
    INTO is_suspect_of (case_num, suspect_id) VALUES ('15', 'B87')
SELECT * FROM dual;

/**** 2.B ****/
DELETE FROM "case"
WHERE "date" <= SYSDATE - INTERVAL '20' YEAR;

/**** 2.C ****/
UPDATE police_officer
SET "rank" = 'Lieutenant'
WHERE "rank" = 'Rookie' AND dept_name = 'Duluth PD';

/**** 2.D ****/
SELECT OFFICER_NAME ,SALARY FROM 
    (SELECT *  FROM police_officer order by salary desc) 
WHERE rownum <= 2;

/**** 2.E ****/
SELECT police_officer.officer_name, COUNT(lead_detective_id) FROM police_officer
INNER JOIN "case"
ON police_officer.officer_id = "case".lead_detective_id 
/*AND police_officer.dept_name = 'UMD PD'*/ group by police_officer.officer_name;

/**** 2.F ****/
SELECT "name" FROM suspect s
INNER JOIN is_suspect_of i
    ON s.suspect_id = i.suspect_id
INNER JOIN "case" c
    ON i.case_num = c.case_num AND c.lead_detective_id = '3E52';

/**** 2.G ****/
SELECT p.officer_name FROM police_officer p
INNER JOIN "case" c
    ON p.officer_id = c.lead_detective_id
INNER JOIN is_suspect_of i
    ON c.case_num = i.case_num
INNER JOIN suspect t
    ON t.suspect_id = i.suspect_id
WHERE t."description" LIKE ('%staff%');

/**** 2.H ****/
SELECT p."rank" FROM police_officer p
INNER JOIN "case" c
    ON p.officer_id = c.lead_detective_id AND c.dept_name IN ('DULUTH PD','UMD PD');

/**** 2.I ****/
SELECT p.officer_name FROM police_officer p
WHERE p.salary > (SELECT AVG(salary) from police_officer
WHERE dept_name = 'UMD PD');

/**** 2.J ****/
SELECT AVG(p.salary) FROM police_officer p
INNER JOIN (SELECT dept_name  FROM police_officer
            GROUP BY dept_name
            HAVING COUNT(*) > 1)
ON p.salary > '41000.00';

/**** 2.K ****/
SELECT OFFICER_NAME ,SALARY FROM 
    (SELECT *  FROM police_officer order by salary ASC) 
WHERE rownum >= 1;

SELECT p.officer_name, p.salary FROM police_officer p
WHERE p.salary <> (SELECT MIN(s.salary) from police_officer s);