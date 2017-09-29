/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import graph.DequeAdder;
import graph.Vertex;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Deque;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import graph.SimpleVertex;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Deque;
/**
 * A class that creates GUI components for solving search problems.
 * @author Peter Braband
 */
public class GUI extends JComponent {
    
    public GUI(Problem problem, Canvas canvas, Canvas finishCanvas) {
        //Initialize the starting state
        STARTINGSTATE = problem.getCurrentState();
        moveCount = 0;
        this.finishCanvas = finishCanvas;
        
        
        this.setPreferredSize(new Dimension(1000,600));
        this.setLayout(new FlowLayout());
        
        //Setup Introduction Panel
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout());

        
        JPanel panelGame = new JPanel();
        panelGame.setLayout(new GridLayout(0,3,20,0));
        panelGame.setPreferredSize(new Dimension(1000,350));
        
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(5,2));
        panelButtons.setPreferredSize(new Dimension(700,350));
        
        JTextArea textIntro = getIntroductionArea(problem.getIntroduction());
        JPanel canvasPanel = new JPanel();
        canvasPanel.add(canvas);
        canvasPanel.setBorder(BorderFactory.createTitledBorder("Game State"));
        canvas.repaint();
        
        JPanel completeCanvasPanel = new JPanel();
        completeCanvasPanel.setBorder(BorderFactory.createTitledBorder("Goal State"));
        completeCanvasPanel.add(this.finishCanvas);
        
        panelMain.add(textIntro, BorderLayout.PAGE_START);
        panelGame.add(canvasPanel);
        panelGame.add(completeCanvasPanel);
        
        moveButtons = new JButton[problem.getMoves().size()+1];
        
        for (int i = 0; i <= problem.getMoves().size()-1; i++)
        {
            moveButtons[i] = moveButtonMaker(problem.getMoves().get(i),problem, canvas);
            //JButton thisButton = moveButtonMaker(problem.getMoves().get(i),problem);
            panelButtons.add(moveButtons[i]);
        }
        panelButtons.setBorder(BorderFactory.createTitledBorder("Game Moves"));
        panelGame.add(panelButtons);
        panelMain.add(panelGame, BorderLayout.CENTER);
        
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        menuPanel.add(resetButtonMaker(problem,canvas));
        
        JPanel solverPanel = new JPanel();
        solverPanel.setLayout(new GridLayout(4,1));
        
        
        
        
        final JRadioButton radioDepth = new JRadioButton("Depth First");
        //radioDepth.setSelected(true);
        //isDepthFirst = true;
        final JRadioButton radioBreadth = new JRadioButton("Breadth First");
        final JRadioButton radioAStar = new JRadioButton("A*Star Search");
        
        
        
        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changEvent) {
                if (radioDepth.isSelected()) {
                    isDepthFirst = true;
                    isAStar = false;
                }
                else if (radioAStar.isSelected() == false) {
                    isAStar = true;
                    isDepthFirst = false;
                } else isAStar = isDepthFirst = false;
            }
        };
        
        
        
        radioDepth.addChangeListener(changeListener);
        radioAStar.addChangeListener(changeListener);
        
        radioDepth.setEnabled(false);
        radioBreadth.setEnabled(false);
        isAStar = true;
        radioAStar.setSelected(true);
        
        ButtonGroup solverGroup = new ButtonGroup();
        solverGroup.add(radioDepth);
        solverGroup.add(radioBreadth);
        solverGroup.add(radioAStar);

        solverPanel.setBorder(BorderFactory.createTitledBorder("Solve Options"));

        
        solverPanel.add(radioDepth);
        solverPanel.add(radioBreadth);
        solverPanel.add(radioAStar);

        
        
        JPanel solutionPanel = new JPanel();
        solutionPanel.setLayout(new GridLayout(3,1));
        solutionPanel.setBorder(BorderFactory.createTitledBorder("Solution Stats"));
        
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(3,2));
        
        JLabel labelSolnLength = new JLabel("Solution Length: ");
        JLabel labelNumDequeOps = new JLabel("Number of PriorityQueue Ops: ");
        JLabel labelMaxDeque = new JLabel("Max PriorityQue Size: ");

        statsPanel.add(labelSolnLength);
        statsPanel.add(labelSolnLengthAns);
        statsPanel.add(labelNumDequeOps);
        statsPanel.add(labelNumDequeOpsAns);
        statsPanel.add(labelMaxDeque);
        statsPanel.add(labelMaxDequeAns);
       
        buttonNextMove.setEnabled(false);
        buttonAllMove.setEnabled(false);
        
        solutionPanel.add(statsPanel);
        showNextMoveButtonMaker(problem, canvas);
        showAllMovesButtonMaker(problem, canvas);
        solutionPanel.add(buttonNextMove);
        solutionPanel.add(buttonAllMove);
        
        moveButtons[problem.getMoves().size()] = solveButtonMaker(problem);
        solverPanel.add(moveButtons[problem.getMoves().size()]);
        
        menuPanel.add(solverPanel);
        menuPanel.add(solutionPanel);
        
        panelMain.add(menuPanel, BorderLayout.PAGE_END);
        add(panelMain);
        
    }
    
    // private methods and instance fields go here
    
    /**
     * Method that generates a textbox for the introduction.
     * @param String containing the introduction message.
     * @return a JTextArea containing the introduction.
     */
    private JTextArea getIntroductionArea(String intro) {
        JTextArea textIntro = new JTextArea();
        textIntro.setText(intro);
        textIntro.setPreferredSize(new Dimension(700,100));
        textIntro.setBackground(Color.WHITE);
        textIntro.setOpaque(true);
        textIntro.setWrapStyleWord(true);
        textIntro.setLineWrap(true);
        textIntro.enableInputMethods(false);
        
        return textIntro;
    }
    
     /**
     * Method that generates a textbox for the problem state.
     * @param String containing the current state representation message.
     * @return a JTextArea containing the current state representation.
     */
//    private Canvas getProblemStateArea(State curState) {
//        canvasState.setCurState(curState);
//        
//        return canvasState;
//    }
    
     /**
     * Method that generates a button for a game move.
     * @param move Move to link to the button.
     * @param problem.  Current problem to work on.
     * @return a JButton containing an action listener for the move.
     */
    private JButton moveButtonMaker(final Move move, final Problem problem, final Canvas canvas) {
        JButton newButton = new JButton();
        newButton.setText(move.getMoveName());
        newButton.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,12));
        
        newButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
               State checkState = move.doMove(problem.getCurrentState());
               if (checkState != null) {
                   problem.setCurrentState(checkState);
                   canvas.setCurState(checkState);
                   canvas.stopTimer();
                   canvas.rePainter();

                   moveCount++;
                   if (problem.success()) winGame(problem);
               }
               else {
                   JOptionPane.showMessageDialog(null, "The move you entered is not legal.  "
                           + "Please try another move", "Illegal Move", JOptionPane.WARNING_MESSAGE);
               }
                  
          }
        });
        return newButton;
    }
    
    /**
     * Method that generates a reset button for the class.
     * @param problem. Current Problem for the GUI.
     * @return a JButton containing an action listener for reset.
     */
    private JButton resetButtonMaker(final Problem problem, final Canvas canvas)
    {
        JButton newButton = new JButton();
        newButton.setText("Reset Game");
        
        newButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {

              ArrayList<Integer> myInts = new ArrayList<Integer>();
              myInts.add(1); myInts.add(2); myInts.add(3);
              
              
              
              ProblemChooser chooser = new ProblemChooser(problem.getStartingStates(), 
                    problem.getSolutionStates(), problem.getSolutionLengths(), problem.getStartingCanvas(), 
                        problem.getSolutionCanvas());
              


              
              
              resetGame(problem, canvas, chooser);

          }
        });
        return newButton;
    }
    
     /**
     * Method that generates a solve button for the class.
     * @param problem. Current Problem for the GUI.
     * @return a JButton containing an action listener for solving the problem.
     */
    private JButton solveButtonMaker(final Problem problem)
    {
        JButton newButton = new JButton();
        newButton.setText("Solve");
        
        newButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            DequeAdder adder;  
            if(isDepthFirst) {
              adder = new DequeAdder() {
                public void add(Vertex vertex, Deque<Vertex> deque) {
                    deque.push(vertex);
                }
                
              };
              
            }
            else {
              adder = new DequeAdder() {
                public void add(Vertex vertex, Deque<Vertex> deque) {
                    deque.addLast(vertex);
                }
             };
            }
            
             problem.search(problem.getCurrentState(), adder);
             solvedStateInformation(problem);
            for (int i = 0; i < moveButtons.length; i++) {
                moveButtons[i].setEnabled(false);
            }
            
            buttonNextMove.setEnabled(true);
            buttonAllMove.setEnabled(true);

          }
        });
        return newButton;
    }
    
     /**
     * Method for resetting the game, changing game state and re-enabling buttons.
     * @param problem.  Current Problem for the GUI.
     */    
    private void resetGame(final Problem problem, final Canvas canvas, final ProblemChooser chooser) {
        
        problem.setCurrentState(chooser.getStart());
        problem.setFinishState(chooser.getFinal());
        canvas.setCurState(chooser.getStart());
        finishCanvas.setCurState(chooser.getFinal());
        
        
        canvas.stopTimer();
        canvas.rePainter();
        finishCanvas.rePainter();
        t.stop();
        
        problem.resetSolverOps();
        solvedStateInformation(problem);
        
        moveCount = 0;
        
        for (int i = 0; i < moveButtons.length; i++) {
            moveButtons[i].setEnabled(true);
        }
        
        buttonNextMove.setEnabled(false);
        buttonAllMove.setEnabled(false);
    }
    
     /**
     * Method for a successful game.  Disables buttons and shows dialog
     * @param problem.  Current Problem for the GUI.
     */    
    private void winGame(final Problem problem) {
        problem.setCurrentState(STARTINGSTATE);
        JOptionPane.showMessageDialog(null, "Congratulations, you have completed"
                + " the game in " + moveCount + " moves!  " + "Press Reset Game to play again.", "Game Over: You Win!", JOptionPane.INFORMATION_MESSAGE);
        for (int i = 0; i < moveButtons.length; i++) {
            moveButtons[i].setEnabled(false);
        }
    }
    
    
     /**
     * Method that generates a Next Move button for the class.
     * @param button. The button to setup the action listener for.
     
     */
    private void showNextMoveButtonMaker(final Problem problem,  final Canvas canvas)
    {

        buttonNextMove.setText("Next Move");
        
        buttonNextMove.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              problem.setCurrentState((State)problem.finishQueuePeek());
              canvas.setCurState((State)problem.finishQueuePeek());
              canvas.rePainter();
              problem.finishQueuePop();
              
              if(problem.success()) {
                  buttonNextMove.setEnabled(false);
                  buttonAllMove.setEnabled(false);
              }

          }
        });
        
    }
    
     /**
     * Method that displays or removes the solved state information
     * @param button. The button to setup the action listener for.   
     */
    private void solvedStateInformation(final Problem problem)
    {
        if(problem.getSolutionLength() > 0) {
             labelSolnLengthAns.setText(problem.getSolutionLength().toString());
             labelNumDequeOpsAns.setText(problem.getNoDequeOps().toString());
             labelMaxDequeAns.setText(problem.getMaxDequeSize().toString());
        }
        else
        {
             labelSolnLengthAns.setText("");
             labelNumDequeOpsAns.setText("");
             labelMaxDequeAns.setText("");
        }
        
    }
    
    
     /**
     * Method that generates an All Move button for the class.
     * @param button. The button to setup the action listener for.
     
     */
    private void showAllMovesButtonMaker(final Problem problem,  final Canvas canvas)
    {
        buttonAllMove.setText("Show All Moves");
        //buttonAllMove.setEnabled(true);

        //newButton.setText("Next Move");
        t.setCoalesce(false);
        t.addActionListener(new 
          ActionListener()
          {
          public void actionPerformed(ActionEvent event)
          {
             problem.setCurrentState((State)problem.finishQueuePeek());
             canvas.setCurState((State)problem.finishQueuePeek());
             canvas.rePainter();
             problem.finishQueuePop();
              
             if(problem.success()) {
                  t.stop();
                  
              }

          }
         });

        
        buttonAllMove.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
             buttonNextMove.setEnabled(false);
             buttonAllMove.setEnabled(false);
             t.start();
          }
        });
     

    }
    

     /**
     * Data constant holding the starting state for resetting.
     */
    final private State STARTINGSTATE;
     /**
     * An array of buttons to quickly enable and disable them.
     */
    final private JButton[] moveButtons;
    /**
     * An integer for storing the current move count.
     */
    private int moveCount;
    /**
     * A boolean for tracking the breadth vs depth first
     */
    private boolean isDepthFirst;
    /**
     * Holds canvas of the finished state.
     */
    private Canvas finishCanvas;
    
    /**
     * A label containing the results.
     */    
    JLabel labelSolnLengthAns = new JLabel();
    /**
     * A label containing the results.
     */    
    JLabel labelNumDequeOpsAns = new JLabel();
     /**
     * A label containing the results.
     */   
    JLabel labelMaxDequeAns = new JLabel();
    
     /**
     * Button for All Moves
     */     
    JButton buttonAllMove = new JButton();
     /**
     * Button for Next Moves
     */     
    JButton buttonNextMove = new JButton();
     /**
     * Timer for animating through moves.
     */     
     Timer t = new Timer(500, null);
     /**
      * Boolean for is AStar search
      */
     boolean isAStar = false;
}