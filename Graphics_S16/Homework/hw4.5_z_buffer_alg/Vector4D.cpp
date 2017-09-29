//
// Created by Peter Braband on 4/30/16.
//

#include "Vector4D.h"

Vector4D::Vector4D(double x, double y, double z, double w) {
    data.resize(dataSize);
    data.at(0) = x;
    data.at(1) = y;
    data.at(2) = z;
    data.at(3) = w;
}

Vector4D::~Vector4D() {
    data.clear();
}

Vector4D::Vector4D() {
    data.resize(dataSize);
    data.at(0) = 0.0;
    data.at(1) = 0.0;
    data.at(2) = 0.0;
    data.at(3) = 1.0;
}

double Vector4D::getX() const {
    return this->data.at(0);
}

double Vector4D::getY() const {
    return this->data.at(1);
}

double Vector4D::getZ() const {
    return this->data.at(2);
}

double Vector4D::getW() const {
    return this->data.at(3);
}

void Vector4D::setX(const double &x) {
    this->data.at(0) = x;
}

void Vector4D::setY(const double &y) {
    this->data.at(1) = y;
}

void Vector4D::setZ(const double &z) {
    this->data.at(2) = z;
}

void Vector4D::setW(const double &w) {
    this->data.at(3) = w;
}

void Vector4D::set(const double &x, const double &y, const double &z, const double &w) {
    this->data.at(0) = x;
    this->data.at(1) = y;
    this->data.at(2) = z;
    this->data.at(3) = w;
}

Vector4D::Vector4D(Vector3D v3, double w) {
    this->data.at(0) = v3.getX();
    this->data.at(1) = v3.getY();
    this->data.at(2) = v3.getZ();
    this->data.at(3) = w;
}

void Vector4D::set(const double &x, const double &y, const double &z) {
    this->data.at(0) = x;
    this->data.at(1) = y;
    this->data.at(2) = z;
}

void Vector4D::makeHomogonize() {
    this->data.at(0) = this->data.at(0) / this->data.at(3);
    this->data.at(1) = this->data.at(1) / this->data.at(3);
    this->data.at(2) = this->data.at(2) / this->data.at(3);
}





























