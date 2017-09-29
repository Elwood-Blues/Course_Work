//
// Created by Peter Braband on 5/2/16.
//

#ifndef HW4_5_TRIANGLE_H
#define HW4_5_TRIANGLE_H


#include <vector>
#include "Vector3D.h"
#include "Vector4D.h"

class Triangle {
public:
    Triangle();
    ~Triangle();
    Triangle(const double x1, const double y1, const double z1, const double red1, const double green1, const double blue1,
            const double x2, const double y2, const double z2, const double red2, const double green2, const double blue2,
            const double x3, const double y3, const double z3, const double red3, const double green3, const double blue3);
    void setVertexPos(const int vertex, const double x, const double y, const double z);
    void setVertexColor(const int vertex, const double red, const double green, const double blue);
    Vector4D getVertexPos(const int vertex) const;
    Vector3D getVertexColor(const int vertex) const;
    int getSize(void)const;
//    vertexData getVertex
    Vector3D getNormalToObject(const Vector3D& v);

private:
    struct vertexData{
        Vector4D pos;
        Vector3D color;
    };
    std::vector<vertexData> modelData;
    int size;
};


#endif //HW4_5_TRIANGLE_H
