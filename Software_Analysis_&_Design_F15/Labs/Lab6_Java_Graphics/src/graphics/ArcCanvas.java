package graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;

/**
 * This class draws the ellipse and arc combination that is controlled by the slider.
 * @author Your name here
 */
public class ArcCanvas extends JComponent {
   
    /**
     * Creates the ellipse and arc combination.
     * @param initialArcExtent the initial extent of the arc, in degrees
     */
    public ArcCanvas(int initialArcExtent) {
        setPreferredSize(new Dimension(220, 140));
        // You must provide
        aEllipse = new Ellipse2D.Double(10.0, 20.0, 200.0, 100.0);
        aArc = new Arc2D.Double(10.0, 20.0, 200.0, 100.0, 0.0, initialArcExtent, Arc2D.PIE);
    }
    
    /**
     * Draws the ellipse and arc combination.
     * @param g the graphics context object
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        // You must provide
        g2.setColor(Color.RED);
        g2.fill(aArc);
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(5) );
        g2.draw(aEllipse);
    }
    
    /**
     * Draws the shapes present
     * @param arcLength - the extent to draw the arc
     */
    public void setArcExtent(int arcLength)
    {
        this.aArc.extent = arcLength;
    }
    
    //Private variables
    private Arc2D.Double aArc;
    private Ellipse2D.Double aEllipse;
    
}
