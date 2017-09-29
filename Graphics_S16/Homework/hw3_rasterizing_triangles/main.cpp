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
//
//    /* HW2 Section */
//
//    //First Image Section
//    FrameBuffer f6(725, 408), f7(640, 480), f8(640, 480);
//    int originX = (int)(f6.getWidth() / 2.0);
//    int originY= (int)(f6.getHeight() / 2.0);
//    Vector2D lineBegin(originY, originX), lineEnd;
//    Vector3D lineColor(255, 0.0, 0.0);//lines to be drawn red
	double radius = 200;
//    f6.setAll(0, 0, 0);//set background to black
//    for(int i = 0; i < 31; i++){
//        lineEnd.setX(lineBegin.getX() + radius*cos(12*i*M_PI/180));
//        lineEnd.setY(lineBegin.getY() + radius*sin(12*i*M_PI/180));
//        f6.rasterizeline_implicit(lineBegin, lineEnd, lineColor);
//    }
//	f6.writePPM("hw2FirstImage.ppm");
//
//    //Second Image Section
//    originX = (int) (f7.getWidth() / 2.0);
//    originY =(int) (f7.getHeight() / 2.0);
//    lineBegin.set(originY, originX);
//
//
//    f7.setAll(0, 0, 0); //set background to black
//    Vector3D firstRandom, secondRandom;
//
//    for (int j = 1; j < 31; ++j) {
//        lineEnd.setX(lineBegin.getX() + radius*cos(12*j*M_PI/180));
//        lineEnd.setY(lineBegin.getY() + radius*sin(12*j*M_PI/180));
//        firstRandom.set( (short) (rand()%256),(short) (rand()%256),(short) (rand()%256));
//        secondRandom.set((short) (rand()%256),(short) (rand()%256),(short) (rand()%256));
//
//        f7.rasterizeline_parametric(lineBegin, lineEnd, firstRandom, secondRandom);
//    }
//    f7.writePPM("hw2SecondImage.ppm");
//
//    //Third Image Section
//    originX = (int) (f8.getWidth() / 2.0);
//    originY =(int) (f8.getHeight() / 2.0);
//
//    lineBegin.set(originY, originX);
//
//    f8.setAll(0, 0, 0);//set background to black
//
//    for (int k = 0; k < 50; ++k) {
//        for (int i = 0; i <31 ; ++i) {
//            lineEnd.setX(lineBegin.getX() + radius*cos(12*i*M_PI/180));
//            lineEnd.setY(lineBegin.getY() + radius*sin(12*i*M_PI/180));
//            f8.rasterizeline_implicit(lineBegin, lineEnd, lineColor);
//            if(radius > 5) {
//                radius = radius - 5;
//            }
//            else{
//                radius = radius + 5;
//            }
//        }
//
//    }
//    f8.writePPM("hw2ThirdImage.ppm");



    /*  HW3 Section  */

    // First image section
    Vector2D v1, v2, v3, v4;
    Vector3D c1, c2, c3, white;
    white.set(255, 255, 255);
    FrameBuffer firstImage(800, 800), secondImage(800, 800), thirdImage(800,800), fourthImage(800,800);
    v1.set(0,0);
    v2.set(firstImage.getWidth() -1, 0);
    v3.set(firstImage.getWidth() -1, firstImage.getHeight() -1);
    c1.set(255, 0, 0);
    firstImage.rasterize_Triangle(v1, v2, v3, c1, c1, c1);
    v1.set(firstImage.getWidth() -1, firstImage.getHeight() -1);
    v2.set(0, firstImage.getHeight() -1);
    v3.set(0,0);
    c2.set(0, 255, 0);
    c3.set(0,0, 255);
    firstImage.rasterize_Triangle(v1, v2, v3, c1, c2, c3);
    firstImage.writePPM("hw3FirstImage.ppm");


    // Second image section
    v1.set(200,0);
    v2.set(200,200);
    v3.set(0,200);
    c1.set(255,255,0);
    c2.set(0, 255, 255);
    c3.set(255, 0, 255);
    secondImage.rasterize_Triangle(v1, v2, v3, c1, c2, c3);

    v2.set(secondImage.getWidth() -1, 200);
    v3.set(200,200);
    c1.set(255, 0, 0);
    c2.set(255, 0, 0);
    c3.set(0, 0, 255);
    secondImage.rasterize_Triangle(v1, v2, v3, c1, c2, c3);

    v1.set(200, secondImage.getHeight() -1);
    v2.set(200, 200);
    v3.set(secondImage.getWidth() -1, 200);
    c1.set(0, 255, 0);
    c2.set(0, 0, 0);
    c3.set(0, 0, 255);
    secondImage.rasterize_Triangle(v1, v2, v3, c1, c2, c3);

    v1.set(200, 200);
    v2.set(200, secondImage.getHeight() -1);
    v3.set(0, 200);
    c1.set(0, 255, 0);
    c2.set(255, 255, 0);
    c3.set(255, 255, 255);
    secondImage.rasterize_Triangle(v1, v2, v3, c1, c2, c3);

    secondImage.writePPM("hw3SecondImage.ppm");

    // Third image section
    /*  Hexagon Start  */
    int i = 0;
    v1.set(thirdImage.getHeight()/2, thirdImage.getWidth()/2);
    radius = v1.magnitude() - 400;
    v2.set((int) v1.getX() + radius*cos(45*i*M_PI/180),(int) v1.getY() + radius*sin(45*i*M_PI/180));
    i++;
    v3.set((int) v1.getX() + radius*cos(45*i*M_PI/180),(int) v1.getY() + radius*sin(45*i*M_PI/180));

    c1.set(255,255, 0);
    c2.set(255, 0, 255);
    c3.set(255,0,255);
    thirdImage.rasterize_Triangle(v1, v2, v3, c1, c2, c3); // First triangle section
    thirdImage.rasterizeline_implicit(v1, v2, white);
    thirdImage.rasterizeline_implicit(v2, v3, white);
    thirdImage.rasterizeline_implicit(v1, v3, white);

    v2.set((int) v1.getX() + radius*cos(45*i*M_PI/180),(int) v1.getY() + radius*sin(45*i*M_PI/180));
    i++;
    v3.set((int) v1.getX() + radius*cos(45*i*M_PI/180),(int) v1.getY() + radius*sin(45*i*M_PI/180));
    c1.set(0, 255, 255);
    c2.set(0, 255, 255);
    c3.set(0, 0, 0);
    thirdImage.rasterize_Triangle(v1, v2, v3, c1, c2, c3); // Second triangle section
    thirdImage.rasterizeline_implicit(v1, v2, white);
    thirdImage.rasterizeline_implicit(v2, v3, white);
    thirdImage.rasterizeline_implicit(v3, v1, white);

    v2.set((int) v1.getX() + radius*cos(45*i*M_PI/180),(int) v1.getY() + radius*sin(45*i*M_PI/180));
    i++;
    v3.set((int) v1.getX() + radius*cos(45*i*M_PI/180),(int) v1.getY() + radius*sin(45*i*M_PI/180));
    c1.set(100, 200, 50);
    c2.set(0, 0, 0);
    c3.set(100, 200, 50);
    thirdImage.rasterize_Triangle(v1, v2, v3, c1, c2, c3);  //Third Triangle
    thirdImage.rasterizeline_implicit(v1, v2, white);
    thirdImage.rasterizeline_implicit(v2, v3, white);
    thirdImage.rasterizeline_implicit(v3, v1, white);

    v2.set((int) v1.getX() + radius*cos(45*i*M_PI/180),(int) v1.getY() + radius*sin(45*i*M_PI/180));
    i++;
    v3.set((int) v1.getX() + radius*cos(45*i*M_PI/180),(int) v1.getY() + radius*sin(45*i*M_PI/180));
    c1.set(255, 255, 0);
    c2.set(255, 255, 0);
    c3.set(255, 0, 0);
    thirdImage.rasterize_Triangle(v1, v2, v3, c1, c2, c3);  // Fourth Triangle
    thirdImage.rasterizeline_implicit(v1, v2, white);
    thirdImage.rasterizeline_implicit(v2, v3, white);
    thirdImage.rasterizeline_implicit(v3, v1, white);

    v2.set((int) v1.getX() + radius*cos(45*i*M_PI/180),(int) v1.getY() + radius*sin(45*i*M_PI/180));
    i++;
    v3.set((int) v1.getX() + radius*cos(45*i*M_PI/180),(int) v1.getY() + radius*sin(45*i*M_PI/180));
    c1.set(0, 255, 0);
    c2.set(0, 0, 0);
    c3.set(0, 255, 0);
    thirdImage.rasterize_Triangle(v1, v2, v3, c1, c2, c3);  // Fifth triangle
    thirdImage.rasterizeline_implicit(v1, v2, white);
    thirdImage.rasterizeline_implicit(v2, v3, white);
    thirdImage.rasterizeline_implicit(v3, v1, white);

    v2.set((int) v1.getX() + radius*cos(45*i*M_PI/180),(int) v1.getY() + radius*sin(45*i*M_PI/180));
    i++;
    v3.set((int) v1.getX() + radius*cos(45*i*M_PI/180),(int) v1.getY() + radius*sin(45*i*M_PI/180));
    c1.set(0, 0, 0);
    c2.set(255, 0, 0);
    c3.set(255, 0, 0);
    thirdImage.rasterize_Triangle(v1, v2, v3, c1, c2, c3); // sixth triangle
    thirdImage.rasterizeline_implicit(v1, v2, white);
    thirdImage.rasterizeline_implicit(v2, v3, white);
    thirdImage.rasterizeline_implicit(v3, v1, white);

    v2.set((int) v1.getX() + radius*cos(45*i*M_PI/180),(int) v1.getY() + radius*sin(45*i*M_PI/180));
    i++;
    v3.set((int) v1.getX() + radius*cos(45*i*M_PI/180),(int) v1.getY() + radius*sin(45*i*M_PI/180));
    c1.set(0, 0, 0);
    c2.set(0, 255, 0);
    c3.set(0, 255, 0);
    thirdImage.rasterize_Triangle(v1, v2, v3, c1, c2, c3); // seventh triangle
    thirdImage.rasterizeline_implicit(v1, v2, white);
    thirdImage.rasterizeline_implicit(v2, v3, white);
    thirdImage.rasterizeline_implicit(v3, v1, white);

    v2.set((int) v1.getX() + radius*cos(45*i*M_PI/180),(int) v1.getY() + radius*sin(45*i*M_PI/180));
    i++;
    v3.set((int) v1.getX() + radius*cos(45*i*M_PI/180),(int) v1.getY() + radius*sin(45*i*M_PI/180));
    c1.set(0, 0, 0);
    c2.set(255, 0, 255);
    c3.set(255, 0, 255);
    thirdImage.rasterize_Triangle(v1, v2, v3, c1, c2, c3); // 8th triangle
    thirdImage.rasterizeline_implicit(v1, v2, white);
    thirdImage.rasterizeline_implicit(v2, v3, white);
    thirdImage.rasterizeline_implicit(v3, v1, white);
    /* End Hexagon Section  */


    /* start diamond  */
    v1.set(thirdImage.getHeight()*.25, thirdImage.getWidth()*.25);
    v2.set(v1.getX() + 50, v1.getY());
    v3.set(v1.getX() + 25, v1.getY() + 50);
    c1.set(100,100,100);
    c2.set(200,200,200);
    c3.set(0, 0, 0);
    thirdImage.rasterize_Triangle(v1, v2, v3, c1, c2, c3);
    thirdImage.rasterizeline_implicit(v1, v2, white);
    thirdImage.rasterizeline_implicit(v2, v3, white);
    thirdImage.rasterizeline_implicit(v3, v1, white);

    v3.set(v1.getX() + 25, v1.getY() - 50);
    c1.set(255, 0, 0);
    c2.set(0, 255, 0);
    c3.set(0, 0, 255);
    thirdImage.rasterize_Triangle(v1, v2, v3, c1, c2, c3);
    thirdImage.rasterizeline_implicit(v1, v2, white);
    thirdImage.rasterizeline_implicit(v2, v3, white);
    thirdImage.rasterizeline_implicit(v3, v1, white);
    /* End Diamond */

    /* Start Square */
    v1.set(thirdImage.getHeight()*.75, thirdImage.getWidth()*.75);
    v2.set(v1.getY() + 100, v1.getX());
    v3.set(v1.getY(), v1.getX() + 50);
    c1.set(100, 255, 0);
    c2.set(0, 100, 255);
    c3.set(255, 0, 100);
    thirdImage.rasterize_Triangle(v1, v2, v3, c1, c2, c3);
    thirdImage.rasterizeline_implicit(v1, v2, white);
    thirdImage.rasterizeline_implicit(v2, v3, white);
    thirdImage.rasterizeline_implicit(v3, v1, white);

    v1.set(v2.getX(), v3.getY());
    c2.set(75, 175, 0);
    c3.set(0, 0, 175);
    c1.set(255, 0, 75);
    thirdImage.rasterize_Triangle(v1, v2, v3, c1, c2, c3);
    thirdImage.rasterizeline_implicit(v1, v2, white);
    thirdImage.rasterizeline_implicit(v2, v3, white);
    thirdImage.rasterizeline_implicit(v3, v1, white);
    /* End Square */

    thirdImage.writePPM("hw3ThirdImage.ppm");


    // Fourth Image Section


    i = 0;
    v1.set(fourthImage.getHeight()/2, fourthImage.getWidth()/2);
    radius = v1.magnitude() - 400;
    v2.set((int) v1.getX() + radius*cos(60*i*M_PI/180),(int) v1.getY() + radius*sin(60*i*M_PI/180));
    i++;
    v3.set((int) v1.getX() + radius*cos(60*i*M_PI/180),(int) v1.getY() + radius*sin(60*i*M_PI/180));

    c1.set(255,255, 0);
    c2.set(255, 0, 255);
    c3.set(255,0,255);
    fourthImage.rasterize_Triangle(v1, v2, v3, c1, c2, c3); // First triangle section
    fourthImage.rasterizeline_implicit(v1, v2, white);
    fourthImage.rasterizeline_implicit(v2, v3, white);
    fourthImage.rasterizeline_implicit(v1, v3, white);

    v2.set((int) v1.getX() + radius*cos(60*i*M_PI/180),(int) v1.getY() + radius*sin(60*i*M_PI/180));
    i++;
    v3.set((int) v1.getX() + radius*cos(60*i*M_PI/180),(int) v1.getY() + radius*sin(60*i*M_PI/180));
    c1.set(0, 255, 255);
    c2.set(0, 255, 255);
    c3.set(0, 0, 0);
    fourthImage.rasterize_Triangle(v1, v2, v3, c1, c2, c3); // Second triangle section
    fourthImage.rasterizeline_implicit(v1, v2, white);
    fourthImage.rasterizeline_implicit(v2, v3, white);
    fourthImage.rasterizeline_implicit(v3, v1, white);

    v2.set((int) v1.getX() + radius*cos(60*i*M_PI/180),(int) v1.getY() + radius*sin(60*i*M_PI/180));
    i++;
    v3.set((int) v1.getX() + radius*cos(60*i*M_PI/180),(int) v1.getY() + radius*sin(60*i*M_PI/180));
    c1.set(100, 200, 50);
    c2.set(0, 0, 0);
    c3.set(100, 200, 50);
    fourthImage.rasterize_Triangle(v1, v2, v3, c1, c2, c3);  //Third Triangle
    fourthImage.rasterizeline_implicit(v1, v2, white);
    fourthImage.rasterizeline_implicit(v2, v3, white);
    fourthImage.rasterizeline_implicit(v3, v1, white);

    v2.set((int) v1.getX() + radius*cos(60*i*M_PI/180),(int) v1.getY() + radius*sin(60*i*M_PI/180));
    i++;
    v3.set((int) v1.getX() + radius*cos(60*i*M_PI/180),(int) v1.getY() + radius*sin(60*i*M_PI/180));
    c1.set(100, 200, 50);
    c2.set(0, 0, 0);
    c3.set(100, 200, 50);
    fourthImage.rasterize_Triangle(v1, v2, v3, c1, c2, c3);  // Fourth Triangle
    fourthImage.rasterizeline_implicit(v1, v2, white);
    fourthImage.rasterizeline_implicit(v2, v3, white);
    fourthImage.rasterizeline_implicit(v3, v1, white);

    v2.set((int) v1.getX() + radius*cos(60*i*M_PI/180),(int) v1.getY() + radius*sin(60*i*M_PI/180));
    i++;
    v3.set((int) v1.getX() + radius*cos(60*i*M_PI/180),(int) v1.getY() + radius*sin(60*i*M_PI/180));
    c1.set(0, 255, 0);
    c2.set(0, 0, 0);
    c3.set(0, 255, 0);
    fourthImage.rasterize_Triangle(v1, v2, v3, c1, c2, c3);  // Fifth triangle
    fourthImage.rasterizeline_implicit(v1, v2, white);
    fourthImage.rasterizeline_implicit(v2, v3, white);
    fourthImage.rasterizeline_implicit(v3, v1, white);

    v2.set((int) v1.getX() + radius*cos(60*i*M_PI/180),(int) v1.getY() + radius*sin(60*i*M_PI/180));
    i++;
    v3.set((int) v1.getX() + radius*cos(60*i*M_PI/180),(int) v1.getY() + radius*sin(60*i*M_PI/180));
    c1.set(255,255, 0);
    c2.set(255, 0, 255);
    c3.set(255,0,255);
    fourthImage.rasterize_Triangle(v1, v2, v3, c1, c2, c3); // sixth triangle
    fourthImage.rasterizeline_implicit(v1, v2, white);
    fourthImage.rasterizeline_implicit(v2, v3, white);
    fourthImage.rasterizeline_implicit(v3, v1, white);
    /* End Hexagon Section  */

    fourthImage.writePPM("hw3fourthImage.ppm");

    return 0;
}
