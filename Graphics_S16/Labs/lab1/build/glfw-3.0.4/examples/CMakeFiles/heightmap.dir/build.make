# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.2

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
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1"

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1/build"

# Include any dependencies generated for this target.
include glfw-3.0.4/examples/CMakeFiles/heightmap.dir/depend.make

# Include the progress variables for this target.
include glfw-3.0.4/examples/CMakeFiles/heightmap.dir/progress.make

# Include the compile flags for this target's objects.
include glfw-3.0.4/examples/CMakeFiles/heightmap.dir/flags.make

glfw-3.0.4/examples/CMakeFiles/heightmap.dir/heightmap.c.o: glfw-3.0.4/examples/CMakeFiles/heightmap.dir/flags.make
glfw-3.0.4/examples/CMakeFiles/heightmap.dir/heightmap.c.o: ../glfw-3.0.4/examples/heightmap.c
	$(CMAKE_COMMAND) -E cmake_progress_report "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1/build/CMakeFiles" $(CMAKE_PROGRESS_1)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building C object glfw-3.0.4/examples/CMakeFiles/heightmap.dir/heightmap.c.o"
	cd "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1/build/glfw-3.0.4/examples" && /usr/bin/cc  $(C_DEFINES) $(C_FLAGS) -o CMakeFiles/heightmap.dir/heightmap.c.o   -c "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1/glfw-3.0.4/examples/heightmap.c"

glfw-3.0.4/examples/CMakeFiles/heightmap.dir/heightmap.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/heightmap.dir/heightmap.c.i"
	cd "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1/build/glfw-3.0.4/examples" && /usr/bin/cc  $(C_DEFINES) $(C_FLAGS) -E "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1/glfw-3.0.4/examples/heightmap.c" > CMakeFiles/heightmap.dir/heightmap.c.i

glfw-3.0.4/examples/CMakeFiles/heightmap.dir/heightmap.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/heightmap.dir/heightmap.c.s"
	cd "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1/build/glfw-3.0.4/examples" && /usr/bin/cc  $(C_DEFINES) $(C_FLAGS) -S "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1/glfw-3.0.4/examples/heightmap.c" -o CMakeFiles/heightmap.dir/heightmap.c.s

glfw-3.0.4/examples/CMakeFiles/heightmap.dir/heightmap.c.o.requires:
.PHONY : glfw-3.0.4/examples/CMakeFiles/heightmap.dir/heightmap.c.o.requires

glfw-3.0.4/examples/CMakeFiles/heightmap.dir/heightmap.c.o.provides: glfw-3.0.4/examples/CMakeFiles/heightmap.dir/heightmap.c.o.requires
	$(MAKE) -f glfw-3.0.4/examples/CMakeFiles/heightmap.dir/build.make glfw-3.0.4/examples/CMakeFiles/heightmap.dir/heightmap.c.o.provides.build
.PHONY : glfw-3.0.4/examples/CMakeFiles/heightmap.dir/heightmap.c.o.provides

glfw-3.0.4/examples/CMakeFiles/heightmap.dir/heightmap.c.o.provides.build: glfw-3.0.4/examples/CMakeFiles/heightmap.dir/heightmap.c.o

glfw-3.0.4/examples/CMakeFiles/heightmap.dir/__/deps/getopt.c.o: glfw-3.0.4/examples/CMakeFiles/heightmap.dir/flags.make
glfw-3.0.4/examples/CMakeFiles/heightmap.dir/__/deps/getopt.c.o: ../glfw-3.0.4/deps/getopt.c
	$(CMAKE_COMMAND) -E cmake_progress_report "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1/build/CMakeFiles" $(CMAKE_PROGRESS_2)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building C object glfw-3.0.4/examples/CMakeFiles/heightmap.dir/__/deps/getopt.c.o"
	cd "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1/build/glfw-3.0.4/examples" && /usr/bin/cc  $(C_DEFINES) $(C_FLAGS) -o CMakeFiles/heightmap.dir/__/deps/getopt.c.o   -c "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1/glfw-3.0.4/deps/getopt.c"

glfw-3.0.4/examples/CMakeFiles/heightmap.dir/__/deps/getopt.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/heightmap.dir/__/deps/getopt.c.i"
	cd "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1/build/glfw-3.0.4/examples" && /usr/bin/cc  $(C_DEFINES) $(C_FLAGS) -E "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1/glfw-3.0.4/deps/getopt.c" > CMakeFiles/heightmap.dir/__/deps/getopt.c.i

glfw-3.0.4/examples/CMakeFiles/heightmap.dir/__/deps/getopt.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/heightmap.dir/__/deps/getopt.c.s"
	cd "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1/build/glfw-3.0.4/examples" && /usr/bin/cc  $(C_DEFINES) $(C_FLAGS) -S "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1/glfw-3.0.4/deps/getopt.c" -o CMakeFiles/heightmap.dir/__/deps/getopt.c.s

glfw-3.0.4/examples/CMakeFiles/heightmap.dir/__/deps/getopt.c.o.requires:
.PHONY : glfw-3.0.4/examples/CMakeFiles/heightmap.dir/__/deps/getopt.c.o.requires

glfw-3.0.4/examples/CMakeFiles/heightmap.dir/__/deps/getopt.c.o.provides: glfw-3.0.4/examples/CMakeFiles/heightmap.dir/__/deps/getopt.c.o.requires
	$(MAKE) -f glfw-3.0.4/examples/CMakeFiles/heightmap.dir/build.make glfw-3.0.4/examples/CMakeFiles/heightmap.dir/__/deps/getopt.c.o.provides.build
.PHONY : glfw-3.0.4/examples/CMakeFiles/heightmap.dir/__/deps/getopt.c.o.provides

glfw-3.0.4/examples/CMakeFiles/heightmap.dir/__/deps/getopt.c.o.provides.build: glfw-3.0.4/examples/CMakeFiles/heightmap.dir/__/deps/getopt.c.o

# Object files for target heightmap
heightmap_OBJECTS = \
"CMakeFiles/heightmap.dir/heightmap.c.o" \
"CMakeFiles/heightmap.dir/__/deps/getopt.c.o"

# External object files for target heightmap
heightmap_EXTERNAL_OBJECTS =

glfw-3.0.4/examples/heightmap: glfw-3.0.4/examples/CMakeFiles/heightmap.dir/heightmap.c.o
glfw-3.0.4/examples/heightmap: glfw-3.0.4/examples/CMakeFiles/heightmap.dir/__/deps/getopt.c.o
glfw-3.0.4/examples/heightmap: glfw-3.0.4/examples/CMakeFiles/heightmap.dir/build.make
glfw-3.0.4/examples/heightmap: glfw-3.0.4/src/libglfw3.a
glfw-3.0.4/examples/heightmap: /usr/lib/x86_64-linux-gnu/libGLU.so
glfw-3.0.4/examples/heightmap: /usr/lib/x86_64-linux-gnu/libX11.so
glfw-3.0.4/examples/heightmap: /usr/lib/x86_64-linux-gnu/libXrandr.so
glfw-3.0.4/examples/heightmap: /usr/lib/x86_64-linux-gnu/libXi.so
glfw-3.0.4/examples/heightmap: /usr/lib/x86_64-linux-gnu/libXxf86vm.so
glfw-3.0.4/examples/heightmap: /usr/lib/x86_64-linux-gnu/librt.so
glfw-3.0.4/examples/heightmap: /usr/lib/x86_64-linux-gnu/libm.so
glfw-3.0.4/examples/heightmap: /usr/lib/x86_64-linux-gnu/libGL.so
glfw-3.0.4/examples/heightmap: glfw-3.0.4/examples/CMakeFiles/heightmap.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --red --bold "Linking C executable heightmap"
	cd "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1/build/glfw-3.0.4/examples" && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/heightmap.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
glfw-3.0.4/examples/CMakeFiles/heightmap.dir/build: glfw-3.0.4/examples/heightmap
.PHONY : glfw-3.0.4/examples/CMakeFiles/heightmap.dir/build

glfw-3.0.4/examples/CMakeFiles/heightmap.dir/requires: glfw-3.0.4/examples/CMakeFiles/heightmap.dir/heightmap.c.o.requires
glfw-3.0.4/examples/CMakeFiles/heightmap.dir/requires: glfw-3.0.4/examples/CMakeFiles/heightmap.dir/__/deps/getopt.c.o.requires
.PHONY : glfw-3.0.4/examples/CMakeFiles/heightmap.dir/requires

glfw-3.0.4/examples/CMakeFiles/heightmap.dir/clean:
	cd "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1/build/glfw-3.0.4/examples" && $(CMAKE_COMMAND) -P CMakeFiles/heightmap.dir/cmake_clean.cmake
.PHONY : glfw-3.0.4/examples/CMakeFiles/heightmap.dir/clean

glfw-3.0.4/examples/CMakeFiles/heightmap.dir/depend:
	cd "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1/build" && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1" "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1/glfw-3.0.4/examples" "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1/build" "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1/build/glfw-3.0.4/examples" "/home/braba006/Documents/5721/lab1(openGL)/OpenGL_Lab1/build/glfw-3.0.4/examples/CMakeFiles/heightmap.dir/DependInfo.cmake" --color=$(COLOR)
.PHONY : glfw-3.0.4/examples/CMakeFiles/heightmap.dir/depend

