/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;
import framework.Canvas;
import framework.State;
import java.awt.BasicStroke;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;

/**
 *
 * @author Peter
 */
public class PuzzleCanvas extends Canvas{
       /**
     * Constructor uses super to pass initState to canvas constructor.
     * Constructs the WaterJugState.
     * @param initState holds the initial WaterJugState.
     */
    public PuzzleCanvas (State initState) {
        super (initState);
        this.setPreferredSize(new Dimension(300,300));
        
        FlowLayout mainLayout = new FlowLayout();
        mainLayout.setHgap(XSCALE);
        
        thisState = (PuzzleState) getCurState();
        this.setLayout(mainLayout);
        
        
        repaint();
    }
    
    /**
     * Draws the current water jug state.
     * @param Graphics graphics object.
     */
    @Override
    public void paintComponent(Graphics g) {
        thisState = (PuzzleState) getCurState();

        Graphics2D g2 = (Graphics2D) g;
        
        
        int strokeSize = 8;
        
        g2.setStroke(new BasicStroke(strokeSize));
        
        g2.draw(new Rectangle2D.Double(XSTART,YSTART,XSCALE,YSCALE));
        g2.draw(new Rectangle2D.Double(XSTART+XSCALE,YSTART,XSCALE,YSCALE));
        g2.draw(new Rectangle2D.Double(XSTART+2*XSCALE,YSTART,XSCALE,YSCALE));
        g2.draw(new Rectangle2D.Double(XSTART,YSTART+YSCALE,XSCALE,YSCALE));
        g2.draw(new Rectangle2D.Double(XSTART+XSCALE,YSTART+YSCALE,XSCALE,YSCALE));
        g2.draw(new Rectangle2D.Double(XSTART+2*XSCALE,YSTART+YSCALE,XSCALE,YSCALE));
        g2.draw(new Rectangle2D.Double(XSTART,YSTART+YSCALE*2,XSCALE,YSCALE));
        g2.draw(new Rectangle2D.Double(XSTART+XSCALE,YSTART+YSCALE*2,XSCALE,YSCALE));
        g2.draw(new Rectangle2D.Double(XSTART+2*XSCALE,YSTART+YSCALE*2,XSCALE,YSCALE));

        g2.setFont(new Font("Sans-Serif",Font.BOLD,28));
        
        
        int txXStart = XSTART + XSCALE/2 - 8;
        int txYStart = YSTART + YSCALE/2 + 10;
        int bxXStart = XSTART + strokeSize/2;
        int bxYStart = YSTART + strokeSize/2;
        int bxScale = XSCALE - strokeSize;
        

        
        for(Integer i = 0; i < 9; i++) {
            int myInt = thisState.getPieceInSpace(i);
            Integer myInteger = myInt;
            String myString = myInteger.toString();
            
            if (myInt != 0) {
                g2.setColor(Color.BLUE);
                g2.fill(new Rectangle2D.Double(bxXStart + bxScale*(i%3)+strokeSize*(i%3),
                    bxYStart + bxScale*(i/3)+strokeSize*(i/3), bxScale, bxScale));
                
                g2.setColor(Color.WHITE);
                g2.drawString(myString, txXStart + ((i%3)*XSCALE), txYStart + ((i/3)*YSCALE));
            }
              
        }
   
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("WaterJugCanvas Test");
        frame.add(new PuzzleCanvas(new PuzzleState(3,0,1,4,5,6,7,8,2)));
        frame.setPreferredSize(new Dimension(300,300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    @Override
    public void rePainter() {
        repaint();

    }
   
    @Override
    public void stopTimer() {
        

    }    
    

     /**
     * @param thisState the thisState to set
     */
    public void setThisState(PuzzleState thisState) {
        this.thisState = thisState;
    }
    
    /**
     * @return the thisState
     */
    public PuzzleState getThisState() {
        return thisState;
    }



    
    
    
    /**
     * Contains a WaterJugState representation of the current state.
     */
    private PuzzleState thisState;
     /**
     * Contains a WaterJugState representation of the current state.
     */
    private PuzzleState prevState;


    private final int XSTART = 16;
    private final int YSTART = 4;
    private final int YSCALE = 85;
    private final int XSCALE = 85;
}
