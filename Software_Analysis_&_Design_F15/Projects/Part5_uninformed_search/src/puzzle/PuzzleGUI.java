/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;
import framework.GUI;
import javax.swing.JFrame;

/**
 *
 * @author Peter
 */
public class PuzzleGUI extends JFrame {
    
    public PuzzleGUI(){
        PuzzleProblem ourProblem = new PuzzleProblem();
        add(new GUI(ourProblem, new PuzzleCanvas(ourProblem.getCurrentState())));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    public static void main(String[] args){
        new PuzzleGUI();
    }
}
