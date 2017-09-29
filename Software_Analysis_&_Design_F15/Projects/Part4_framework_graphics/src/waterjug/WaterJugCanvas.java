/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package waterjug;
import framework.Canvas;
import framework.State;
import java.awt.BasicStroke;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;


/**
 *
 * @author Peter Braband
 */
public class WaterJugCanvas extends Canvas {
    
    /**
     * Constructor uses super to pass initState to canvas constructor.
     * Constructs the WaterJugState.
     * @param initState holds the initial WaterJugState.
     */
    public WaterJugCanvas (State initState) {
        super (initState);
        this.setPreferredSize(new Dimension(300,300));
        t = getMainTimer();
        
        FlowLayout mainLayout = new FlowLayout();
        mainLayout.setHgap(XSCALE);
        
        thisState = (WaterJugState) getCurState();
        this.setLayout(mainLayout);
        
        JLabel xHeader = new JLabel();
        xHeader.setText("Jug X");
        add(xHeader);
        
        JLabel yHeader = new JLabel();
        yHeader.setText("Jug Y");
        add(yHeader);
        
        repaint();
    }
    
    /**
     * Draws the current water jug state.
     * @param g graphics object.
     */
    @Override
    public void paintComponent(Graphics g) {
        thisState = (WaterJugState) getCurState();
        prevState = (WaterJugState) getPrevState();
        Graphics2D g2 = (Graphics2D) g;
        
        
        int strokeSize = 8;
        
        g2.setStroke(new BasicStroke(strokeSize));
        
        
        //Draw lines for the buckets
        //X Bucket
        g2.draw(new Line2D.Double(XSTART,YSTART,XSTART,YSTART - YSCALE * 3));
        g2.draw(new Line2D.Double(XSTART + XSCALE,YSTART,
                XSTART + XSCALE,YSTART - YSCALE * 3));
        g2.draw(new Line2D.Double(XSTART,YSTART,XSTART + XSCALE,YSTART));
        
        //Y Bucket        
        g2.draw(new Line2D.Double(xNewStart,YSTART,xNewStart,YSTART - YSCALE * 4));
        g2.draw(new Line2D.Double(xNewStart + XSCALE,YSTART,
                xNewStart + XSCALE,YSTART - YSCALE * 4));
        g2.draw(new Line2D.Double(xNewStart,YSTART,xNewStart + XSCALE,YSTART));
        
                
        //Water Fill
        Integer xHeight = thisState.getJugAmount(0);
        Integer yHeight = thisState.getJugAmount(1);
        


        
        g2.setColor((Color.BLUE));
        
        
        
        int jxCurHeight = 0;
        int jxScaler = 0;
        int jyCurHeight = 0;
        int jyScaler = 0;
        boolean xDoneAnim = false;
        boolean yDoneAnim = false;
        
        
        if (xHeight != prevState.getJugAmount(0)) {
            
        
        if (xHeight >= prevState.getJugAmount(0)) {
            jxCurHeight = YSTART-YSCALE*prevState.getJugAmount(0) -  timeY - strokeSize/2;
            //jxCurHeight = prevState.getJugAmount(0) + YSTART - timeY - strokeSize/2;
            jxScaler = timeY;
            if (jxCurHeight <= YSTART-YSCALE*xHeight) xDoneAnim = true;
        }
        else
        {
            jxCurHeight = YSTART-YSCALE*prevState.getJugAmount(0) +  timeY - strokeSize/2;
            jxScaler = YSCALE*prevState.getJugAmount(0) - timeY;
            if (jxScaler <= YSCALE*xHeight) xDoneAnim = true;
        }

        if (!xDoneAnim) {
            g2.fill(new Rectangle2D.Double(XSTART + strokeSize/2,
                    jxCurHeight,
                    XSCALE-strokeSize,
                    jxScaler));
        }
        else {
                 g2.fill(new Rectangle2D.Double(XSTART + strokeSize/2,
                         YSTART-YSCALE*xHeight,
                         XSCALE-strokeSize,
                         YSCALE*xHeight));            
        }
        }
        else {
                 g2.fill(new Rectangle2D.Double(XSTART + strokeSize/2,
                         YSTART-YSCALE*xHeight,
                         XSCALE-strokeSize,
                         YSCALE*xHeight));  
           xDoneAnim = true;            
        }
            
        
       if (yHeight != prevState.getJugAmount(1))
       {
            if (yHeight >= prevState.getJugAmount(1)) {
                 jyCurHeight = YSTART-YSCALE*prevState.getJugAmount(1) -  timeY - strokeSize/2;
                 jyScaler = timeY;
                 if (jyCurHeight <= YSTART-YSCALE*yHeight) yDoneAnim = true;
             }
             else
             {
                 jyCurHeight = YSTART-YSCALE*prevState.getJugAmount(1) +  timeY - strokeSize/2;
                 jyScaler = YSCALE*prevState.getJugAmount(1) - timeY;
                 if (jyScaler <= YSCALE*yHeight) yDoneAnim = true;
             } 

             if (!yDoneAnim) {
                 g2.fill(new Rectangle2D.Double(xNewStart + strokeSize/2,
                         jyCurHeight,
                         XSCALE-strokeSize,
                         jyScaler));
             }
             else
             {
                 g2.fill(new Rectangle2D.Double(xNewStart + strokeSize/2,
                         YSTART-YSCALE*yHeight,
                         XSCALE-strokeSize,
                         YSCALE*yHeight));
             }
       }
       else {
           g2.fill(new Rectangle2D.Double(xNewStart + strokeSize/2,
              YSTART-YSCALE*yHeight,
              XSCALE-strokeSize,
              YSCALE*yHeight));
           yDoneAnim = true;
       }
        
        
        doneAnim = xDoneAnim && yDoneAnim;
        
        
        //Print the white current heights
        if (doneAnim) {
            g2.setColor(Color.WHITE);        
            g2.setFont(new Font("Sanserif", Font.BOLD,32));
            g2.drawString(xHeight.toString(), XSTART+XSCALE/2-7, YSTART - 16);
            g2.drawString(yHeight.toString(), xNewStart+XSCALE/2-7, YSTART - 16);
 
            
        }
        
        
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("WaterJugCanvas Test");
        frame.add(new WaterJugCanvas(new WaterJugState(2,1)));
        frame.setPreferredSize(new Dimension(300,300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    @Override
    public void rePainter() {
        doneAnim = false;
        t = new Timer(10, null);
        t.setCoalesce(false);
        t.addActionListener(new 
          ActionListener()
          {
          public void actionPerformed(ActionEvent event)
          {
             setTimeY(timeY+10);   

             if (doneAnim) {
             
             }
             else repaint();

          }
         });
        
        t.start();
        setTimeY(0);

    }
   

     /**
     * @param thisState the thisState to set
     */
    public void setThisState(WaterJugState thisState) {
        this.thisState = thisState;
    }
    
    /**
     * @return the thisState
     */
    public WaterJugState getThisState() {
        return thisState;
    }

    /**
     * @return the timeY
     */
    public int getTimeY() {
        return timeY;
    }

    /**
     * @param timeY the timeY to set
     */
    public void setTimeY(int timeY) {
        this.timeY = timeY;
    }
    
    /**
     * Override the stoptimer to stop this timer.
     */
    public void stopTimer() {
        t.stop();
        setTimeY(0);
    }

    
    
    
    /**
     * Contains a WaterJugState representation of the current state.
     */
    private WaterJugState thisState;
     /**
     * Contains a WaterJugState representation of the current state.
     */
    private WaterJugState prevState;


    private final int XSTART = 25;
    private final int YSTART = 250;
    private final int YSCALE = 50;
    private final int XSCALE = 100;
    private final int XBUCKETGAP = 40;
    private final int xNewStart = XSTART + XBUCKETGAP + XSCALE;
    private int timeY = 0;
    private boolean doneAnim = true;
    private Timer t;
    
    
    //PRIVATE METHOD SECTION
    //-------------------------


    

    
}
