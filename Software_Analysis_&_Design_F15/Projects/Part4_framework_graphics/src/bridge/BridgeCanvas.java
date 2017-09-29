/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bridge;
import framework.Canvas;
import framework.State;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;


/**
 *
 * @author Peter Braband
 */
public class BridgeCanvas extends Canvas{
        
    /**
     * Constructor uses super to pass initState to canvas constructor.
     * Constructs the WaterJugState.
     * @param initState holds the initial WaterJugState.
     */
    public BridgeCanvas (State initState) {
        super (initState);
        this.setPreferredSize(new Dimension(300,300));
        
        FlowLayout mainLayout = new FlowLayout();
        
        thisState = (BridgeState) getCurState();
        this.setLayout(mainLayout);
        
    }
    
    /**
     * Draws the current water jug state.
     * @param Graphics graphics object.
     */
    @Override
    public void paintComponent(Graphics g) {
        

        
        thisState = (BridgeState) getCurState();
        final Graphics2D g2 = (Graphics2D) g;
        
        g2.setColor(new Color(25,125,25));
        g2.fill(new Rectangle2D.Float(0,0,300,300));
        
        g2.setColor(new Color(25,25,200));
        g2.setStroke(new BasicStroke(50));
        g2.draw(new Arc2D.Double(135.0,-30.0,45.0,180.0,270.0,180.0,Arc2D.OPEN));
        g2.draw(new Arc2D.Double(135.0,140.0,45.0,170.0,90.0,180.0,Arc2D.OPEN));
        
        
        //Draw the Bridge
        g2.setColor(new Color(120,62,35));
        g2.fill(new Rectangle2D.Double(90,115,125,60));

        
        
        //Draw the Strings
        

       
        g2.setColor(Color.WHITE);        
        g2.setFont(new Font("Sanserif", Font.BOLD,32));
       

        if (thisState.getP1Position() == Position.WEST) {
            g2.drawString("P1", WESTSTART,YSTART + YSCALE*0);}
        else {g2.drawString("P1", EASTSTART,YSTART + YSCALE*0);}

        if (thisState.getP2Position() == Position.WEST) {
            g2.drawString("P2", WESTSTART,YSTART + YSCALE*1);}
        else {g2.drawString("P2", EASTSTART,YSTART + YSCALE*1);}

        if (thisState.getP5Position() == Position.WEST) {
            g2.drawString("P5", WESTSTART,YSTART + YSCALE*3);}
        else {g2.drawString("P5", EASTSTART,YSTART + YSCALE*3);}

        if (thisState.getP10Position() == Position.WEST) {
            g2.drawString("P10", WESTSTART,YSTART + YSCALE*4);}
        else {g2.drawString("P10", EASTSTART,YSTART + YSCALE*4);}


        g2.setFont(new Font("Sanserif", Font.BOLD,26));
        g2.drawString("Time: " + thisState.getTimeSoFar(), 100, 150);


        g2.setColor(new Color(255,255,175));
        if (thisState.getFlashlightPosition()== Position.WEST) {
            g2.drawString("F", WESTSTART,YSTART + YSCALE*2);}
        else {g2.drawString("F", EASTSTART,YSTART + YSCALE*2);}


    }
    
    @Override
    public void rePainter() {
        this.repaint();
    }

     /**
     * @param thisState the thisState to set
     */
    public void setThisState(BridgeState newState) {
        this.thisState = newState;
    }
    
    /**
     * @return the thisState
     */
    public BridgeState getThisState() {
        return thisState;
    }
    
     /**
     * Override the stoptimer to stop this timer.
     */
    public void stopTimer() {
        
    }


    
    /**
     * Contains a BridgeState representation of the current state.
     */
    private BridgeState thisState;


    
    
    //PRIVATE METHOD SECTION
    //-------------------------
    int YSCALE = 50;
    int WESTSTART = 20;
    int EASTSTART = 235;
    int YSTART = 50;
    
    int drawCurrent = WESTSTART;
    
}
