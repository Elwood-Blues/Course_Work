/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
//#include <string>

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

    int* bufferdata();

private:
    const static short int MAX = 255;
    const static short int MIN = 0;
    int h, w;

    short int* data;

};

#endif /* FRAMEBUFFER_H */

