# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.8

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /Applications/CMake.app/Contents/bin/cmake

# The command to remove a file.
RM = /Applications/CMake.app/Contents/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Users/elwood/Downloads/OpenGL_Lab1-master

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/elwood/Downloads/OpenGL_Lab1-master/build

# Include any dependencies generated for this target.
include utests/CMakeFiles/utest_Vector3D.dir/depend.make

# Include the progress variables for this target.
include utests/CMakeFiles/utest_Vector3D.dir/progress.make

# Include the compile flags for this target's objects.
include utests/CMakeFiles/utest_Vector3D.dir/flags.make

utests/CMakeFiles/utest_Vector3D.dir/utest_Vector3D.cpp.o: utests/CMakeFiles/utest_Vector3D.dir/flags.make
utests/CMakeFiles/utest_Vector3D.dir/utest_Vector3D.cpp.o: ../utests/utest_Vector3D.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/elwood/Downloads/OpenGL_Lab1-master/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object utests/CMakeFiles/utest_Vector3D.dir/utest_Vector3D.cpp.o"
	cd /Users/elwood/Downloads/OpenGL_Lab1-master/build/utests && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/utest_Vector3D.dir/utest_Vector3D.cpp.o -c /Users/elwood/Downloads/OpenGL_Lab1-master/utests/utest_Vector3D.cpp

utests/CMakeFiles/utest_Vector3D.dir/utest_Vector3D.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/utest_Vector3D.dir/utest_Vector3D.cpp.i"
	cd /Users/elwood/Downloads/OpenGL_Lab1-master/build/utests && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/elwood/Downloads/OpenGL_Lab1-master/utests/utest_Vector3D.cpp > CMakeFiles/utest_Vector3D.dir/utest_Vector3D.cpp.i

utests/CMakeFiles/utest_Vector3D.dir/utest_Vector3D.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/utest_Vector3D.dir/utest_Vector3D.cpp.s"
	cd /Users/elwood/Downloads/OpenGL_Lab1-master/build/utests && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/elwood/Downloads/OpenGL_Lab1-master/utests/utest_Vector3D.cpp -o CMakeFiles/utest_Vector3D.dir/utest_Vector3D.cpp.s

utests/CMakeFiles/utest_Vector3D.dir/utest_Vector3D.cpp.o.requires:

.PHONY : utests/CMakeFiles/utest_Vector3D.dir/utest_Vector3D.cpp.o.requires

utests/CMakeFiles/utest_Vector3D.dir/utest_Vector3D.cpp.o.provides: utests/CMakeFiles/utest_Vector3D.dir/utest_Vector3D.cpp.o.requires
	$(MAKE) -f utests/CMakeFiles/utest_Vector3D.dir/build.make utests/CMakeFiles/utest_Vector3D.dir/utest_Vector3D.cpp.o.provides.build
.PHONY : utests/CMakeFiles/utest_Vector3D.dir/utest_Vector3D.cpp.o.provides

utests/CMakeFiles/utest_Vector3D.dir/utest_Vector3D.cpp.o.provides.build: utests/CMakeFiles/utest_Vector3D.dir/utest_Vector3D.cpp.o


# Object files for target utest_Vector3D
utest_Vector3D_OBJECTS = \
"CMakeFiles/utest_Vector3D.dir/utest_Vector3D.cpp.o"

# External object files for target utest_Vector3D
utest_Vector3D_EXTERNAL_OBJECTS =

utests/utest_Vector3D: utests/CMakeFiles/utest_Vector3D.dir/utest_Vector3D.cpp.o
utests/utest_Vector3D: utests/CMakeFiles/utest_Vector3D.dir/build.make
utests/utest_Vector3D: src/libsive-rtutil.a
utests/utest_Vector3D: /usr/local/lib/libboost_program_options-mt.a
utests/utest_Vector3D: /usr/local/lib/libboost_unit_test_framework-mt.a
utests/utest_Vector3D: utests/CMakeFiles/utest_Vector3D.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/elwood/Downloads/OpenGL_Lab1-master/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable utest_Vector3D"
	cd /Users/elwood/Downloads/OpenGL_Lab1-master/build/utests && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/utest_Vector3D.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
utests/CMakeFiles/utest_Vector3D.dir/build: utests/utest_Vector3D

.PHONY : utests/CMakeFiles/utest_Vector3D.dir/build

utests/CMakeFiles/utest_Vector3D.dir/requires: utests/CMakeFiles/utest_Vector3D.dir/utest_Vector3D.cpp.o.requires

.PHONY : utests/CMakeFiles/utest_Vector3D.dir/requires

utests/CMakeFiles/utest_Vector3D.dir/clean:
	cd /Users/elwood/Downloads/OpenGL_Lab1-master/build/utests && $(CMAKE_COMMAND) -P CMakeFiles/utest_Vector3D.dir/cmake_clean.cmake
.PHONY : utests/CMakeFiles/utest_Vector3D.dir/clean

utests/CMakeFiles/utest_Vector3D.dir/depend:
	cd /Users/elwood/Downloads/OpenGL_Lab1-master/build && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/elwood/Downloads/OpenGL_Lab1-master /Users/elwood/Downloads/OpenGL_Lab1-master/utests /Users/elwood/Downloads/OpenGL_Lab1-master/build /Users/elwood/Downloads/OpenGL_Lab1-master/build/utests /Users/elwood/Downloads/OpenGL_Lab1-master/build/utests/CMakeFiles/utest_Vector3D.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : utests/CMakeFiles/utest_Vector3D.dir/depend

