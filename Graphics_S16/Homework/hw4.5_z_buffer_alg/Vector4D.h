//
// Created by Peter Braband on 4/30/16.
//

#ifndef HW3_5_VECTOR4D_H
#define HW3_5_VECTOR4D_H


#include <vector>
#include "Vector3D.h"

class Vector4D {
public:
    Vector4D();
    ~Vector4D();
    Vector4D(double x, double y, double z, double w);
    Vector4D(Vector3D, double w);
    double getX() const ;
    double getY() const ;
    double getZ() const ;
    double getW() const ;
    void setX(const double& x);
    void setY(const double& y);
    void setZ(const double& z);
    void setW(const double& w);
    void set(const double& x, const double& y, const double& z, const double& w);
    void set(const double& x, const double& y, const double& z);
    void makeHomogonize();

private:
    std::vector<double> data;
    const static int dataSize = 4;

};


#endif //HW3_5_VECTOR4D_H
