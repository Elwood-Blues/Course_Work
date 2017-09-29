/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 * Peter Braband
 */

#include "Vector3D.h"
#include<cmath>
#include <stdexcept>
#include <iostream>

double Vector3D::getX()const {
    return this->v3Array[0];
}

double Vector3D::getY()const {
    return this->v3Array[1];
}

double Vector3D::getZ()const {
    return this->v3Array[2];
}
//setter for vector X value
void Vector3D::setX(double x) {
    this->v3Array[0] = x;
}
//setter for vector Y value
void Vector3D::setY(double y) {
    this->v3Array[1] = y;
}
void Vector3D::setZ(double d) {
    this->v3Array[2] = d;
}
//Default C-tor
Vector3D::Vector3D(void){
    v3Array[0]=0.0;
    v3Array[1]=0.0;
    v3Array[2]=0.0;
}

//Initialized C-tor
Vector3D::Vector3D(double x1, double y1, double z1){
    v3Array[0]=x1;
    v3Array[1]=y1;
    v3Array[2]=z1;
}

//Overloaded equality for Vector comparison
bool Vector3D::operator==(const Vector3D& L) {
    return L.v3Array[0] == this->v3Array[0] && L.v3Array[1] == this->v3Array[1] && L.v3Array[2] == this->v3Array[2];
}

bool Vector3D::operator!=(const Vector3D &L) {
    return L.v3Array[0] != this->v3Array[0] || L.v3Array[1] != this->v3Array[1] || L.v3Array[2] != this->v3Array[2];
}
//Destructor
Vector3D::~Vector3D(void) {}

float* Vector3D::vecfData(void) {
    static float newArray[sizeof(v3Array)];
    for(int i = 0; i < sizeof(v3Array); ++i){
        newArray[i] = (float)v3Array[i];
    }
    return newArray;
}
Vector3D Vector3D::operator+=(const Vector3D &d) {
    return Vector3D(this->v3Array[0] += d.v3Array[0], this->v3Array[1] += d.v3Array[1], this->v3Array[2] += d.v3Array[2]);
}
Vector3D Vector3D::operator-=(const Vector3D &d) {
    return Vector3D(this->v3Array[0] -= d.v3Array[0], this->v3Array[1] -= d.v3Array[1], this->v3Array[2] -= d.v3Array[2]);
}
Vector3D Vector3D::operator*=(const Vector3D &d) {
    return Vector3D(this->v3Array[0] *= d.v3Array[0], this->v3Array[1] *= d.v3Array[1], this->v3Array[2] *= d.v3Array[2]);
}
Vector3D Vector3D::operator/=(const Vector3D &d) {
    return Vector3D(this->v3Array[0] /= d.v3Array[0], this->v3Array[1] /= d.v3Array[1], this->v3Array[2] /= d.v3Array[2]);
}
Vector3D Vector3D::operator+(const Vector3D &d) {
    return Vector3D(this->v3Array[0] + d.v3Array[0], this->v3Array[1] + d.v3Array[1], this->v3Array[2] + d.v3Array[2]);
}
Vector3D Vector3D::operator-(const Vector3D &d) {
    return Vector3D(this->v3Array[0] - d.v3Array[0], this->v3Array[1] - d.v3Array[1], this->v3Array[2] - d.v3Array[2]);
}

void Vector3D::normalize(void) {
    double length = this->magnitude();
    if(length > 0){
        this->v3Array[0] /= length;
        this->v3Array[1] /= length;
        this->v3Array[2] /= length;
    }

}

double Vector3D::magnitude() {
    const double length = sqrt( this->v3Array[0] * this->v3Array[0] + this->v3Array[1] * this->v3Array[1] + this->v3Array[2] * this->v3Array[2] );
    return length;
}

double Vector3D::dotProduct(const Vector3D &a) {
    return ( a.v3Array[0] * this->v3Array[0] + a.v3Array[1] * this->v3Array[1]
             + a.v3Array[2] * this->v3Array[2] );
}

Vector3D Vector3D::operator*(double scalar) {
    return Vector3D(this->v3Array[0] *= scalar,this->v3Array[1] *= scalar,this->v3Array[2] *= scalar);
}

Vector3D Vector3D::operator/(double scalar) {
    return Vector3D(this->v3Array[0] /= scalar,this->v3Array[1] /= scalar,this->v3Array[2] /= scalar);
}

Vector3D Vector3D::operator/(const Vector3D &d) {
    return Vector3D(this->v3Array[0] / d.v3Array[0], this->v3Array[1] / d.v3Array[1], this->v3Array[2] / d.v3Array[2]);
}

Vector3D Vector3D::operator*(const Vector3D &d) {
    return Vector3D(this->v3Array[0] * d.v3Array[0], this->v3Array[1] * d.v3Array[1], this->v3Array[2] * d.v3Array[2]);
}


Vector3D Vector3D::crossProduct(const Vector3D &a) {
    return Vector3D( (a.v3Array[1] * this->v3Array[2] - a.v3Array[2] * this->v3Array[1]),
                     (a.v3Array[2] * this->v3Array[0] - a.v3Array[0] * this->v3Array[2]),
                     (a.v3Array[0] * this->v3Array[1] - a.v3Array[1] * this->v3Array[0]));
}

void Vector3D::display() {
    std::cout << "X value: " << this->getX() << std::endl;
    std::cout << "Y value: " << this->getY() << std::endl;
    std::cout << "Z value: " << this->getZ() << std::endl;
}

void Vector3D::set(const double d, const double d1, const double d2) {
    this->setX(d);
    this->setY(d1);
    this->setZ(d2);
}
std::ostream &operator<<(std::ostream &out, const Vector3D &v) {
    out << "[" << v.getX() << ", " << v.getY() << ", " << v.getZ() << "]";
    return  out;
}
