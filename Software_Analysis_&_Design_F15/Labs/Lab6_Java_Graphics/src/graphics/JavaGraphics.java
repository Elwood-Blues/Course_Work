package graphics;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class will give you practice with Java graphics.
 * It is complete except for the slider's change listener and the
 * button's action listener.
 * Note that it makes use of two inner classes.
 * @author Peter Braband
 */
public class JavaGraphics extends JComponent {

    /**
     * Creates the overall component that contains the arc panel and mouse
     * panel laid out vertically.
     */
    public JavaGraphics() {
        
        ArcPanel arcPanel = new ArcPanel();
        MousePanel mousePanel = new MousePanel();
        
        setLayout(new GridLayout(2, 1, 0, 20));
        add(arcPanel);
        add(mousePanel);
    }
    
    /**
     * An inner class responsible for the arc panel, including the arc canvas
     * and the associated slider.
     */
    private class ArcPanel extends JPanel {
        
        /**
         * You must complete this constructor by giving the slider a 
         * change listener.
         */
        public ArcPanel() {
            slider = new JSlider(0, 360);
            slider.setValue(0);
            slider.setBorder(new TitledBorder("Move the slider:"));
            arcCanvas = new ArcCanvas(slider.getValue());
            
            // Give the slider a change listener here
            slider.addChangeListener(new
                    ChangeListener()
                    {
                        public void stateChanged(ChangeEvent e)
                        {
                            arcCanvas.setArcExtent(slider.getValue() );
                            arcCanvas.repaint();
                        }
                    });
            
            add(arcCanvas);
            add(slider);
        }
        
        private ArcCanvas arcCanvas;
        private JSlider slider;
    }
    
    /**
     * An inner class responsible for the mouse panel, including the mouse
     * canvas and the associated reset button.
     */
    private class MousePanel extends JPanel {
        
        /**
         * You must complete this constructor by giving the button an
         * action listener.
         */
        public MousePanel() {
            
            mouseCanvas = new MouseCanvas();
            button = new JButton("RESET");
            
            // Give the button an action listener here
            button.addActionListener(new
                    ActionListener() 
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                            mouseCanvas.setConnected(false);
                        }
                    });
            
            JPanel panel = new JPanel();
            
            panel.add(mouseCanvas);
            panel.add(button);
            panel.setBorder(new TitledBorder("Click and drag from left circle to right:"));
            
            add(panel);
        }
        
        private MouseCanvas mouseCanvas;
        private JButton button;
    }
    
    /**
     * Launches the graphics demonstration in a frame
     * @param args the command line arguments -- not used
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Java Graphics Demonstration");
        frame.add(new JavaGraphics());
        
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
