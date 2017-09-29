/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package demo;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;

/**
 *
 * @author tcolburn
 */
public class DemoFrame extends JFrame {

    public DemoFrame() {

        super("Hash Function Demonstration");
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(500,450));

        add(new DemoPanel());

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new DemoFrame();
    }

}
