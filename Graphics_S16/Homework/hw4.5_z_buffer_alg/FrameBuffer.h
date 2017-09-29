/*
 * File:   FrameBuffer.h
 * Author: Peter Braband
 *
 * Created on January 27, 2016, 8:50 PM
 */

#ifndef FRAMEBUFFER_H
#define FRAMEBUFFER_H

#include "Vector2D.h"
#include "Vector3D.h"
#include "Vector4D.h"
#include <vector>


class FrameBuffer {
public:
    FrameBuffer();
    FrameBuffer(int height, int width);
    ~FrameBuffer(void){}

    int getWidth() const;
    int getHeight() const;

    void set(int x, int y, short int red, short int green, short int blue);
    void setAll(short int red, short int green, short int blue);
//    void erase(); //Optional
    void resize(int height, int width);

    void writePPM(const std::string file) const;

//    int* bufferdata();
    void rasterizeline_implicit(const Vector2D& p0, const Vector2D& p1, const Vector3D& c);
    void rasterizeline_parametric( const Vector2D& p0, const Vector2D& p1, const Vector3D& c0, const Vector3D& c1);
    int sign(double x);
    void rasterize_Triangle(const Vector2D& p0, const Vector2D& p1, const Vector2D& p2,
                                        const Vector3D& c0, const Vector3D& c1, const Vector3D& c2);
    void rasterize_Triangle(const Vector4D& p0, const Vector4D& p1, const Vector4D& p2,
                            const Vector3D& c0, const Vector3D& c1, const Vector3D& c2);
    void rayTrace();
    void zBuffRender(bool isOrtho);
    double f_0_1(int x, int y, const Vector2D& p0, const Vector2D& p1);
    double f_1_2(int x, int y, const Vector2D& p1, const Vector2D& p2);
    double f_2_0(int x, int y, const Vector2D& p2, const Vector2D& p0);
    double f_0_1(double x, double y, const Vector4D& p0, const Vector4D& p1);
    double f_1_2(double x, double y, const Vector4D& p1, const Vector4D& p2);
    double f_2_0(double x, double y, const Vector4D& p2, const Vector4D& p0);


private:
    const static short int MAX = 255;
    const static short int MIN = 0;
    const static short int DEPTH = 3;
    int h, w;

    bool isHit(Vector3D& c_vec, Vector3D& d_vec, Vector3D& e_vec, double &rad);
    bool computeT(Vector3D& c_vec, Vector3D& d_vec, Vector3D e_vec, double &rad, double t_0, double t_1, double& t);
    double calcDiscriminant(Vector3D& c_vec, Vector3D& d_vec, Vector3D& e_vec, double& rad);
//    short int* data;
    std::vector< std::vector<std::vector<short>> > data;


};

#endif /* FRAMEBUFFER_H */

