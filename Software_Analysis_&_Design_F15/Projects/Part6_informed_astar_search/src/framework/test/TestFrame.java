/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framework.test;

import bridge.BridgeProblem;
import framework.GUI;
import framework.ProblemPane;
import javax.swing.JFrame;
import waterjug.WaterJugProblem;
import waterjug.WaterJugCanvas;
import bridge.BridgeCanvas;
import puzzle.PuzzleCanvas;
import puzzle.PuzzleProblem;
import blocks.BlockProblem;
import blocks.BlockCanvas;

/**
 * A class to display the bridge crossing and water jug problems in a tabbed pane
 * within an application frame.
 * @author tcolburn
 */
public class TestFrame extends JFrame {
    
    public TestFrame() {
        super("Testing Bridge and Water Jug Problems");
        ProblemPane problemPane = new ProblemPane();
        WaterJugProblem theProblem = new WaterJugProblem();
        BridgeProblem theOtherProblem = new BridgeProblem();
        PuzzleProblem thePuzProblem = new PuzzleProblem();
        BlockProblem theBlockProblem = new BlockProblem();
        problemPane.add("Bridge", new GUI(theOtherProblem, 
                new BridgeCanvas(theOtherProblem.getCurrentState()),
                new BridgeCanvas(theOtherProblem.getFinishState())));
        problemPane.add("Water Jug", new GUI(theProblem, 
                new WaterJugCanvas(theProblem.getCurrentState()),
                new WaterJugCanvas(theProblem.getFinishState())));
        problemPane.add("8-Piece Puzzle", new GUI(thePuzProblem, 
                new PuzzleCanvas(thePuzProblem.getCurrentState()),
                new PuzzleCanvas(thePuzProblem.getFinishState())));
        problemPane.add("Block Problem", new GUI(theBlockProblem,
                new BlockCanvas(theBlockProblem.getCurrentState()),
                new BlockCanvas(theBlockProblem.getFinishState())));
        
        add(problemPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new TestFrame();
    }
    
}