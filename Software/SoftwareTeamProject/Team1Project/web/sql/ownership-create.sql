/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  elwood
 * Created: Apr 7, 2016
 */
CREATE TABLE "ownership" (
id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
owner VARCHAR(64)NOT NULL,
book VARCHAR(64) NOT NULL,
FOREIGN KEY (owner) REFERENCES "person" ("id"),
FOREIGN KEY (book) REFERENCES "book" ("id")
);
