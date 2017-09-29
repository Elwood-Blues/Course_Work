// Main program file, main.cpp, for Homework Assignment 1
// Author: Doug Dunham 
// Sept. 13, 2008
// For CS 5721

// Student:  Peter Braband
#include <iostream>
#include <cmath>
#include <cstdlib>

using namespace std;

#include "Vector2D.h"
#include "Vector3D.h"
#include "FrameBuffer.h"

int main()
{
    for (int i = 0; i < 2; ++i) {
        if(i == 0){
            FrameBuffer hw4(500, 500);
//            hw4.setAll(120, 120, 120);
            hw4.zBuffRender(true);
            hw4.writePPM("hw4.5(ortho).ppm");
        }else{
            FrameBuffer hw4(500, 500);
//            hw4.setAll(120, 120, 120);
            hw4.zBuffRender(false);
            hw4.writePPM("hw4.5(proj).ppm");
        }
    }
    return 0;
}
