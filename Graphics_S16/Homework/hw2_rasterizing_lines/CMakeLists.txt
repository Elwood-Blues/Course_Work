cmake_minimum_required(VERSION 2.8)
project(hw2)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp Vector2D.cpp Vector2D.h Vector3D.cpp Vector3D.h FrameBuffer.cpp FrameBuffer.h)

add_executable(hw2 ${SOURCE_FILES})
