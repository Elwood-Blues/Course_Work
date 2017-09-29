/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 * Peter Braband.
 */

#include "FrameBuffer.h"
#include<fstream>
#include <math.h>


FrameBuffer::FrameBuffer() {
    h = 0;
    w = 0;
    data.resize(h);
    for (int i = 0; i < h; ++i) {
        data[i].resize(w);
        for (int j = 0; j < w; ++j) {
            data[i][j].resize(DEPTH);
        }
    }
    for (int i = 0; i < h; ++i) {
        for (int j = 0; j < w; ++j) {
            for (int k = 0; k < DEPTH; ++k) {
                data[i][j][k] = 0;
            }
            data[i][j] = {0,0,0};
        }
    }
//    data = new short int[this->h * this->w * 5];
//    for(unsigned int i = 0; i < sizeof(data); ++i){
////        *(data + i) = 0;
//        data[i] =0;
//        }
}

FrameBuffer::FrameBuffer(int width, int height){
    h = height;
    w = width;
    data.resize(h);
    for (int i = 0; i < h; ++i) {
        data[i].resize(w);
        for (int j = 0; j < w; ++j) {
            data[i][j].resize(DEPTH);
        }
    }
    for (int i = 0; i < h; ++i) {
        for (int j = 0; j < w; ++j) {
            for (int k = 0; k < DEPTH; ++k) {
                data[i][j][k] = 0;
            }
//            data[i][j] = {0,0,0};
        }
    }
//    data = new short int [this->h * this->w * 5];
//    for(unsigned int i = 0; i < sizeof(data); ++i){
////        *(data + i) = 0;
//        data[i] = 0;
//    }
}

int FrameBuffer::getWidth() const {
    return this->w;
}

int FrameBuffer::getHeight() const {
    return this->h;
}
void FrameBuffer::setAll(short int red, short int green, short int blue){
    for(int i = 0; i < this->h-1; ++i){
        for(int j = 0; j < this->w-1; ++j){
        
            this->set(i,j,red,green,blue);
        }
    }
}
void FrameBuffer::set(int x, int y, short int red, short int green, short int blue) {

//    if(x < this->getWidth() && y < this->getHeight()) {
        /******Red Set******/
        if (red <= MAX && red >= MIN) {
            data[x][y][0] = red;
//            data[x][y].setRed(red);
//            data[(x * 3) + (y * 3) * w + 0] = red;
        }
        else (red < MIN) ? (data[x][y][0] = MIN) : (data[x][y][0] = MAX);
//        else(red < MIN) ? (data[x][y].setRed(MIN)) : (data[x][y].setRed(MAX));
//        else(red < MIN) ? (data[(x * 3) + (y * 3) * w + 0] = MIN) : (data[(x * 3) + (y * 3) * w + 0] = MAX);

        /****Green Set*****/
        if (green <= MAX && green >= MIN) {
            data[x][y][1] = green;
//            data[x][y].setGreen(green);
//            data[(x * 3) + (y * 3) * w + 1] = green;
        }
        else (green < MIN) ? (data[x][y][1] = MIN) : (data[x][y][1] = MAX);
//        else(green < MIN) ? (data[x][y].setGreen(MIN)) : (data[x][y].setGreen(MAX));
//        else(green < MIN) ? (data[(x * 3) + (y * 3) * w + 1] = MIN) : (data[(x * 3) + (y * 3) * w + 1] = MAX);

        /********Blue Set******/
        if (blue <= MAX && blue >= MIN) {
            data[x][y][2] = blue;
//            data[x][y].setBlue(blue);
//            data[(x * 3) + (y * 3) * w + 2] = blue;
        }
        else (blue < MIN) ? (data[x][y][2] = MIN) : (data[x][y][2] = MAX);
//        else(blue < MIN) ? (data[x][y].setBlue(MIN)) : (data[x][y].setBlue(MAX));
//        else(blue < MIN) ? (data[(x * 3) + (y * 3) * w + 2] = MIN) : (data[(x * 3) + (y * 3) * w + 2] = 255);
//    }
}

void FrameBuffer::resize(int height, int width) {
    this->h = height;
    this->w = width;
    data.resize(h);
    for (int i = 0; i < h; ++i) {
        data[i].resize(w);
        for (int j = 0; j < DEPTH; ++j) {
            data[i][j].resize(DEPTH);
        }
    }
//    delete[](data);
//    data = new short int [this->h * this->w * 5];
}

void FrameBuffer::writePPM(const std::string file) const {
    std::ofstream output;
    output.open(file.c_str());
    if(output.is_open()){
        output << "P3" << std::endl << "# " << file << std::endl << this->w <<  " " << this->h << " "
        << MAX << std::endl;
        for(int i = h -1; i >= 0; --i){
            for(int j = 0; j < w; ++j){
                for(int k = 0; k < 3; ++k){
//                    output << data[(i*3) + (j*3)*w +k] << " ";
                    output << data[i][j][k] << " ";
                }
            }
            output << std::endl;
        }
        output.close();
    }
    else{
        std::cout << "file could not be opened!\n";
    }
}

//int *FrameBuffer::bufferdata() {
//    return (int) &data;
//}

void FrameBuffer::rasterizeline_implicit(const Vector2D &p0, const Vector2D &p1, const Vector3D &c) {
    int x1 = (int) p0.getX();
    int x2 =(int) p1.getX();
    int y1 =(int) p0.getY();
    int y2 =(int) p1.getY();
    double dX = x2 - x1;
    double dY = y2 - y1;
    int x, y;
    if(fabs(dY) > fabs(dX)){
        for (y = y1; y != y2; y += sign(dY)) {
            x =(int) (x1 + (y - y1) * dX/dY);
            set(x, y,(short) c.getX(),(short) c.getY(),(short) c.getZ());
        }
    } else{
        for (x = x1; x != x2; x += sign(dX)) {
            y =(int) (y1 + (x - x1) * dY/dX);
            set((int) x,(int) y,(short) c.getX(),(short) c.getY(),(short) c.getZ());
        }
    }
//
//    double dX = p1.getX() - p0.getX();
//    double dY = p1.getY() - p0.getY();
//    double err = 0.0;
//    double dErr = fabs(dY/dX);
//
//    int y = p0.getY();
//    int x = p0.getX();
//
//    if(dErr > 1){
//        if(p1.getX() > p0.getX() && p1.getY() > p0.getY()){
//            for (int i = p0.getY(); i < p1.getY(); i++) {
//                set(x, i, c.getX(), c.getY(), c.getZ());
//                err = err + 1/dErr;
//
//                if(fabs(err) >= 0.5){
//                    x++;
//                    err--;
//                }
//            }
//        }
//
//        else if(p1.getX() > p0.getX() && p1.getY() < p0.getY()){
//            for(int i = p0.getY(); i > p1.getY(); i--){
//                set(x, i, c.getX(), c.getY(), c.getZ());
//                err = err + 1/dErr;
//
//                if(fabs(err) >= 0.5){
//                    x++;
//                    err--;
//                }
//            }
//        }
//
//        else if(p1.getX() < p0.getX() && p1.getY() > p0.getY()){
//            for (int i = p0.getY(); i < p1.getY(); i++) {
//                set(x, i, c.getX(), c.getY(), c.getZ());
//                err = err + 1/dErr;
//
//                if(fabs(err) >= 0.5) {
//                    x--;
//                    err--;
//                }
//            }
//        }
//
//        else{
//            for (int i = p0.getY(); i > p1.getY() ; i--) {
//                set(x, i, c.getX(), c.getY(), c.getZ());
//                err = err + 1/dErr;
//
//                if(fabs(err) >= 0.5) {
//                    x--;
//                    err--;
//                }
//            }
//        }
//    }
//
//    else{
//        if(p1.getX() > p0.getX() && p1.getY() > p0.getY()){
//            for (int i = p0.getX(); i < p1.getX(); i++) {
//                set(i, y, c.getX(), c.getY(), c.getZ());
//                err = err + dErr;
//
//                if(fabs(err) >= 0.5){
//                    y++;
//                    err--;
//                }
//            }
//        }
//        else if(p1.getX() > p0.getX() && p1.getY() < p0.getY()){
//            for (int i = p0.getX(); i < p1.getX(); i++) {
//                set(i, y, c.getX(), c.getY(), c.getZ());
//                err = err + dErr;
//
//                if(fabs(err) >= 0.5){
//                    y--;
//                    err--;
//                }
//            }
//        }
//        else if(p1.getX() < p0.getX() && p1.getY() < p0.getY()){
//            for (int i = p0.getX(); i > p1.getX() ; i--) {
//                set(i, y, c.getX(), c.getY(), c.getZ());
//                err = err + dErr;
//
//                if (fabs(err) >= 0.5){
//                    y--;
//                    err--;
//                }
//            }
//        }
//        else{
//            for (int i = p0.getX(); i > p1.getX() ; i--) {
//                set(i, y, c.getX(), c.getY(), c.getZ());
//                err = err + dErr;
//
//                if (fabs(err) >= 0.5){
//                    y++;
//                    err--;
//                }
//            }
//        }
//    }

}

void FrameBuffer::rasterizeline_parametric(const Vector2D &p0, const Vector2D &p1, const Vector3D &c0,
                                           const Vector3D &c1) {

    int x1 = (int) p0.getX();
    int x2 =(int) p1.getX();
    int y1 =(int) p0.getY();
    int y2 =(int) p1.getY();
    double dX = x2 - x1;
    double dY = y2 - y1;
    int x, y;

    Vector3D baseColor(c1.getX(), c1.getY(), c1.getZ());
    double distance = sqrt(dX * dX + dY * dY);
    double colorX = fabs(c1.getX() - c0.getX())/distance;
    double colorY = fabs(c1.getY() - c0.getY())/distance;
    double colorZ = fabs(c1.getZ() - c0.getZ())/distance;

    Vector3D colorStep((short) colorX,(short) colorY,(short) colorZ);

    if(fabs(dY) > fabs(dX)){
        for (y = y1; y != y2; y += sign(dY)) {
            x =(int) (x1 + (y - y1) * dX/dY);
            set(x, y,(short) baseColor.getX(),(short) baseColor.getY(),(short) baseColor.getZ());
            baseColor = baseColor + colorStep;
        }
    } else{
        for (x = x1; x != x2; x += sign(dX)) {
            y =(int) (y1 + (x - x1) * dY/dX);
            set(x, y,(short) baseColor.getX(),(short) baseColor.getY(),(short) baseColor.getZ());
            baseColor = baseColor + colorStep;
        }
    }
//    Vector3D baseColor(c1.getX(), c1.getY(), c1.getZ());
//    double dX = p1.getX() - p0.getX();
//    double dY = p1.getY() - p0.getY();
//    double err = 0.0;
//    double dErr = fabs(dY / dX);
//    double distance = sqrt(dX * dX + dY * dY);
//
//    double colorX = fabs(c1.getX() - c0.getX())/distance;
//    double colorY = fabs(c1.getY() - c0.getY())/distance;
//    double colorZ = fabs(c1.getZ() - c0.getZ())/distance;
//
//    Vector3D colorStep(colorX, colorY, colorZ);
//
//    int x = p0.getX();
//    int y = p0.getY();
//
//
//    if(dErr > 1){
//        if (p1.getX() > p0.getX() && p1.getY() > p0.getY()){
//            for (int i = p0.getY(); i < p1.getY(); i++) {
//                set(x, i, baseColor.getX(), baseColor.getY(), baseColor.getZ());
//                baseColor = baseColor + colorStep;
//                err = err + 1/dErr;
//
//                if (fabs(err) >= 0.5){
//                    x++;
//                    err--;
//                }
//            }
//        }
//        else if (p1.getX() > p0.getX() && p1.getY() < p0.getY()){
//            for (int i = p0.getY(); i > p1.getY(); i--) {
//                set(x, i, baseColor.getX(), baseColor.getY(), baseColor.getZ());
//                baseColor = baseColor + colorStep;
//                err = err + 1/dErr;
//
//                if (fabs(err) >= 0.5){
//                    x++;
//                    err--;
//                }
//            }
//        }
//        else if (p1.getX() < p0.getX() && p1.getY() > p0.getY()){
//            for (int i = p0.getY(); i < p1.getY(); i++) {
//                set(x, i, baseColor.getX(), baseColor.getY(), baseColor.getZ());
//                baseColor = baseColor + colorStep;
//                err = err + 1/dErr;
//
//                if (fabs(err) >= 0.5){
//                    x--;
//                    err--;
//                }
//            }
//        }
//        else {
//            for (int i = p0.getY(); i > p1.getY() ; i--) {
//                set(x, i, baseColor.getX(), baseColor.getY(), baseColor.getZ());
//                baseColor = baseColor + colorStep;
//                err = err + 1/dErr;
//
//                if (fabs(err) >= 0.5){
//                    x--;
//                    err--;
//                }
//            }
//        }
//    }
//    else{
//        if (p1.getX() > p0.getX() && p1.getY() > p0.getY()){
//            for (int i = p0.getX(); i < p1.getX(); i++) {
//                set(i, y, baseColor.getX(), baseColor.getY(), baseColor.getZ());
//                baseColor = baseColor + colorStep;
//                err += dErr;
//
//                if (fabs(err) >= 0.5){
//                    y++;
//                    err--;
//                }
//            }
//        }
//        else if (p1.getX() > p0.getX() && p1.getY() < p0.getY()){
//            for (int i = p0.getX(); i < p1.getX(); i++) {
//                set(i, y, baseColor.getX(), baseColor.getY(), baseColor.getZ());
//                baseColor = baseColor + colorStep;
//                err += dErr;
//
//                if (fabs(err) >= 0.5){
//                    y--;
//                    err--;
//                }
//            }
//        }
//        else if (p1.getX() < p0.getX() && p1.getY() < p0.getY()){
//            for (int i = p0.getX(); i > p1.getX(); i--) {
//                set(i, y, baseColor.getX(), baseColor.getY(), baseColor.getZ());
//                baseColor = baseColor + colorStep;
//                err += dErr;
//
//                if (fabs(err) >= 0.5){
//                    y--;
//                    err--;
//                }
//            }
//        }
//        else{
//            for (int i = p0.getX(); i > p1.getX(); i--) {
//                set(i, y, baseColor.getX(), baseColor.getY(), baseColor.getZ());
//                baseColor = baseColor + colorStep;
//                err += dErr;
//
//                if (fabs(err) >= 0.5){
//                    y++;
//                    err--;
//                }
//            }
//        }
//    }

}

int FrameBuffer::sign(double x) {
    if (x < 0){
        return -1;
    }
    else{
        return 1;
    }
}

void FrameBuffer::rasterize_Triangle(const Vector2D &p0, const Vector2D &p1, const Vector2D &p2, const Vector3D &c0,
                                     const Vector3D &c1, const Vector3D &c2) {

}






