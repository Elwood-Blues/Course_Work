// Main program file, main.cpp, for Homework Assignment 1
// Author: Doug Dunham 
// Sept. 13, 2008
// For CS 5721

// Compile with:  g++ -Wall -o hwk1 main.cpp FrameBuffer.cpp -lm
// To run it use:  ./hwk1
// Student:  Peter Braband
#include <iostream>

using namespace std;

#include "Vector2D.h"
#include "Vector3D.h"
#include "FrameBuffer.h"

int main()
{
	// Vector2D

	Vector2D a(1.0, 3.0), b(5.0, 3.2), c(0.0, 0.0);
	c = a + b;
	std::cout << "c = [" << c.getX() << ", " << c.getY() << "]" << std::endl << std::endl;
	
	std::cout << "a dotProduct b = " << a.dotProduct(b) << std::endl;
	//std::cout << "a crossProduct b = " << a.crossProduct(b) << std::endl << std::endl;

	a.set(-4.0, 3.9);
	b.set(14.2, 9.4);
	std::cout << "a length = " << a.magnitude() << std::endl;
	std::cout << "b length = " << b.magnitude() << std::endl;

	double len = a.magnitude();
	a.normalize();
//	std::cout << "a = [" << a[0] << ", " << a[1] << "]" << std::endl;
	std::cout << "new a length = " << a.magnitude() << ", old length = " << len << std::endl;

	a = /*len **/ a * len;
	std::cout << "a = [" << a.getX() << ", " << a.getY() << "]" << std::endl;

	float* z = a.vecfData();
	std::cout << "z = [" << z[0] << ", " << z[1] << "]" << std::endl;
	std::cout << "\r\n";

	// Vector3D

	Vector3D d(1.0, 3.0, 5.0), e(5.0, 3.2, 1.7), f(0.0, 0.0, 0.0);
	f = d + e;
	std::cout << "f = [" << f.getX() << ", " << f.getY() << ", " << f.getZ() << "]" << std::endl << std::endl;

	std::cout << "d dotProduct e = " << d.dotProduct(e) << std::endl;
	std::cout << "d crossProduct e = " << d.crossProduct(e) << std::endl;
	std::cout << "e crossProduct d = " << e.crossProduct(d) << std::endl << std::endl;

	d.set(-2.32, 3.9, 7.1);
	e.set(14.2, 9.4, -3.6);
	std::cout << "d length = " << d.magnitude() << std::endl;
	std::cout << "e length = " << e.magnitude() << std::endl;

	len = d.magnitude();
	d.normalize();
	std::cout << "d = [" << d.getX() << ", " << d.getY() << ", " << d.getZ() << "]" << std::endl;
	std::cout << "new d length = " << d.magnitude() << ", old length = " << len << std::endl;

	d = d * len;
	std::cout << "d = [" << d.getX() << ", " << d.getY() << ", " << d.getZ() << "]" << std::endl;

	z = d.vecfData();
	std::cout << "z = [" << z[0] << ", " << z[1] << ", " << z[2] << "]" << std::endl;
	std::cout << "\r\n";

	// FrameBuffer
         FrameBuffer f1(89,145), f2(480,48),f3(480,600), f4(10,10), f5(600,800);
    
    f1.setAll(255,255,255);//White

    f2.setAll(0,0,0);//Black
    
    f3.setAll(255,0,0);//red

    f4.setAll(0,255,0);//green

    
    f5.setAll(0,0,0);//multi
    
    f5.set(0,0,0,255,0);
    f5.set(f5.getHeight()-1, f5.getWidth()-1,255,0,0);
    f5.set(0, f5.getWidth()-1,255,255,0);
    
    f1.writePPM("white.ppm");
    f2.writePPM("black.ppm");
    f3.writePPM("red.ppm");
    f4.writePPM("green.ppm");
    f5.writePPM("multi.ppm");
	
	return 0;
}
