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
include glfw-3.0.4/tests/CMakeFiles/joysticks.dir/depend.make

# Include the progress variables for this target.
include glfw-3.0.4/tests/CMakeFiles/joysticks.dir/progress.make

# Include the compile flags for this target's objects.
include glfw-3.0.4/tests/CMakeFiles/joysticks.dir/flags.make

glfw-3.0.4/tests/CMakeFiles/joysticks.dir/joysticks.c.o: glfw-3.0.4/tests/CMakeFiles/joysticks.dir/flags.make
glfw-3.0.4/tests/CMakeFiles/joysticks.dir/joysticks.c.o: ../glfw-3.0.4/tests/joysticks.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/elwood/Downloads/OpenGL_Lab1-master/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object glfw-3.0.4/tests/CMakeFiles/joysticks.dir/joysticks.c.o"
	cd /Users/elwood/Downloads/OpenGL_Lab1-master/build/glfw-3.0.4/tests && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/joysticks.dir/joysticks.c.o   -c /Users/elwood/Downloads/OpenGL_Lab1-master/glfw-3.0.4/tests/joysticks.c

glfw-3.0.4/tests/CMakeFiles/joysticks.dir/joysticks.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/joysticks.dir/joysticks.c.i"
	cd /Users/elwood/Downloads/OpenGL_Lab1-master/build/glfw-3.0.4/tests && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /Users/elwood/Downloads/OpenGL_Lab1-master/glfw-3.0.4/tests/joysticks.c > CMakeFiles/joysticks.dir/joysticks.c.i

glfw-3.0.4/tests/CMakeFiles/joysticks.dir/joysticks.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/joysticks.dir/joysticks.c.s"
	cd /Users/elwood/Downloads/OpenGL_Lab1-master/build/glfw-3.0.4/tests && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /Users/elwood/Downloads/OpenGL_Lab1-master/glfw-3.0.4/tests/joysticks.c -o CMakeFiles/joysticks.dir/joysticks.c.s

glfw-3.0.4/tests/CMakeFiles/joysticks.dir/joysticks.c.o.requires:

.PHONY : glfw-3.0.4/tests/CMakeFiles/joysticks.dir/joysticks.c.o.requires

glfw-3.0.4/tests/CMakeFiles/joysticks.dir/joysticks.c.o.provides: glfw-3.0.4/tests/CMakeFiles/joysticks.dir/joysticks.c.o.requires
	$(MAKE) -f glfw-3.0.4/tests/CMakeFiles/joysticks.dir/build.make glfw-3.0.4/tests/CMakeFiles/joysticks.dir/joysticks.c.o.provides.build
.PHONY : glfw-3.0.4/tests/CMakeFiles/joysticks.dir/joysticks.c.o.provides

glfw-3.0.4/tests/CMakeFiles/joysticks.dir/joysticks.c.o.provides.build: glfw-3.0.4/tests/CMakeFiles/joysticks.dir/joysticks.c.o


# Object files for target joysticks
joysticks_OBJECTS = \
"CMakeFiles/joysticks.dir/joysticks.c.o"

# External object files for target joysticks
joysticks_EXTERNAL_OBJECTS =

glfw-3.0.4/tests/joysticks: glfw-3.0.4/tests/CMakeFiles/joysticks.dir/joysticks.c.o
glfw-3.0.4/tests/joysticks: glfw-3.0.4/tests/CMakeFiles/joysticks.dir/build.make
glfw-3.0.4/tests/joysticks: glfw-3.0.4/src/libglfw3.a
glfw-3.0.4/tests/joysticks: glfw-3.0.4/tests/CMakeFiles/joysticks.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/elwood/Downloads/OpenGL_Lab1-master/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking C executable joysticks"
	cd /Users/elwood/Downloads/OpenGL_Lab1-master/build/glfw-3.0.4/tests && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/joysticks.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
glfw-3.0.4/tests/CMakeFiles/joysticks.dir/build: glfw-3.0.4/tests/joysticks

.PHONY : glfw-3.0.4/tests/CMakeFiles/joysticks.dir/build

glfw-3.0.4/tests/CMakeFiles/joysticks.dir/requires: glfw-3.0.4/tests/CMakeFiles/joysticks.dir/joysticks.c.o.requires

.PHONY : glfw-3.0.4/tests/CMakeFiles/joysticks.dir/requires

glfw-3.0.4/tests/CMakeFiles/joysticks.dir/clean:
	cd /Users/elwood/Downloads/OpenGL_Lab1-master/build/glfw-3.0.4/tests && $(CMAKE_COMMAND) -P CMakeFiles/joysticks.dir/cmake_clean.cmake
.PHONY : glfw-3.0.4/tests/CMakeFiles/joysticks.dir/clean

glfw-3.0.4/tests/CMakeFiles/joysticks.dir/depend:
	cd /Users/elwood/Downloads/OpenGL_Lab1-master/build && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/elwood/Downloads/OpenGL_Lab1-master /Users/elwood/Downloads/OpenGL_Lab1-master/glfw-3.0.4/tests /Users/elwood/Downloads/OpenGL_Lab1-master/build /Users/elwood/Downloads/OpenGL_Lab1-master/build/glfw-3.0.4/tests /Users/elwood/Downloads/OpenGL_Lab1-master/build/glfw-3.0.4/tests/CMakeFiles/joysticks.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : glfw-3.0.4/tests/CMakeFiles/joysticks.dir/depend

