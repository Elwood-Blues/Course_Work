/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

/**
 * A class that creates GUI components for solving search problems.
 * @author Peter Braband
 */
public class GUI extends JComponent {
    
    public GUI(Problem problem, Canvas canvas) {
        //Initialize the starting state
        STARTINGSTATE = problem.getCurrentState();
        moveCount = 0;
        
        
        this.setPreferredSize(new Dimension(800,500));
        this.setLayout(new FlowLayout());
        
        //Setup Introduction Panel
        JPanel panelMain = new JPanel();
        //panelMain.setLayout(new GridLayout(3,1,20,20));
        panelMain.setLayout(new BorderLayout());
        
        JPanel panelGame = new JPanel();
        panelGame.setLayout(new GridLayout(0,2,20,0));
        panelGame.setPreferredSize(new Dimension(700,300));
        
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(5,2));
        panelButtons.setPreferredSize(new Dimension(700,300));
        
        JTextArea textIntro = getIntroductionArea(problem.getIntroduction());
        //textState = getProblemStateArea(problem.getCurrentState());
        JPanel canvasPanel = new JPanel();
        canvasPanel.add(canvas);
        canvasPanel.setBorder(BorderFactory.createTitledBorder("Game State"));
        canvas.repaint();
        
        panelMain.add(textIntro, BorderLayout.PAGE_START);
        panelGame.add(canvasPanel);
        
        moveButtons = new JButton[problem.getMoves().size()];
        
        for (int i = 0; i <= problem.getMoves().size()-1; i++)
        {
            moveButtons[i] = moveButtonMaker(problem.getMoves().get(i),problem, canvas);
            panelButtons.add(moveButtons[i]);
        }
        panelButtons.setBorder(BorderFactory.createTitledBorder("Game Moves"));
        panelGame.add(panelButtons);
        panelMain.add(panelGame, BorderLayout.CENTER);
        
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        menuPanel.add(resetButtonMaker(problem, canvas));
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
        textIntro.setPreferredSize(new Dimension(700,150));
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
//    private JTextArea getProblemStateArea(State curState) {
//        JTextArea textState = new JTextArea();
//        textState.setText(curState.toString());
//        textState.setPreferredSize(new Dimension (400,150));
//        textState.setPreferredSize(new Dimension(700,150));
//        textState.setBackground(Color.WHITE);
//        textState.setOpaque(true);
//        textState.setWrapStyleWord(true);
//        textState.setLineWrap(true);
//        textState.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
//        textState.setBorder(BorderFactory.createTitledBorder("Game State"));
//        
//        return textState;
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
                   //textState.setText(problem.getCurrentState().toString());
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
               resetGame(problem, canvas);
               //textState.setText(problem.getCurrentState().toString());
               moveCount = 0;
          }
        });
        return newButton;
    }
    
     /**
     * Method for resetting the game, changing game state and re-enabling buttons.
     * @param problem.  Current Problem for the GUI.
     */    
    private void resetGame(final Problem problem, final Canvas canvas) {
        problem.setCurrentState(STARTINGSTATE);
        canvas.setCurState(STARTINGSTATE);
        canvas.stopTimer();
        canvas.rePainter();
        
        for (int i = 0; i < moveButtons.length; i++) {
            moveButtons[i].setEnabled(true);
        }
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
}
