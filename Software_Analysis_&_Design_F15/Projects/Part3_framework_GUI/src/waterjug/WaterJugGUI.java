/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waterjug;

import framework.GUI;
import javax.swing.JFrame;

/**
 *
 * @author Peter Braband
 */
public class WaterJugGUI extends JFrame {
    public WaterJugGUI() {
        add(new GUI(new WaterJugProblem()));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    /**
     * This method launches the gui.
     * @param args ignored
     */
    public static void main(String[] args) {
        new WaterJugGUI();
    }
}
