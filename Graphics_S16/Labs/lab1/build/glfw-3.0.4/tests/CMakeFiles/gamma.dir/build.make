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
include glfw-3.0.4/tests/CMakeFiles/gamma.dir/depend.make

# Include the progress variables for this target.
include glfw-3.0.4/tests/CMakeFiles/gamma.dir/progress.make

# Include the compile flags for this target's objects.
include glfw-3.0.4/tests/CMakeFiles/gamma.dir/flags.make

glfw-3.0.4/tests/CMakeFiles/gamma.dir/gamma.c.o: glfw-3.0.4/tests/CMakeFiles/gamma.dir/flags.make
glfw-3.0.4/tests/CMakeFiles/gamma.dir/gamma.c.o: ../glfw-3.0.4/tests/gamma.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/elwood/Downloads/OpenGL_Lab1-master/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object glfw-3.0.4/tests/CMakeFiles/gamma.dir/gamma.c.o"
	cd /Users/elwood/Downloads/OpenGL_Lab1-master/build/glfw-3.0.4/tests && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/gamma.dir/gamma.c.o   -c /Users/elwood/Downloads/OpenGL_Lab1-master/glfw-3.0.4/tests/gamma.c

glfw-3.0.4/tests/CMakeFiles/gamma.dir/gamma.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/gamma.dir/gamma.c.i"
	cd /Users/elwood/Downloads/OpenGL_Lab1-master/build/glfw-3.0.4/tests && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /Users/elwood/Downloads/OpenGL_Lab1-master/glfw-3.0.4/tests/gamma.c > CMakeFiles/gamma.dir/gamma.c.i

glfw-3.0.4/tests/CMakeFiles/gamma.dir/gamma.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/gamma.dir/gamma.c.s"
	cd /Users/elwood/Downloads/OpenGL_Lab1-master/build/glfw-3.0.4/tests && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /Users/elwood/Downloads/OpenGL_Lab1-master/glfw-3.0.4/tests/gamma.c -o CMakeFiles/gamma.dir/gamma.c.s

glfw-3.0.4/tests/CMakeFiles/gamma.dir/gamma.c.o.requires:

.PHONY : glfw-3.0.4/tests/CMakeFiles/gamma.dir/gamma.c.o.requires

glfw-3.0.4/tests/CMakeFiles/gamma.dir/gamma.c.o.provides: glfw-3.0.4/tests/CMakeFiles/gamma.dir/gamma.c.o.requires
	$(MAKE) -f glfw-3.0.4/tests/CMakeFiles/gamma.dir/build.make glfw-3.0.4/tests/CMakeFiles/gamma.dir/gamma.c.o.provides.build
.PHONY : glfw-3.0.4/tests/CMakeFiles/gamma.dir/gamma.c.o.provides

glfw-3.0.4/tests/CMakeFiles/gamma.dir/gamma.c.o.provides.build: glfw-3.0.4/tests/CMakeFiles/gamma.dir/gamma.c.o


glfw-3.0.4/tests/CMakeFiles/gamma.dir/__/deps/getopt.c.o: glfw-3.0.4/tests/CMakeFiles/gamma.dir/flags.make
glfw-3.0.4/tests/CMakeFiles/gamma.dir/__/deps/getopt.c.o: ../glfw-3.0.4/deps/getopt.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/elwood/Downloads/OpenGL_Lab1-master/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building C object glfw-3.0.4/tests/CMakeFiles/gamma.dir/__/deps/getopt.c.o"
	cd /Users/elwood/Downloads/OpenGL_Lab1-master/build/glfw-3.0.4/tests && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/gamma.dir/__/deps/getopt.c.o   -c /Users/elwood/Downloads/OpenGL_Lab1-master/glfw-3.0.4/deps/getopt.c

glfw-3.0.4/tests/CMakeFiles/gamma.dir/__/deps/getopt.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/gamma.dir/__/deps/getopt.c.i"
	cd /Users/elwood/Downloads/OpenGL_Lab1-master/build/glfw-3.0.4/tests && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /Users/elwood/Downloads/OpenGL_Lab1-master/glfw-3.0.4/deps/getopt.c > CMakeFiles/gamma.dir/__/deps/getopt.c.i

glfw-3.0.4/tests/CMakeFiles/gamma.dir/__/deps/getopt.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/gamma.dir/__/deps/getopt.c.s"
	cd /Users/elwood/Downloads/OpenGL_Lab1-master/build/glfw-3.0.4/tests && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /Users/elwood/Downloads/OpenGL_Lab1-master/glfw-3.0.4/deps/getopt.c -o CMakeFiles/gamma.dir/__/deps/getopt.c.s

glfw-3.0.4/tests/CMakeFiles/gamma.dir/__/deps/getopt.c.o.requires:

.PHONY : glfw-3.0.4/tests/CMakeFiles/gamma.dir/__/deps/getopt.c.o.requires

glfw-3.0.4/tests/CMakeFiles/gamma.dir/__/deps/getopt.c.o.provides: glfw-3.0.4/tests/CMakeFiles/gamma.dir/__/deps/getopt.c.o.requires
	$(MAKE) -f glfw-3.0.4/tests/CMakeFiles/gamma.dir/build.make glfw-3.0.4/tests/CMakeFiles/gamma.dir/__/deps/getopt.c.o.provides.build
.PHONY : glfw-3.0.4/tests/CMakeFiles/gamma.dir/__/deps/getopt.c.o.provides

glfw-3.0.4/tests/CMakeFiles/gamma.dir/__/deps/getopt.c.o.provides.build: glfw-3.0.4/tests/CMakeFiles/gamma.dir/__/deps/getopt.c.o


# Object files for target gamma
gamma_OBJECTS = \
"CMakeFiles/gamma.dir/gamma.c.o" \
"CMakeFiles/gamma.dir/__/deps/getopt.c.o"

# External object files for target gamma
gamma_EXTERNAL_OBJECTS =

glfw-3.0.4/tests/gamma: glfw-3.0.4/tests/CMakeFiles/gamma.dir/gamma.c.o
glfw-3.0.4/tests/gamma: glfw-3.0.4/tests/CMakeFiles/gamma.dir/__/deps/getopt.c.o
glfw-3.0.4/tests/gamma: glfw-3.0.4/tests/CMakeFiles/gamma.dir/build.make
glfw-3.0.4/tests/gamma: glfw-3.0.4/src/libglfw3.a
glfw-3.0.4/tests/gamma: glfw-3.0.4/tests/CMakeFiles/gamma.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/elwood/Downloads/OpenGL_Lab1-master/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Linking C executable gamma"
	cd /Users/elwood/Downloads/OpenGL_Lab1-master/build/glfw-3.0.4/tests && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/gamma.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
glfw-3.0.4/tests/CMakeFiles/gamma.dir/build: glfw-3.0.4/tests/gamma

.PHONY : glfw-3.0.4/tests/CMakeFiles/gamma.dir/build

glfw-3.0.4/tests/CMakeFiles/gamma.dir/requires: glfw-3.0.4/tests/CMakeFiles/gamma.dir/gamma.c.o.requires
glfw-3.0.4/tests/CMakeFiles/gamma.dir/requires: glfw-3.0.4/tests/CMakeFiles/gamma.dir/__/deps/getopt.c.o.requires

.PHONY : glfw-3.0.4/tests/CMakeFiles/gamma.dir/requires

glfw-3.0.4/tests/CMakeFiles/gamma.dir/clean:
	cd /Users/elwood/Downloads/OpenGL_Lab1-master/build/glfw-3.0.4/tests && $(CMAKE_COMMAND) -P CMakeFiles/gamma.dir/cmake_clean.cmake
.PHONY : glfw-3.0.4/tests/CMakeFiles/gamma.dir/clean

glfw-3.0.4/tests/CMakeFiles/gamma.dir/depend:
	cd /Users/elwood/Downloads/OpenGL_Lab1-master/build && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/elwood/Downloads/OpenGL_Lab1-master /Users/elwood/Downloads/OpenGL_Lab1-master/glfw-3.0.4/tests /Users/elwood/Downloads/OpenGL_Lab1-master/build /Users/elwood/Downloads/OpenGL_Lab1-master/build/glfw-3.0.4/tests /Users/elwood/Downloads/OpenGL_Lab1-master/build/glfw-3.0.4/tests/CMakeFiles/gamma.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : glfw-3.0.4/tests/CMakeFiles/gamma.dir/depend

