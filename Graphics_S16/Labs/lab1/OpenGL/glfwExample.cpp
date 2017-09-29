#include <cstdlib>
#include <iostream>
#include <vector>
#include <unistd.h>
#include <GL/glew.h>
#include <GLFW/glfw3.h>

int CheckGLErrors(const char *s)
{
    int errCount = 0;
    
    return errCount;
}

int main(void)
{
    /* Initialize the library */
    if (!glfwInit())
        exit (-1);
    // throw std::runtime_error("Error! initialization of glfw failed!");

    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
    glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

    /* Create a windowed mode window and its OpenGL context */
    int winWidth = 1200;
    int winHeight = 1200;
    float aspectRatio = winWidth / (float)winHeight;
    
    GLFWwindow* window = glfwCreateWindow(winWidth, winHeight, "Lab 1 Example", NULL, NULL);
    if (!window) {
        std::cerr << "GLFW did not create a window!" << std::endl;
        
        glfwTerminate();
        return -1;
    }

    /* Make the window's context current */
    glfwMakeContextCurrent(window);

    glewExperimental = GL_TRUE;
    GLenum err=glewInit();
    if(err != GLEW_OK) {
        std::cerr <<"GLEW Error! glewInit failed, exiting."<< std::endl;
        exit(EXIT_FAILURE);
    }

    const GLubyte* renderer = glGetString (GL_RENDERER);
    const GLubyte* version = glGetString (GL_VERSION);
    std::cout << "Renderer: " << renderer << std::endl;
    std::cout << "OpenGL version supported: " << version << std::endl;

    glEnable(GL_DEPTH_TEST);
    glDepthFunc(GL_LESS);
    glClearColor(0.0, 0.7, 1.0, 1.0);

    int fb_width, fb_height;
    glfwGetFramebufferSize(window, &fb_width, &fb_height);
    glViewport(0, 0, fb_width, fb_height);
 
	double red = 0.0;
	double green = 0.0;
	double blue = 0.0;
	int i = 0;
	int j = 0;
    /* Loop until the user closes the window */
    while (!glfwWindowShouldClose(window))
    {
	
	if(i == 0){

	if (j<100){//red
	red+=.01;
	}
	if(j>=100 && j<200){//orange
	green+=.01;
	}
	if(j>=200 && j<300){//green
	red-=.01;
	}
	if(j>=300 && j<400){//cyan
	blue+=.01;
	}
	if(j>=400 && j<500){//blue
	green-=.01;
	}
	if(j>=500 && j<600){//white
	red+=.01;
	green+=.01;
	}
	if(j>=600 && j<700){//black
	red-=.01;
	green-=.01;
	blue-=.01;
	}
	
	j = j % 700;
	
	glClearColor(red, green, blue, 1.0);


    	red = ((double) rand() / (RAND_MAX));
	green =((double) rand() /(RAND_MAX));
	blue = ((double) rand()/ (RAND_MAX));
	glClearColor(red, green, blue, 1.0);
        /* Render here */
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	j++;

	}

        /* Swap front and back buffers */
        glfwSwapBuffers(window);

        /* Poll for and process events */
        glfwPollEvents();
        if (glfwGetKey( window, GLFW_KEY_ESCAPE ) == GLFW_PRESS)
            glfwSetWindowShouldClose(window, 1);

	sleep(1);

    }
  
    glfwTerminate();
    return 0;
}

