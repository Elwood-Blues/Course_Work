/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   Vector2D.cpp
 * Author: Peter Braband
 *
 * Created on January 27, 2016, 8:43 PM
 */

#include<cmath>
#include <stdexcept>

#include "Vector2D.h"

double Vector2D::getX() const {
    return this->v[0];
}

double Vector2D::getY()const {
    return this->v[1];
}

//setter for vector X value
void Vector2D::setX(double x) {
    this->v[0] = x;
}
//setter for vector Y value
void Vector2D::setY(double y) {
    this->v[1] = y;
}

//Default C-tor
Vector2D::Vector2D(){
    v[0]=0;
    v[1]=0;
}

//Initialized C-tor
Vector2D::Vector2D(double x1, double y1){
    v[0]=x1;
    v[1]=y1;
}

//Overloaded equality for Vector comparison
bool Vector2D::operator==(const Vector2D& L) {
    return L.v[0] == this->v[0] && L.v[1] == this->v[1];
}

//Destructor
Vector2D::~Vector2D(void) {}

float* Vector2D::vecfData(void) {
    static float newArray[sizeof(v)];
    for(int i = 0; i < sizeof(v); ++i){
        newArray[i] = (float)v[i];
    }
    return newArray;
}
Vector2D Vector2D::operator+=(const Vector2D &d) {
    return Vector2D(this->v[0] += d.v[0], this->v[1] += d.v[1]);
}
Vector2D Vector2D::operator-=(const Vector2D &d) {
    return Vector2D(this->v[0] -= d.v[0], this->v[1] -= d.v[1]);
}
Vector2D Vector2D::operator*=(const Vector2D &d) {
    return Vector2D(this->v[0] *= d.v[0], this->v[1] *= d.v[1]);
}
Vector2D Vector2D::operator/=(const Vector2D &d) {
    return Vector2D(this->v[0] /= d.v[0], this->v[1] /= d.v[1]);
}
Vector2D Vector2D::operator+(const Vector2D &a) {
    return Vector2D(this->v[0] + a.v[0], this->v[1] + a.v[1]);
}
Vector2D Vector2D::operator-(const Vector2D &d) {
    return Vector2D(this->v[0] - d.v[0], this->v[1] - d.v[1]);
}
Vector2D Vector2D::normalize() {
    double length = this->magnitude();
    if(length == 0.0){
        return Vector2D(0.0, 0.0);//**Zero vector**//
        // throw std::overflow_error("Division by Zero Error");
    }
    else {
        return Vector2D(this->v[0] /= length, this->v[1] /= length);
    }
}

double Vector2D::magnitude() {
    return sqrt((pow(v[0], 2.0)) + pow(v[1], 2.0));
}

double Vector2D::dotProduct(const Vector2D &a) {
    return ( (a.v[0] * this->v[0]) + (a.v[1] * this->v[1]) );
}


Vector2D Vector2D::operator*(double scalar) {
    return Vector2D(this->v[0]* scalar, this->v[1]* scalar);
}

Vector2D Vector2D::operator/(double scalar) {
    return Vector2D(this->v[0]/ scalar, this->v[1]/ scalar);
}

Vector2D Vector2D::operator/(const Vector2D &d) {
    return Vector2D(this->v[0] / d.v[0], this->v[1] / d.v[1]);
}

Vector2D Vector2D::operator*(const Vector2D &d) {
    return Vector2D(this->v[0] * d.v[0], this->v[1] * d.v[1]);
}

void Vector2D::display(void) {
    std::cout << "X value: " << this->getX() << std::endl;
    std::cout << "Y value: " << this->getY() << std::endl;
}

bool Vector2D::operator!=(const Vector2D &d) {
    return d.v[0] != this->v[0] || d.v[1] != this->v[1];;
}

void Vector2D::set(const double d, const double d1) {
    this->setX(d);
    this->setY(d1);
}

std::ostream &operator<<(std::ostream &out, const Vector2D &v) {
    out << "[" << v.getX() << ", " << v.getY() << "]";
    return  out;
}

