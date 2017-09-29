//
// Created by Peter Braband on 4/30/16.
//

#include "Matrix4x4.h"
#include <cmath>

Matrix4x4::Matrix4x4() {
    data.resize(h);
    for (int i = 0; i < h ; ++i) {
        data.at(i).resize(w);
    }
    this->setToIdentity();
}

Matrix4x4::~Matrix4x4() {
    data.clear();
}

Matrix4x4::Matrix4x4(const double &m00, const double &m01, const double &m02, const double &m03, const double &m10,
                     const double &m11, const double &m12, const double &m13, const double &m20, const double &m21,
                     const double &m22, const double &m23, const double &m30, const double &m31, const double &m32,
                     const double &m33) {
    data.resize(h);
    for (int i = 0; i < h; ++i) {
        data.at(i).resize(w);
    }

    data.at(0).at(0) = m00;
    data.at(0).at(1) = m01;
    data.at(0).at(2) = m02;
    data.at(0).at(3) = m03;
    data.at(1).at(0) = m10;
    data.at(1).at(1) = m11;
    data.at(1).at(2) = m12;
    data.at(1).at(3) = m13;
    data.at(2).at(0) = m20;
    data.at(2).at(1) = m21;
    data.at(2).at(2) = m22;
    data.at(2).at(3) = m23;
    data.at(3).at(0) = m30;
    data.at(3).at(1) = m31;
    data.at(3).at(2) = m32;
    data.at(3).at(3) = m33;
}

double Matrix4x4::get(const int &x, const int &y) const {
    return this->data.at(x).at(y);
}

void Matrix4x4::set(const int &x, const int &y, const double &d) {
    if (y < h && x < w){
        this->data.at(x).at(y) = d;
    }
}

void Matrix4x4::set(const double &m00, const double &m01, const double &m02, const double &m03, const double &m10,
                    const double &m11, const double &m12, const double &m13, const double &m20, const double &m21,
                    const double &m22, const double &m23, const double &m30, const double &m31, const double &m32,
                    const double &m33) {
    data.at(0).at(0) = m00;
    data.at(0).at(1) = m01;
    data.at(0).at(2) = m02;
    data.at(0).at(3) = m03;
    data.at(1).at(0) = m10;
    data.at(1).at(1) = m11;
    data.at(1).at(2) = m12;
    data.at(1).at(3) = m13;
    data.at(2).at(0) = m20;
    data.at(2).at(1) = m21;
    data.at(2).at(2) = m22;
    data.at(2).at(3) = m23;
    data.at(3).at(0) = m30;
    data.at(3).at(1) = m31;
    data.at(3).at(2) = m32;
    data.at(3).at(3) = m33;
}

void Matrix4x4::setToIdentity(void) {
    for (int i = 0; i < h; ++i) {
        for (int j = 0; j < w; ++j) {
            if( (i == 0 && j == 0) || (i == 1 && j == 1) || (i == 2 && j == 2) || (i == 3 && j == 3) ) data.at(i).at(j) = 1.0;
            else data.at(i).at(j) = 0.0;
        }
    }
}

Vector4D Matrix4x4::operator*(const Vector4D &v) {
    Vector4D newVec;
    newVec.setX(this->data.at(0).at(0) * v.getX() + this->data.at(0).at(1) * v.getY() + this->data.at(0).at(2) * v.getZ() + this->data.at(0).at(3) * v.getW());
    newVec.setY(this->data.at(1).at(0) * v.getX() + this->data.at(1).at(1) * v.getY() + this->data.at(1).at(2) * v.getZ() + this->data.at(1).at(3) * v.getW());
    newVec.setZ(this->data.at(2).at(0) * v.getX() + this->data.at(2).at(1) * v.getY() + this->data.at(2).at(2) * v.getZ() + this->data.at(2).at(3) * v.getW());
    newVec.setW(this->data.at(3).at(0) * v.getX() + this->data.at(3).at(1) * v.getY() + this->data.at(3).at(2) * v.getZ() + this->data.at(3).at(3) * v.getW());
    return newVec;
}

Matrix4x4 Matrix4x4::operator*(const Matrix4x4 &m) {
    Matrix4x4 newMat;
    for (int row = 0; row < newMat.h; ++row) {//row
        for (int col = 0; col < newMat.w; ++col) {//column
            //assuming 3x3 Matrix multiplication
            for (int inner = 0; inner < newMat.w; ++inner) {//for 0-3
                newMat.set( row, col, newMat.get(row, col) + this->data.at(row).at(inner) * m.get(inner, col) );
            }
        }
    }
    return newMat;
}

void Matrix4x4::makeScale(Vector3D &v) {
    this->data.at(0).at(0) *= v.getX();
    this->data.at(1).at(1) *= v.getY();
    this->data.at(2).at(2) *= v.getZ();
}

void Matrix4x4::rotateZ(double theta_in_deg) {
    this->data.at(0).at(0) *= cos(theta_in_deg);
    this->data.at(0).at(1) *= -sin(theta_in_deg);
    this->data.at(1).at(0) *= sin(theta_in_deg);
    this->data.at(1).at(1) *= cos(theta_in_deg);
}

void Matrix4x4::makeTranslate(Vector3D &v) {
    this->data.at(0).at(3) = v.getX();
    this->data.at(1).at(3) = v.getY();
    this->data.at(2).at(3) = v.getZ();
}

void Matrix4x4::roatateY(double theta_in_deg) {
    this->data.at(0).at(0) *= cos(theta_in_deg);
    this->data.at(0).at(2) *= cos(theta_in_deg);
    this->data.at(2).at(0) *= -sin(theta_in_deg);
}

void Matrix4x4::rotateX(double theta_in_deg) {
    this->data.at(1).at(1) *= cos(theta_in_deg);
    this->data.at(1).at(2) *= -sin(theta_in_deg);
    this->data.at(2).at(1) *= sin(theta_in_deg);
    this->data.at(2).at(2) *= cos(theta_in_deg);
}

void Matrix4x4::makeOrtho(const double l, const double r, const double b, const double t, const double n, const double f) {
    this->data.at(0).at(0) = 2.0 / (r - l);
    this->data.at(0).at(3) = -( (r + l) / (r - l) );
    this->data.at(1).at(1) = 2.0 / (t - b);
    this->data.at(1).at(3) = -( (t + b) / (t - b) );
    this->data.at(2).at(2) = 2.0 / (n - f);
    this->data.at(2).at(3) = -( (n + f) / (n - f) );
}

void Matrix4x4::makeViewPort(const int n_x, const int n_y) {
    this->data.at(0).at(0) = (double) n_x / 2.0;
    this->data.at(0).at(3) = (double) (n_x -1) / 2.0;
    this->data.at(1).at(1) = (double) n_y /2.0;
    this->data.at(1).at(3) = (double) (n_y - 1) / 2.0;
}

void Matrix4x4::setToZero(void) {
    for (int i = 0; i < this->h; ++i) {
        for (int j = 0; j < this->w; ++j) {
            data.at(i).at(j) = 0.0;
        }
    }
}

Vector4D Matrix4x4::getXvector() const {
    return Vector4D(this->get(0,0), this->get(1, 0), this->get(2, 0), this->get(3, 0));
}

Vector4D Matrix4x4::getYvector() const {
    return Vector4D(this->get(0, 1), this->get(1, 1), this->get(2, 1), this->get(3, 1));
}

Vector4D Matrix4x4::getZvector() const {
    return Vector4D(this->get(0, 2), this->get(1, 2), this->get(2,2), this->get(3, 2));
}










