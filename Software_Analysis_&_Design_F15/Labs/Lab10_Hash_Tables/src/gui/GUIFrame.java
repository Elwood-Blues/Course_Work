/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;

/**
 *
 * @author tcolburn
 */
public class GUIFrame extends JFrame {

    public GUIFrame() {

        super("Hash Table Tester");
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(350,450));

        add(new GUIPanel());

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new GUIFrame();
    }

}
