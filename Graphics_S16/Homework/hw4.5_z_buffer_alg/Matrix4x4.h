//
// Created by Peter Braband on 4/30/16.
//

#ifndef HW3_5_MATRIX4X4_H
#define HW3_5_MATRIX4X4_H


#include <vector>
#include "Vector3D.h"
#include "Vector4D.h"

class Matrix4x4 {
public:
    Matrix4x4();
    ~Matrix4x4();
    Matrix4x4(const double& m00, const double& m01, const double& m02, const double& m03,
                const double& m10, const double& m11,  const double& m12, const double& m13,
              const double& m20, const double& m21, const double& m22, const double& m23,
              const double& m30, const double& m31, const double& m32, const double& m33);
    double get(const int& x, const int& y) const ;
    void set(const int& x, const int& y, const double& d);
    void set(const double &m00, const double &m01, const double &m02, const double &m03, const double &m10,
             const double &m11, const double &m12, const double &m13, const double &m20, const double &m21,
             const double &m22, const double &m23, const double &m30, const double &m31, const double &m32,
             const double &m33);
    void makeTranslate( Vector3D& v);
    void makeScale( Vector3D& v);
    void makeOrtho(const double l, const double r, const double b, const double t, const double n, const double f);
    void makeViewPort(const int n_x, const int n_y);
    void makePerspective(/*TODO implement arguements*/);
    Vector4D operator*(const Vector4D& v);
    Matrix4x4 operator*(const Matrix4x4& m);

    //useful not reqd
    void setToIdentity(void);
    void setToZero(void);
    void rotateZ( double theta_in_deg );
    void roatateY( double theta_in_deg );
    void rotateX( double theta_in_deg );
    Matrix4x4 transpose(void);
    Vector4D getXvector()const;
    Vector4D getYvector() const;
    Vector4D getZvector() const;


private:
    std::vector<std::vector<double>> data;
//    double data[h][w];
    const static int h = 4;
    const static int w = 4;

};


#endif //HW3_5_MATRIX4X4_H
