/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   Vector3D.h
 * Author: Peter Braband
 *
 * Created on January 27, 2016, 8:47 PM
 */

#ifndef VECTOR3D_H
#define VECTOR3D_H
#include<iostream>
class Vector3D {
private:
    double v3Array[3];


public:
    float* vecfData(void);

    //default c-tor
    Vector3D();
    //Parameterized C-tor
    Vector3D(double, double, double);

    //Destructor
    ~Vector3D(void);

    //getter for vector X value
    double getX()const;

    //getter for vector Y value
    double getY()const;

    //getter for Vector Z value
    double getZ()const;

    //setter for vector X value
    void setX(const double);

    //single setter function for all values
    void set(const double, const double, const double);

    //setter for vector Y value
    void setY(const double);
    //setter for vector Z value
    void setZ(const double);

    //Overloaded operators:
    Vector3D operator+(const Vector3D&);
    Vector3D operator-(const Vector3D&);
    Vector3D operator *(double scalar);
    Vector3D operator /(double scalar);


    Vector3D operator+=(const Vector3D&);

    Vector3D operator-=(const Vector3D&);
    //Scalar operations
    Vector3D operator*=(const Vector3D&);
    Vector3D operator*(const Vector3D&);

    Vector3D operator/=(const Vector3D&);
    Vector3D operator/(const Vector3D&);

    void normalize();

    double magnitude();

    double dotProduct(const Vector3D&);

    Vector3D crossProduct(const Vector3D&);

    bool operator==(const Vector3D &L);//checks for equality between two vectors

    bool operator!=(const Vector3D &L); //checks for inequality between two vectors

    void display();
    
    friend std::ostream& operator<<(std::ostream &out, const Vector3D& v);
};

#endif /* VECTOR3D_H */

