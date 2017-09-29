package layout_management;

import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class will give you practice creating and laying out containers.
 * @author Peter Braband #3418868
 */
public class LayoutManagement extends JComponent {
    
    public LayoutManagement() {
        FlowLayout mainLayout = new FlowLayout(0,20,0);
        mainLayout.setAlignment(FlowLayout.CENTER);
        setLayout(mainLayout);
        
        final JLabel message = new JLabel("Default Message");
        
        JPanel pane1 = new JPanel();
        JPanel pane2 = new JPanel();
        JPanel pane3 = new JPanel();
        
        GridLayout myGrid = new GridLayout(0,1);
        myGrid.setVgap(5);
        pane1.setLayout(myGrid);
        pane3.setLayout(myGrid);
        
        final JLabel borderedBox1 = new JLabel("");
        borderedBox1.setBackground(Color.PINK);
        borderedBox1.setBorder(BorderFactory.createTitledBorder("Message 1"));
        borderedBox1.setOpaque(true);
        borderedBox1.setPreferredSize(new Dimension(125,50));
        
        final JLabel borderedBox2 = new JLabel("");
        borderedBox2.setBackground(Color.PINK);
        borderedBox2.setBorder(BorderFactory.createTitledBorder("Message 2"));
        borderedBox2.setOpaque(true);
        borderedBox2.setPreferredSize(new Dimension(125,50));
        
        
        String[] labels = "This class will give you practice creating and laying out containers".split("\\s");
        int count = 0;
        for (final String label : labels) {
            count++;
            if(count <= 6)
            {
                JButton button = new JButton(label);
                button.setBackground(Color.CYAN);
                button.setPreferredSize(new Dimension(125,50) );
                button.addActionListener(new
                    ActionListener(){
                        public void actionPerformed(ActionEvent e)
                        {
                            borderedBox1.setText(label);
                        }
                    });
                pane1.add(button);
            }
            else
            {
                JButton button = new JButton(label);
                button.setBackground(Color.CYAN);
                button.setPreferredSize(new Dimension(125,50));
                button.addActionListener(new
                    ActionListener()
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                borderedBox2.setText(label);
                            }
                        });
                pane3.add(button);
            }
            
        }
        
        pane2.setLayout(myGrid);
        pane2.add(borderedBox1);
        pane2.add(borderedBox2);
        
        add(pane1);
        add(pane2);
        add(pane3);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
    }

    /**
     * @param args the command line arguments -- not used
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Layout Management Lab");
        frame.add(new LayoutManagement());
        
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
