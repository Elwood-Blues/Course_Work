/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  grantpage
 * Created: Mar 23, 2016
 */
/**
*   MODIFIED: 4/6/16
*   BY: Peter
*   REASON: changed to 'person', because 'user' is a reserverd keyword in SQL
*           also, broke up the 'name' into 'first_name' and 'last_name'
*/
CREATE TABLE "person" (
    "id" INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "first_name" VARCHAR(64) NOT NULL,
    "last_name" VARCHAR(64) NOT NULL,
    "password" VARCHAR(20) NOT NULL,
    "phone_number" VARCHAR(10) NOT NULL,
    "email" VARCHAR(64) NOT NULL
);