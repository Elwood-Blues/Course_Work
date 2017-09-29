cmake_minimum_required(VERSION 2.8)
project(hw4.5)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp Vector2D.cpp Vector2D.h Vector3D.cpp Vector3D.h FrameBuffer.cpp FrameBuffer.h Vector4D.cpp Vector4D.h Matrix4x4.cpp Matrix4x4.h Triangle.cpp Triangle.h)

add_executable(hw4.5 ${SOURCE_FILES})
