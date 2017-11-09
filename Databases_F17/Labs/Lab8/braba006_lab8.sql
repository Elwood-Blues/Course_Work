/***  Peter Braband
**  Lab8
** #3418868 */
CREATE TABLE client(
    ssn NUMERIC(8,0) NOT NULL PRIMARY KEY
);
/
CREATE TABLE client_phones(
    client_ssn NUMERIC(8,0) NOT NULL,
    phone_number INTEGER NOT NULL,
    CONSTRAINT client_phones_PK PRIMARY KEY (client_ssn, phone_number),
    FOREIGN KEY (client_ssn) REFERENCES client(ssn)
);
/
CREATE TABLE policy(
    "number" INTEGER NOT NULL PRIMARY KEY,
    pdate DATE NOT NULL,
    client_ssn NUMERIC(8,0) NOT NULL,
    FOREIGN KEY (client_ssn) REFERENCES client(ssn)
);
/
CREATE TABLE payment(
    policy_num INTEGER NOT NULL,
    payment_date DATE NOT NULL,
    CONSTRAINT payment_pk PRIMARY KEY (policy_num, payment_date),
    FOREIGN KEY (policy_num) REFERENCES policy("number")
);
/
CREATE TABLE vehicle(
    vin VARCHAR(120) NOT NULL PRIMARY KEY,
    make VARCHAR(60) NOT NULL,
    policy_num INTEGER NOT NULL,
    FOREIGN KEY (policy_num) REFERENCES policy("number"),
    CHECK (make IN ('TOYOTA', 'HONDA', 'ACURA', 'VOLKSWAGEN'))
);
/
CREATE TABLE accident(
    "id" VARCHAR(120) NOT NULL PRIMARY KEY
);
/
CREATE TABLE vehicles_in_accident(
    accident_id VARCHAR(120) NOT NULL,
    vehicle_vin VARCHAR(120) NOT NULL,
    CONSTRAINT veh_in_acc_PK PRIMARY KEY (accident_id, vehicle_vin),
    FOREIGN KEY (accident_id) REFERENCES accident("id"),
    FOREIGN KEY (vehicle_vin) REFERENCES vehicle(vin)
);