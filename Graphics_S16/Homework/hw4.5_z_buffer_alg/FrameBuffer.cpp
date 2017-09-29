/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 * Peter Braband.
 */

#include "FrameBuffer.h"
#include "Matrix4x4.h"
#include "Triangle.h"
#include<fstream>
#include <cmath>


FrameBuffer::FrameBuffer() {
    h = 0;
    w = 0;
    data.resize(h);
    for (int i = 0; i < h; ++i) {
        data[i].resize(w);
        for (int j = 0; j < w; ++j) {
            data[i][j].resize(DEPTH);
            for (int k = 0; k < DEPTH; ++k) {
                data[i][j][k] = 0;
            }
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
}

FrameBuffer::FrameBuffer(int width, int height){
    h = height;
    w = width;
    data.resize(h);
    for (int i = 0; i < h; ++i) {
        data[i].resize(w);
        for (int j = 0; j < w; ++j) {
            data[i][j].resize(DEPTH);
            for (int k = 0; k < DEPTH; ++k) {
                data[i][j][k] = 0;
            }
        }
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
        if (red <= MAX && red >= MIN) {
            data[x][y][0] = red;
        }
        else (red < MIN) ? (data[x][y][0] = MIN) : (data[x][y][0] = MAX);

        /****Green Set*****/
        if (green <= MAX && green >= MIN) {
            data[x][y][1] = green;
        }
        else (green < MIN) ? (data[x][y][1] = MIN) : (data[x][y][1] = MAX);

        /********Blue Set******/
        if (blue <= MAX && blue >= MIN) {
            data[x][y][2] = blue;
        }
        else (blue < MIN) ? (data[x][y][2] = MIN) : (data[x][y][2] = MAX);
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
}

int FrameBuffer::sign(double x) {
    if (x < 0){
        return -1;
    }
    else{
        return 1;
    }
}



double FrameBuffer::f_0_1(int x, int y, const Vector2D &p0, const Vector2D &p1) {
    return (p0.getY() - p1.getY())*x + (p1.getX() - p0.getX())*y + p0.getX()*p1.getY() - p1.getX()*p0.getY();
}

double FrameBuffer::f_1_2(int x, int y, const Vector2D &p1, const Vector2D &p2) {
    return (p1.getY() - p2.getY())*x + (p2.getX() - p1.getX())*y + p1.getX()*p2.getY() - p2.getX()*p1.getY();
}

double FrameBuffer::f_2_0(int x, int y, const Vector2D &p2, const Vector2D &p0) {
    return (p2.getY() - p0.getY())*x + (p0.getX() - p2.getX())*y + p2.getX()*p0.getY() - p0.getX()*p2.getY();
}

void FrameBuffer::rayTrace() {
    //variable declarations
    double left(0.0), right(1.0), bottom(0.0), top(1.0), near(0.0), far(-10.0), radius(0.25), u, v, t_0, t_1, t, k_a(0.9), I_a(0.3), k_d(0.9), L, I(1.0);
    int n_x(this->getWidth()), n_y(this->getHeight());
    bool  hit, ortho(0);
    Vector3D e_vec(0.0,0.0,0.0), c_vec(0.5, 0.5, -5.0), d_vec(0.0, 0.0, -1.0), K_d(1.0, 0, 0), I_d(0.0, 0.0, 1.0), n, h, l, v_vec, k_s;
    I_d.normalize();
    u = left;
    v = bottom;
    t = 0.0;

    for (int j = 0; j < n_y; ++j) {
        for (int i = 0; i < n_x; ++i) {
            u = (left + (right-left)) * (i + 0.5)/ n_x;
            v = (bottom + (top -bottom)) * (j + 0.5)/ n_y;

            ortho = true;
            if(ortho) {
                e_vec.setX(u);
                e_vec.setY(v);
                e_vec.setZ(0);

                d_vec.setX(0.0);
                d_vec.setY(0.0);
                d_vec.setZ(-1.0);

                t_0 = near;
                t_1 = 1000.0;
            }
            else {
                e_vec.setX(0.0);
                e_vec.setY(0.0);
                e_vec.setZ(0.0);
                d_vec.setX(u);
                d_vec.setY(v);
                d_vec.setZ(-1.0);

                t_0 = 1.0;
                t_1 = 1000.0;
            }

            hit = isHit(c_vec, d_vec, e_vec, radius);
            if (hit && computeT(c_vec, d_vec, e_vec, radius, t_0, t_1, t)) {
//              t = computeT(c_vec, d_vec, e_vec, radius, t_0, t_1, t);

//              std::cout << "HIT\n";
//              std::cout << "t value: " << t << std::endl;

                Vector3D pt = e_vec + (d_vec * t);
                n = pt - c_vec;
//              std::cout << "n ----- length = " << n.magnitude() << std::endl;
//              n.display();
                n.normalize();
                Vector3D normalVis = n;
//              std::cout << "normalVis ---- length = " << normalVis.magnitude() << std::endl;
//              normalVis.display();

//              normalVis.setX(normalVis.getX() + 1.0);
//              normalVis.setY(normalVis.getY() + 1.0);
//              normalVis.setZ(normalVis.getZ() + 1.0);
//              normalVis.setX(normalVis.getX() / 2.0);
//              normalVis.setY(normalVis.getY() / 2.0);
//              normalVis.setZ(normalVis.getZ() / 2.0);
                l = I_d - pt;
                l.normalize();
                v_vec = e_vec;
                v_vec.normalize();
                h = (v_vec + l);
                h.normalize();
//              k_s.setX(this->data[u][v][0]);
//              k_s.setY(this->data[u][v][1]);
//              k_s.setZ(this->data[u][v][2]);

                double ndotL = std::max(0.0, n.dotProduct(I_d));
                double ndotH = std::max(0.0, n.dotProduct(h));
//                std::cout << "---Setting Color---" << ndotL << std::endl;
//                std::cout << normalVis.getX() * 255 << ", " << normalVis.getY() * 255 << ", " << normalVis.getZ() * 255 << std::endl;
//                        set(i, j, (short) (normalVis.getX() * 255.0), (short) (normalVis.getY() * 255.0),
//                            (short) (normalVis.getZ() * 255.0));
//                    L = k_d * I_a + k_d * I *  ndotL + k_a * I * pow(ndotH, 100.0);
                        Vector3D diffuse = K_d * ndotL;
                        Vector3D pixIntesity = diffuse;

                    pixIntesity = normalVis * 255.0;
//                        if(L > 1.0) L = 1.0;
//                        set(i, j, short(L * 255), short(L * 255), short(L * 255));
                        set(i, j, short(pixIntesity.getX() * 255), short(pixIntesity.getY() * 255), short(pixIntesity.getZ() * 255));
                    set(i,j ,short (ndotL * 255),short (ndotL * 255),short (ndotL * 255));


                }
                else {
                    set(i, j, 0, 0, 0);
                }
            }
    }
}

bool FrameBuffer::isHit(Vector3D &c_vec, Vector3D &d_vec, Vector3D &e_vec, double &rad) {
    double val = calcDiscriminant(c_vec, d_vec, e_vec, rad);
    if(val >= 0) return true;
    else return false;
}

bool FrameBuffer::computeT(Vector3D &c_vec, Vector3D &d_vec, Vector3D e_vec, double &rad, double t_0, double t_1, double& t) {
    double  tPlus, tMinus, disc;
//    e_vec.display();
    disc = calcDiscriminant(c_vec, d_vec, e_vec, rad);
//    std::cout << "discriminat val: " << disc << std::endl;

    Vector3D negD = d_vec * -1.0;
    double negD_EmC = negD.dotProduct(e_vec - c_vec);
    double dDotD = d_vec.dotProduct(d_vec);

    tMinus = (negD_EmC - sqrt(disc)) / dDotD;
    tPlus = (negD_EmC + sqrt(disc)) / dDotD;
//    std::cout << "tminus: " << tMinus << "tPlus: " << tPlus << std::endl;

    if(tMinus <= tPlus && tMinus >= t_0 && tMinus <= t_1){
        t = tMinus;
        return true;
    }
    else if(tPlus >= t_0 && tPlus <= t_1) {
        t = tPlus;
        return true;
    }
    else return false;
}

double FrameBuffer::calcDiscriminant(Vector3D &c_vec, Vector3D &d_vec, Vector3D &e_vec, double &rad) {
    Vector3D eMinusC = e_vec - c_vec;
    double dDotEC = d_vec.dotProduct( eMinusC );
    double dDotD = d_vec.dotProduct(d_vec);
    return (dDotEC * dDotEC) - dDotD * (eMinusC.dotProduct(eMinusC) - rad*rad);
}

void FrameBuffer::rasterize_Triangle(const Vector2D &p0, const Vector2D &p1, const Vector2D &p2, const Vector3D &c0,
                                     const Vector3D &c1, const Vector3D &c2) {
    double xMin = fmin(p0.getX(), fmin(p1.getX(), p2.getX()));
    double xMax = fmax(p0.getX(), fmax(p1.getX(), p2.getX()));
    double yMin = fmin(p0.getY(), fmin(p1.getY(), p2.getY()));
    double yMax = fmax(p0.getY(), fmax(p1.getY(), p2.getY()));

    double alpha, beta, gamma, tempDbl;
    Vector3D c; //color
    Vector2D temp1, temp2;
    temp1.set(-1,-1);
    temp2.set(-1,-1);
    for (int y =(int) yMin; y < yMax; ++y) {
        for (int x =(int) xMin; x < xMax ; ++x) {
            alpha = f_1_2(x,y, p1, p2) / f_1_2((int) p0.getX(),(int) p0.getY(), p1, p2);
            beta = f_2_0(x, y, p2, p0) / f_2_0((int) p1.getX(),(int) p1.getY(), p2, p0);
            gamma = f_0_1(x, y, p0, p1) / f_0_1((int) p2.getX(), (int) p2.getY(), p0, p1);
            if (alpha >= 0 && beta >= 0 && gamma >= 0){
                if ((alpha > 0 || alpha * f_1_2(x, y, temp1, temp2) > 0) && (beta > 0 || beta * f_2_0(x, y, temp1, temp2) > 0)
                    && ( gamma > 0 || gamma * f_0_1(x, y, temp1, temp2))){
                    c.setX((alpha * c0.getX()) + (beta * c1.getX()) + (gamma * c2.getX()));
                    c.setY((alpha * c0.getY()) + (beta * c1.getY()) + (gamma * c2.getY()));
                    c.setZ((alpha * c0.getY()) + (beta * c1.getY()) + (gamma * c2.getY()));
                    set(x, y, (short)c.getX(),(short) c.getY(),(short) c.getZ());
                }
            }
        }
    }
}

void FrameBuffer::zBuffRender(bool isOrtho) {
    double left(-10.0), right(10.0), bottom(-10.0), top(10.0), near(-5.0), far(-25.0);
    int n_x(500), n_y(500);
    std::vector<Triangle> triangleContainer;
//    FrameBuffer zBuffer(n_x, n_y);
    Triangle triangle1(0.0, 0.0, -5.0, 1.0, 0.0, 0.0, 1.0, 3.0, -7.0, 1.0, 0.0, 0.0, -1.0, 0.8, -5.0, 1.0, 0.0, 0.0);
    Triangle triangle2(3.0, 3.0, -5.0, 0.0, 1.0, 1.0, -5.0, -8.0, -10.0, 0.0, 1.0, 1.0, -2.0, 5.0, -6.0, 0.0, 1.0, 1.0);
    triangleContainer.push_back(triangle1);
    triangleContainer.push_back(triangle2);

    Vector3D light_direc(-1.0, 1.0, 1.0), light_Col(1.0, 1.0, 1.0);
    Matrix4x4 sceneMatrix, matrix_VP, matrix_ortho;
    matrix_ortho.makeOrtho(left, right, bottom, top, near, far);
    matrix_VP.makeViewPort(n_x, n_y);

    Vector3D E(1.0, 1.0, 10.0), G(0.0, 0.0, -1.0), T(0.1, 1.0, 0.0), W, U, V;
    W = G * (1.0 / G.magnitude()) * -1.0;
    U = T.crossProduct(W) * (T.crossProduct(W)).magnitude();
    V = W.crossProduct(U);
    if (!isOrtho){
        Matrix4x4 Proj(near, 0.0, 0.0, 0.0,
                        0.0, near, 0.0, 0.0,
                        0.0, 0.0, near + far, -1 *far*near,
                        0.0, 0.0, 1.0, 0.0);
        matrix_ortho = matrix_ortho * Proj;
        E.set(0.0, 0.0, 0.0);
        G.set(0.0, 0.0, -1.0);
        T.set(0.0, 1.0, 0.0);
        W = G * (1.0 / G.magnitude()) * -1;
        U = T.crossProduct(W) * (T.crossProduct(W)).magnitude();
        V = W.crossProduct(U);
    }

    Matrix4x4 sceneView(U.getX(), U.getY(), U.getZ(), 0.0,
                        V.getX(), V.getY(), V.getZ(), 0.0,
                        W.getX(), W.getY(), W.getZ(), 0.0,
                        0.0, 0.0, 0.0, 1.0);
    Matrix4x4 sceneView2(1.0, 0.0, 0.0, -E.getX(),
                        0.0, 1.0, 0.0, -E.getY(),
                        0.0, 0.0, 1.0, E.getZ(),
                        0.0, 0.0, 0.0, 1.0);
    Matrix4x4 camera = sceneView * sceneView2;
    sceneMatrix = (matrix_VP * matrix_ortho) * camera;

    std::vector<std::vector<double>>zBuff;
    zBuff.resize(n_y);
    for (int i = 0; i < n_y; ++i) {
        zBuff.at(i).resize(n_x);
        for (int j = 0; j < n_x; ++j) {
            zBuff.at(i).at(j) = far;
        }
    }
    for (int k = 0; k < triangleContainer.size() ; ++k) {
        Triangle tempTriangle = triangleContainer.at(k);
        Vector4D firstVertex_P0 = sceneMatrix * tempTriangle.getVertexPos(0);
        Vector3D firstVertex_C0 = tempTriangle.getVertexColor(0);
        firstVertex_P0.makeHomogonize();

        Vector4D secondVertex_P1 = sceneMatrix * tempTriangle.getVertexPos(1);
        Vector3D secondVertex_C1 = tempTriangle.getVertexColor(1);
        secondVertex_P1.makeHomogonize();

        Vector4D thirdVertex_P2 = sceneMatrix * tempTriangle.getVertexPos(2);
        Vector3D thirdVertex_C2 = tempTriangle.getVertexColor(2);
        thirdVertex_P2.makeHomogonize();

        int xMax = (int) std::max(firstVertex_P0.getX(), std::max(secondVertex_P1.getX(), thirdVertex_P2.getX()) );
        int yMax = (int) std::max(firstVertex_P0.getY(), std::max(secondVertex_P1.getY(), thirdVertex_P2.getY()) );
        int xMin = (int) std::min(firstVertex_P0.getX(), std::min(secondVertex_P1.getX(), thirdVertex_P2.getX()) );
        int yMin = (int) std::min(firstVertex_P0.getY(), std::min(secondVertex_P1.getY(), thirdVertex_P2.getY()) );

        for (int y = yMin; y <= yMax; ++y) {
            for (int x = xMin; x <= xMax; ++x) {
                double alphaVal = f_1_2(x,y, secondVertex_P1, thirdVertex_P2) / f_1_2(firstVertex_P0.getX(), firstVertex_P0.getY(), secondVertex_P1, thirdVertex_P2);
                double betaVal = f_2_0(x, y, thirdVertex_P2, firstVertex_P0) / f_2_0(secondVertex_P1.getX(), secondVertex_P1.getY(), thirdVertex_P2, firstVertex_P0);
                double gammaVal = f_0_1(x, y, firstVertex_P0, secondVertex_P1) / f_0_1(thirdVertex_P2.getX(), thirdVertex_P2.getY(), firstVertex_P0, secondVertex_P1);

                if (alphaVal > 0 && betaVal > 0 && gammaVal >0){
                    double z = alphaVal * firstVertex_P0.getZ() + betaVal * secondVertex_P1.getZ() + gammaVal * thirdVertex_P2.getZ();
                    if (z > zBuff.at(x).at(y)){
                        light_direc.normalize();

                        Vector3D h = Vector3D(0.0, 0.0, 1.0) + light_direc;
                        h = h * (1.0 / h.magnitude());
                        double L[3] = {0.0, 0.0, 0.0}, k_a(0.9), l_a(0.3), k_d(0.9), k_s(1.0), intensity(1.0);

                        Vector3D normal = tempTriangle.getNormalToObject(light_direc);
                        L[0] += k_a * intensity + k_d * light_Col.getX() * std::max(0.0, normal.dotProduct(light_direc)) + k_s * light_Col.getX() * pow(std::max(0.0, normal.dotProduct(h)), 1000);
                        L[1] += k_a * intensity + k_d * light_Col.getY() * std::max(0.0, normal.dotProduct(light_direc)) + k_s * light_Col.getY() * pow(std::max(0.0, normal.dotProduct(h)), 1000);
                        L[2] += k_a * intensity + k_d * light_Col.getZ() * std::max(0.0, normal.dotProduct(light_direc)) + k_s * light_Col.getZ() * pow(std::max(0.0, normal.dotProduct(h)), 1000);

                        for (int i = 0; i < 3; ++i) {
                            if(L[i] > 1.0) L[i] = 1.0;
                        }
//                        tempTriangle.getVertexColor(0).setX(tempTriangle.getVertexColor(0).getX() * 255);
//                        tempTriangle.getVertexColor(0).setY(tempTriangle.getVertexColor(0).getY() * 255);
//                        tempTriangle.getVertexColor(0).setZ(tempTriangle.getVertexColor(0).getZ() * 255);

                        zBuff[x][y] = z;
//                        tempTriangle.getVertexColor(0).display();
                        double newRed = tempTriangle.getVertexColor(0).getX() * L[0];
//                        std::cout << "newRed: " << newRed << std::endl;
                        double newGreen = tempTriangle.getVertexColor(0).getY() * L[1];
//                        std::cout << "newGreen: " << newGreen << std::endl;
                        double newBlue = tempTriangle.getVertexColor(0).getZ() * L[2];
//                        std::cout << "newBlue: " << newBlue << std::endl;
                        newRed *=255;
                        newGreen *=255;
                        newBlue *= 255;
                        this->set(x, y, short(newRed), short(newGreen), short(newBlue) );
                    }
                }
            }

        }
    }
}

void FrameBuffer::rasterize_Triangle(const Vector4D &p0, const Vector4D &p1, const Vector4D &p2, const Vector3D &c0,
                                     const Vector3D &c1, const Vector3D &c2) {
    double xMin = fmin(p0.getX(), fmin(p1.getX(), p2.getX()));
    double xMax = fmax(p0.getX(), fmax(p1.getX(), p2.getX()));
    double yMin = fmin(p0.getY(), fmin(p1.getY(), p2.getY()));
    double yMax = fmax(p0.getY(), fmax(p1.getY(), p2.getY()));

    double alpha, beta, gamma, tempDbl;
    Vector3D c; //color
    Vector4D temp1, temp2;
    temp1.set(-1.0,-1.0, 1.0, 1.0);
    temp2.set(-1.0,-1.0, 1.0, 1.0);
    for (int y =(int) yMin; y < yMax; ++y) {
        for (int x =(int) xMin; x < xMax ; ++x) {
            alpha = f_1_2(x,y, p1, p2) / f_1_2((int) p0.getX(),(int) p0.getY(), p1, p2);
            beta = f_2_0(x, y, p2, p0) / f_2_0((int) p1.getX(),(int) p1.getY(), p2, p0);
            gamma = f_0_1(x, y, p0, p1) / f_0_1((int) p2.getX(), (int) p2.getY(), p0, p1);
            if (alpha >= 0 && beta >= 0 && gamma >= 0){
                if ((alpha > 0 || alpha * f_1_2(x, y, temp1, temp2) > 0) && (beta > 0 || beta * f_2_0(x, y, temp1, temp2) > 0)
                    && ( gamma > 0 || gamma * f_0_1(x, y, temp1, temp2))){
                    c.setX((alpha * c0.getX()) + (beta * c1.getX()) + (gamma * c2.getX()));
                    c.setY((alpha * c0.getY()) + (beta * c1.getY()) + (gamma * c2.getY()));
                    c.setZ((alpha * c0.getY()) + (beta * c1.getY()) + (gamma * c2.getY()));
                    set(x, y, (short)c.getX(),(short) c.getY(),(short) c.getZ());
                }
            }
        }
    }
}

double FrameBuffer::f_0_1(double x, double y, const Vector4D &p0, const Vector4D &p1) {
    return (p0.getY() - p1.getY())*x + (p1.getX() - p0.getX())*y + p0.getX()*p1.getY() - p1.getX()*p0.getY();
}

double FrameBuffer::f_1_2(double x, double y, const Vector4D &p1, const Vector4D &p2) {
    return (p1.getY() - p2.getY())*x + (p2.getX() - p1.getX())*y + p1.getX()*p2.getY() - p2.getX()*p1.getY();
}

double FrameBuffer::f_2_0(double x, double y, const Vector4D &p2, const Vector4D &p0) {
    return (p2.getY() - p0.getY())*x + (p0.getX() - p2.getX())*y + p2.getX()*p0.getY() - p0.getX()*p2.getY();
}



