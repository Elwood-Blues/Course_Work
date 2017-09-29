/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocks;

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
import java.awt.geom.Line2D;
import java.awt.Color;
import java.util.Stack;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author Peter
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class BlockCanvas extends Canvas {
           /**
     * Constructor uses super to pass initState to canvas constructor.
     * Constructs the WaterJugState.
     * @param initState holds the initial WaterJugState.
     */
    public BlockCanvas (State initState) {
        super (initState);
        this.setPreferredSize(new Dimension(300,300));
        this.setMaxSize(300);
        
        FlowLayout mainLayout = new FlowLayout();
        mainLayout.setHgap(XSCALE);
        
        thisState = (BlockState) getCurState();
        this.setLayout(mainLayout);
        
        
        repaint();
    }
    
     /**
     * Constructor uses super to pass initState to canvas constructor.
     * Constructs the WaterJugState.
     * @param initState holds the initial WaterJugState.
     */
    public BlockCanvas (State initState, int scale) {
        super (initState);
        this.setMaxSize(scale);
        this.setPreferredSize(new Dimension((int)super.getMaxSize(),(int)super.getMaxSize()));
        
        FlowLayout mainLayout = new FlowLayout();
        mainLayout.setHgap(XSCALE);
        
        thisState = (BlockState) getCurState();
        this.setLayout(mainLayout);
        
        
        repaint();
    }
    
    /**
     * Draws the current water jug state.
     * @param Graphics graphics object.
     */
    @Override
    public void paintComponent(Graphics g) {
        thisState = (BlockState) getCurState();

        Graphics2D g2 = (Graphics2D) g;
        g2.scale(super.getMaxSize() / 300, super.getMaxSize() / 300);
        
        
        int strokeSize = 8;
        
        g2.setStroke(new BasicStroke(strokeSize));
        
        g2.setColor(new Color(50,0,50));
        
        g2.fill(new Rectangle2D.Float(0,0,300,300));
        
        g2.setColor(new Color(200,200,200));
        g2.draw(new Line2D.Double(XSTART,YSTART,300 - XSTART,YSTART));

        g2.setColor(new Color(255,255,255));
        g2.setFont(new Font("Sanserif", Font.BOLD,26));
        g2.drawString("p", (300-XSTART)/6+5, YSTART +30);
        g2.drawString("q", (300-XSTART)/2+5, YSTART +30);
        g2.drawString("r", (300-XSTART)/6*5+5, YSTART +30);
        
        strokeSize /= 3;
        g2.setStroke(new BasicStroke(strokeSize));
        
        for(int i = 0; i < thisState.getP().size(); i++) {
            g2.setColor(new Color(0,0,80));
            g2.fill(new Rectangle2D.Double((300-XSTART)/6+10-XSCALE/2+strokeSize/2,
                    YSTART-((i+1)*YSCALE)-5+strokeSize/2
                    ,XSCALE-strokeSize,YSCALE-strokeSize));
            
            g2.setColor(new Color(255,255,255));
            g2.draw(new Rectangle2D.Double((300-XSTART)/6+10-XSCALE/2,
                    YSTART-((i+1)*YSCALE)-5,XSCALE,YSCALE));
            
            g2.drawString(thisState.getP().get(i).toString(),
                    (300-XSTART)/6+1,
                    YSTART-strokeSize*3-YSCALE/4-(i*YSCALE));
                      
        }
        
        for(int i = 0; i < thisState.getQ().size(); i++) {
            g2.setColor(new Color(0,0,80));
            g2.fill(new Rectangle2D.Double((300-XSTART)/2+10-XSCALE/2+strokeSize/2,
                    YSTART-((i+1)*YSCALE)-5+strokeSize/2
                    ,XSCALE-strokeSize,YSCALE-strokeSize));
            
            g2.setColor(new Color(255,255,255));
            g2.draw(new Rectangle2D.Double((300-XSTART)/2+10-XSCALE/2,
                    YSTART-((i+1)*YSCALE)-5,XSCALE,YSCALE));
            
            g2.drawString(thisState.getQ().get(i).toString(),
                    (300-XSTART)/2+1,
                    YSTART-strokeSize*3-YSCALE/4-(i*YSCALE));
                      
        }
        
        for(int i = 0; i < thisState.getR().size(); i++) {
            g2.setColor(new Color(0,0,80));
            g2.fill(new Rectangle2D.Double((300-XSTART)/6*5+10-XSCALE/2+strokeSize/2,
                    YSTART-((i+1)*YSCALE)-5+strokeSize/2
                    ,XSCALE-strokeSize,YSCALE-strokeSize));
            
            g2.setColor(new Color(255,255,255));
            g2.draw(new Rectangle2D.Double((300-XSTART)/6*5+10-XSCALE/2,
                    YSTART-((i+1)*YSCALE)-5,XSCALE,YSCALE));
            
            g2.drawString(thisState.getR().get(i).toString(),
                    (300-XSTART)/6*5+1,
                    YSTART-strokeSize*3-YSCALE/4-(i*YSCALE));
                      
        }

   
    }
    
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("BlockCanvas Test");
//        JPanel panel = new JPanel();
//        panel.setBorder(BorderFactory.createTitledBorder("Test"));
//        panel.setPreferredSize(new Dimension(300,300));
//
//        Stack pStack = new Stack<Character>();
//        Stack qStack = new Stack<Character>();
//        Stack rStack = new Stack<Character>();
//        pStack.add('A'); pStack.add('B'); pStack.add('C');
//        qStack.add('D'); qStack.add('E'); qStack.add('F');
//        rStack.add('G'); rStack.add('H'); rStack.add('I');
//        pStack.add('J'); pStack.add('K');
//        
//        panel.add(new BlockCanvas(new BlockState(pStack,
//            qStack,
//            rStack)));
//        frame.add(panel);
//        frame.setPreferredSize(new Dimension(400,400));
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }
    
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
    public void setThisState(BlockState thisState) {
        this.thisState = thisState;
    }
    
    /**
     * @return the thisState
     */
    public BlockState getThisState() {
        return thisState;
    }



    
    
    
    /**
     * Contains a WaterJugState representation of the current state.
     */
    private BlockState thisState;
     /**
     * Contains a WaterJugState representation of the current state.
     */
    private BlockState prevState;


    private final int XSTART = 20;
    private final int YSTART = 250;
    private final int YSCALE = 40;
    private final int XSCALE = 40;
    private final int XYGAP = 40;
    
    
    //PRIVATE METHOD SECTION
    //-------------------------
}
