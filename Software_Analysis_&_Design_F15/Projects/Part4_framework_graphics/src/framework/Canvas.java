/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framework;


import javax.swing.Timer;
import javax.swing.JComponent;
import java.awt.Graphics;

/**
 * A class that creates a canvas for representing current state. 
 * @author Peter Braband
 */
public abstract class Canvas extends JComponent {
    
    /** Sets the current state of the canvas to the initial state passed in.
     * 
     * @param initState The initial state of the problem. 
     */
    public Canvas(State initState) {
        curState = initState;
        prevState = initState;
        mainTimer = new Timer(10, null);
    }
    
    /**
     * Abstract method for painting the graphics.
     * @param g
     */
    public abstract void paintComponent(Graphics g);
    
    public abstract void rePainter();

    /**
     * @return the curState
     */
    public State getCurState() {
        return curState;
    }

    /**
     * @param newState the curState to set
     */
    public void setCurState(State newState) {
        this.prevState = curState;
        this.curState = newState;
    }
    
    
        /**
     * Represents the current state of the problem.
     */
    private State curState;
    
    /**
     * Represents the current state of the problem.
     */
    private State prevState;
    
    /**
     * Holds the current timer of the canvas for stopping.
     */
    private Timer mainTimer;

    /**
     * @return the prevState
     */
    public State getPrevState() {
        return prevState;
    }
    
     /**
     * Method that stops timer.  Abstract must be overriden
     */
    public abstract void stopTimer();
    
    /**
     * Method that returns the timer.
     */
    public Timer getMainTimer() {
        return mainTimer;
    }
}
