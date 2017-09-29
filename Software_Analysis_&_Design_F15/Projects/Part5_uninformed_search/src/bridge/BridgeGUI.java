/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridge;

import framework.GUI;
import javax.swing.JFrame;

/**
 *
 * @author Peter Braband
 */
public class BridgeGUI extends JFrame {
    public BridgeGUI() {
        BridgeProblem ourProblem = new BridgeProblem();
        add(new GUI(ourProblem, new BridgeCanvas(ourProblem.getCurrentState())));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    /**
     * This method launches the gui.
     * @param args ignored
     */
    public static void main(String[] args) {
        new BridgeGUI();
    }
    
}
