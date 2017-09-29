/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 * Peter Braband.
 */

#include "FrameBuffer.h"
#include<fstream>


FrameBuffer::FrameBuffer() {
    h = 0;
    w = 0;
    data = new short int[this->h * this->w * 5];
    for(unsigned int i = 0; i < sizeof(data); ++i){
//        *(data + i) = 0;
        data[i] =0;
        }
}

FrameBuffer::FrameBuffer(int height, int width){
    h = height;
    w = width;
    data = new short int [this->h * this->w * 5];
    for(unsigned int i = 0; i < sizeof(data); ++i){
//        *(data + i) = 0;
        data[i] = 0;
    }
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
    /******Red Set******/
    if(red <= MAX && red >= MIN){
        data[(x*3) + (y*3)*w + 0] = red;
    }
    else(red < MIN)? (data[(x*3) + (y*3)*w + 0] = MIN):(data[(x*3) + (y*3)*w + 0] = MAX);

    /****Green Set*****/
    if(green <= MAX && green >= MIN){
        data[(x*3) + (y*3)*w + 1] = green;
    }
    else(green < MIN)? (data[(x*3) + (y*3)*w + 1] = MIN):(data[(x*3) + (y*3)*w + 1] = MAX);

    /********Blue Set******/
    if(blue <= MAX && blue >= MIN){
        data[(x*3) + (y*3)*w + 2] = blue;
    }
    else(blue < MIN)? (data[(x*3) + (y*3)*w + 2] = MIN):(data[(x*3) + (y*3)*w + 2] = 255);

}

void FrameBuffer::resize(int height, int width) {
    this->h = height;
    this->w = width;
    delete[](data);
    data = new short int [this->h * this->w * 5];
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
                    output << data[(i*3) + (j*3)*w +k] << " ";
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

int *FrameBuffer::bufferdata() {
    return (int *) data;
}
