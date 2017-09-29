// Main program file, main.cpp, for Homework Assignment 1
// Author: Doug Dunham 
// Sept. 13, 2008
// For CS 5721

// Compile with:  g++ -Wall -o hwk1 main.cpp FrameBuffer.cpp -lm
// To run it use:  ./hwk1
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
//	// Vector2D
//
//	Vector2D a(1.0, 3.0), b(5.0, 3.2), c(0.0, 0.0);
//	c = a + b;
//	std::cout << "c = [" << c.getX() << ", " << c.getY() << "]" << std::endl << std::endl;
//
//	std::cout << "a dotProduct b = " << a.dotProduct(b) << std::endl;
//	//std::cout << "a crossProduct b = " << a.crossProduct(b) << std::endl << std::endl;
//
//	a.set(-4.0, 3.9);
//	b.set(14.2, 9.4);
//	std::cout << "a length = " << a.magnitude() << std::endl;
//	std::cout << "b length = " << b.magnitude() << std::endl;
//
//	double len = a.magnitude();
//	a.normalize();
////	std::cout << "a = [" << a[0] << ", " << a[1] << "]" << std::endl;
//	std::cout << "new a length = " << a.magnitude() << ", old length = " << len << std::endl;
//
//	a = /*len **/ a * len;
//	std::cout << "a = [" << a.getX() << ", " << a.getY() << "]" << std::endl;
//
//	float* z = a.vecfData();
//	std::cout << "z = [" << z[0] << ", " << z[1] << "]" << std::endl;
//	std::cout << "\r\n";
//
//	// Vector3D
//
//	Vector3D d(1.0, 3.0, 5.0), e(5.0, 3.2, 1.7), f(0.0, 0.0, 0.0);
//	f = d + e;
//	std::cout << "f = [" << f.getX() << ", " << f.getY() << ", " << f.getZ() << "]" << std::endl << std::endl;
//
//	std::cout << "d dotProduct e = " << d.dotProduct(e) << std::endl;
//	std::cout << "d crossProduct e = " << d.crossProduct(e) << std::endl;
//	std::cout << "e crossProduct d = " << e.crossProduct(d) << std::endl << std::endl;
//
//	d.set(-2.32, 3.9, 7.1);
//	e.set(14.2, 9.4, -3.6);
//	std::cout << "d length = " << d.magnitude() << std::endl;
//	std::cout << "e length = " << e.magnitude() << std::endl;
//
//	len = d.magnitude();
//	d.normalize();
//	std::cout << "d = [" << d.getX() << ", " << d.getY() << ", " << d.getZ() << "]" << std::endl;
//	std::cout << "new d length = " << d.magnitude() << ", old length = " << len << std::endl;
//
//	d = d * len;
//	std::cout << "d = [" << d.getX() << ", " << d.getY() << ", " << d.getZ() << "]" << std::endl;
//
//	z = d.vecfData();
//	std::cout << "z = [" << z[0] << ", " << z[1] << ", " << z[2] << "]" << std::endl;
//	std::cout << "\r\n";
//
//	// FrameBuffer
//    FrameBuffer f1(89,145), f2(480,48),f3(480,600), f4(10,10), f5(600,800);
//
//    f1.setAll(255,255,255);//White
//
//    f2.setAll(0,0,0);//Black
//
//    f3.setAll(255,0,0);//red
//
//    f4.setAll(0,255,0);//green
//
//
//    f5.setAll(0,0,0);//multi
//
//    f5.set(0,0,0,255,0);
//    f5.set(f5.getHeight()-1, f5.getWidth()-1,255,0,0);
//    f5.set(0, f5.getWidth()-1,255,255,0);
//
//    f1.writePPM("white.ppm");
//    f2.writePPM("black.ppm");
//    f3.writePPM("red.ppm");
//    f4.writePPM("green.ppm");
//    f5.writePPM("multi.ppm");

    /* HW2 Section */

    //First Image Section
    FrameBuffer f6(725, 408), f7(640, 480), f8(640, 480);
    int originX = (int)(f6.getWidth() / 2.0);
    int originY= (int)(f6.getHeight() / 2.0);
    Vector2D lineBegin(originY, originX), lineEnd;
//    Vector2D lineBegin(204, 364), lineEnd;
    Vector3D lineColor(255, 0.0, 0.0);//lines to be drawn red
//	double angle = 0.0;
	double radius = 200;
    f6.setAll(0, 0, 0);//set background to black
    for(int i = 0; i < 31; i++){
//        lineEnd.setX(originX + 100*cos(12*i*M_PI/180));
//        lineEnd.setY(originY + 100*sin(12*i*M_PI/180));
        lineEnd.setX(lineBegin.getX() + radius*cos(12*i*M_PI/180));
        lineEnd.setY(lineBegin.getY() + radius*sin(12*i*M_PI/180));
        f6.rasterizeline_implicit(lineBegin, lineEnd, lineColor);
        //angle = (i * 12) * (M_PI/180.0);
    }
	f6.writePPM("hw2FirstImage.ppm");
//    Vector2D temp1, temp2;
//    Vector3D tempCol(255, 0.0, 0.0);
//    temp1.set(75, 75);
//    temp2.set(75, 150);
//    FrameBuffer temp(250, 250);
//    temp.rasterizeline_implicit(temp1, temp2, tempCol);
//
//    temp.writePPM("temp.ppm");

    //Second Image Section
    originX = (int) (f7.getWidth() / 2.0);
    originY =(int) (f7.getHeight() / 2.0);
    lineBegin.set(originY, originX);


    f7.setAll(0, 0, 0); //set background to black
    Vector3D firstRandom, secondRandom;

    for (int j = 1; j < 31; ++j) {
        lineEnd.setX(lineBegin.getX() + radius*cos(12*j*M_PI/180));
        lineEnd.setY(lineBegin.getY() + radius*sin(12*j*M_PI/180));
        firstRandom.set( (short) (rand()%256),(short) (rand()%256),(short) (rand()%256));
        secondRandom.set((short) (rand()%256),(short) (rand()%256),(short) (rand()%256));

        f7.rasterizeline_parametric(lineBegin, lineEnd, firstRandom, secondRandom);
    }
    f7.writePPM("hw2SecondImage.ppm");

    //Third Image Section
    originX = (int) (f8.getWidth() / 2.0);
    originY =(int) (f8.getHeight() / 2.0);

    lineBegin.set(originY, originX);

    f8.setAll(0, 0, 0);//set background to black

    for (int k = 0; k < 50; ++k) {
        for (int i = 0; i <31 ; ++i) {
            lineEnd.setX(lineBegin.getX() + radius*cos(12*i*M_PI/180));
            lineEnd.setY(lineBegin.getY() + radius*sin(12*i*M_PI/180));
            f8.rasterizeline_implicit(lineBegin, lineEnd, lineColor);
            if(radius > 5) {
                radius = radius - 5;
            }
            else{
                radius = radius + 5;
            }
        }

    }
    f8.writePPM("hw2ThirdImage.ppm");
	return 0;
}
