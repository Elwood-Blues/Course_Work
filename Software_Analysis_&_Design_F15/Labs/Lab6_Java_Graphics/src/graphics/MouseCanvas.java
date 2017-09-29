package graphics;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.JComponent;

/**
 * This class draws the component with two circles and gives it a mouse
 * listener and a mouse motion listener.
 * @author Your name here
 */
public class MouseCanvas extends JComponent {
    
    /**
     * Creates the component with two circles and gives it a mouse
     * listener and a mouse motion listener.
     */
    public MouseCanvas() {
        setPreferredSize(new Dimension(220, 70));
        circle1 = new Ellipse2D.Double(20.0,10.0,50.0,50.0);
        circle2 = new Ellipse2D.Double(circle1.getX()+100,circle1.getY(),50.0,50.0);
        mouseStart = new Point();
        
        mouseEnd = new Point();
        
        connector = new Line2D.Double(mouseStart,mouseEnd);
              
        
        this.addMouseListener(new MouseAdapter() {
           public void mousePressed(MouseEvent e)
           {
               if (circle1.contains(e.getPoint())) mouseStart = e.getPoint();
               repaint();
           }
        });
        
        this.addMouseMotionListener(new MouseMotionAdapter() {
           public void mouseDragged(MouseEvent e)
           {
               mouseEnd = e.getPoint();
               connector.setLine(mouseStart, mouseEnd);
               
               repaint();
           }
        });
        
        this.addMouseListener(new MouseAdapter() {
           public void mouseReleased(MouseEvent e)
           {      
               if (circle2.contains(mouseEnd) && circle1.contains(mouseStart))
                   connected = true;
               connector.setLine(new Point.Double(0,0), new Point.Double(0,0));
               mouseStart.setLocation(new Point(0,0));
               mouseEnd.setLocation(new Point(0,0));
               repaint();
           }
        });
        
        // You must provide
    }
    
    /**
     * Draws the two circles and, if required, the rubber band and 
     * connecting edge.
     * @param g the graphics context object
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.draw(circle1);
        g2.draw(circle2);
        
        if (connector.getX1() != 0 && !connected) g2.draw(connector);
        
        if (connected) g2.draw(new Line2D.Double(
                new Point.Double(circle1.getMaxX(),circle1.getCenterY()), 
                new Point.Double(circle2.getMinX(),circle2.getCenterY())));
        // You must provide
    }
    
    public void setConnected(boolean boo)
    {
        connected = boo;
        repaint();
    }
    
    private final Ellipse2D.Double circle1;
    private final Ellipse2D.Double circle2;
    private final Line2D.Double connector;
    private Point mouseStart;
    private Point mouseEnd;
    private boolean connected;
}
