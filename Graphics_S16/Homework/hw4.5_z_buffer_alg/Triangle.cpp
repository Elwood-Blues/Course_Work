//
// Created by Peter Braband on 5/2/16.
//

#include "Triangle.h"

Vector4D Triangle::getVertexPos(const int vertex) const {
    return modelData.at(vertex).pos;
}

Vector3D Triangle::getVertexColor(const int vertex) const {
    return modelData.at(vertex).color;
}

Triangle::Triangle() {
    vertexData v1, v2, v3;
    v1.pos.set(0.0, 0.0, 0.0);
    v1.color.set(0.0, 0.0, 0.0);
    v2.pos.set(0.0, 0.0, 0.0);
    v2.color.set(0.0, 0.0, 0.0);
    v3.pos.set(0.0, 0.0, 0.0);
    v3.color.set(0.0, 0.0, 0.0);
    modelData.push_back(v1);
    modelData.push_back(v2);
    modelData.push_back(v3);
}

void Triangle::setVertexPos(const int vertex, const double x, const double y, const double z) {
    modelData.at(vertex).pos.set(x, y, z);
}

void Triangle::setVertexColor(const int vertex, const double red, const double green, const double blue) {
    modelData.at(vertex).color.set(red, green, blue);
}

Triangle::~Triangle() {

}

Triangle::Triangle(const double x1, const double y1, const double z1, const double red1, const double green1,
                   const double blue1, const double x2, const double y2, const double z2, const double red2,
                   const double green2, const double blue2, const double x3, const double y3, const double z3,
                   const double red3, const double green3, const double blue3) {
    vertexData v1, v2, v3;
    v1.pos.set(x1, y1, z1);
    v1.color.set(red1, green1, blue1);
    v2.pos.set(x2, y2, z2);
    v2.color.set(red2, green2, blue2);
    v3.pos.set(x3, y3, z3);
    v3.color.set(red3, green3, blue3);
    modelData.push_back(v1);
    modelData.push_back(v2);
    modelData.push_back(v3);
}

int Triangle::getSize(void) const {
    return (int) (modelData.size());
}

Vector3D Triangle::getNormalToObject(const Vector3D &v) {
    Vector3D p0(modelData.at(0).pos.getX(), modelData.at(0).pos.getY(), modelData.at(0).pos.getZ());
    Vector3D p1(modelData.at(1).pos.getX(), modelData.at(1).pos.getY(), modelData.at(1).pos.getZ());
    Vector3D p2(modelData.at(2).pos.getX(), modelData.at(2).pos.getY(), modelData.at(2).pos.getZ());
    Vector3D n = (p1 - p0).crossProduct(p2 - p0);
    n.normalize();
    return n;
}














