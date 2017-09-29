package listeners;

import java.awt.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.border.TitledBorder;

/**
 *  This class will give you practice writing GUI components and listeners.
 * @author Peter Braband
 */
public class Listeners {
    
    public Listeners() {
        JFrame frame = new JFrame("Listeners Lab");
        frame.setPreferredSize(new Dimension(450, 150));
        frame.setLayout(new FlowLayout());
        
        // Stop/Go button here
        final JButton stopGo = new JButton("Stop!");
        
        Font ButtonFont = new Font("sans-serif", Font.BOLD, 20);
        stopGo.setFont(ButtonFont);
        stopGo.setPreferredSize(new Dimension(100, 50));
        stopGo.setBackground(Color.RED);
        frame.add(stopGo);
        
        stopGo.addActionListener(new
            ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    if(stopGo.getText().equals("Stop!"))
                    {
                        stopGo.setBackground(Color.GREEN);
                        stopGo.setText("Go!");
                    }
                    else
                    {
                        stopGo.setBackground(Color.RED);
                        stopGo.setText("Stop!");
                    }
                            
                
                }
            });
        
        // Slider and label here
        final JSlider aSlider = new JSlider();
        final JLabel aLabel = new JLabel();
        final StringBuilder sliderLabel = new StringBuilder();
        
        sliderLabel.append(aSlider.getValue() );
        aLabel.setText(sliderLabel.toString() );
        aLabel.setPreferredSize(new Dimension(60,50));
        aLabel.setFont(new Font("san-seriff",Font.BOLD,20));
        aLabel.setForeground(Color.BLUE);
        aLabel.setBorder(new TitledBorder("Value"));
        
        aSlider.addChangeListener(new
                ChangeListener()
                {
                    public void stateChanged(ChangeEvent event)
                    {
                        sliderLabel.delete(0,3);
                        sliderLabel.append(aSlider.getValue() );
                        aLabel.setText(sliderLabel.toString() );
                    }
                });
        frame.add(aSlider);
        frame.add(aLabel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * @param args the command line arguments -- not used
     */
    public static void main(String[] args) {
        new Listeners();
    }
}
