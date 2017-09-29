/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   Vector2D.h
 * Author: Peter Braband
 *
 * Created on January 27, 2016, 8:43 PM
 */

#ifndef VECTOR2D_H
#define VECTOR2D_H
#include<iostream>

class Vector2D {
private:
    double v[2];


public:
    float* vecfData(void);

    //default c-tor
    Vector2D();
    //Parameterized C-tor
    Vector2D(double, double);

    //Destructor
    ~Vector2D(void);

    //getter for vector X value
    double getX()const;

    //getter for vector Y value
    double getY()const;

    //setter for both values
    void set(const double, const double);

    //setter for vector X value
    void setX(double);

    //setter for vector Y value
    void setY(double);

    //Overloaded operators:
    Vector2D operator+(const Vector2D&);
    Vector2D operator-(const Vector2D&);
    Vector2D operator *(double scalar);
    Vector2D operator /(double scalar);

    //checks for equality between two vectors
    bool operator!=(const Vector2D&);
    bool operator==(const Vector2D&);

    Vector2D operator+=(const Vector2D&);

    Vector2D operator-=(const Vector2D&);
    //Scalar operations
    Vector2D operator*=(const Vector2D&);
    Vector2D operator*(const Vector2D&);

    Vector2D operator/=(const Vector2D&);
    Vector2D operator/(const Vector2D&);

    Vector2D normalize();

    double magnitude();

    double dotProduct(const Vector2D&);

    //double crossProduct(const Vector2D&); //Only need for Vector3D
    void display(void);

    friend std::ostream& operator<<(std::ostream &out, const Vector2D& v);
};


#endif /* VECTOR2D_H */

