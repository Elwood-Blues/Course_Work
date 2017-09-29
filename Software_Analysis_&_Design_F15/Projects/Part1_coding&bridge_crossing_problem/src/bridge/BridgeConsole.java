package bridge;

import java.util.Scanner;

/**
 * This class provides a terminal console interface to the Bridge
 * Crossing problem.
 * The user attempts to solve the problem, with invalid moves rejected,
 * and the user can quit at any time.
 * If a move is valid, the new state is displayed.
 * If the solution is found, a message is given showing the number of moves
 * attempted, and processing halts.
 * @author Peter Braband #3418868
 */
public class BridgeConsole {
/**
     * Private variable keeps track of the moves used in the program.
     */
    private static int moveCount;
    
    /**
     * Constant string for the message to display if you lost the game.
     */
    private static String FAILUREMESSAGE = "You've exceeded the time!  Try again next time!";
    
     /**
     * This method prints the snapshot of the current game.
     * @param theProblem current bridge problem.
     * @param moveCount counts the number of moves the gamer has tried.
     */
    private static void printState(BridgeProblem theProblem, int moveCount) {
        System.out.println(theProblem.getCurrentState().toString());
        System.out.println("You have made " + moveCount + " moves.");
    }
    
    /**
     * This method prints list of moves from the problem.
     * @param theProblem current bridge problem.
     */
    private static void printMoveList(BridgeProblem theProblem)
    {
        System.out.println("0. Quit the Game");
        int i = 0;
        while(i <= theProblem.getMoves().size()-1) {
            System.out.println(i+1 + ". " + theProblem.getMoves().
                get(i).getMoveName());    
            i++;
        }
    }
    
     /**
     * This method gets the option from the user.  It handles the input exceptions
     * as well as checks to make sure it's a valid option, but doesn't check 
     * to see if it's a valid move.
     * @return integer corresponding with a legal output.
     */
    private static int getOption() {
        boolean keepGoing = true;
        int inputInteger = -1;
        while (keepGoing) {
            System.out.println("Please choose an option:");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            try {
                inputInteger = Integer.parseInt(input);
                if (inputInteger >= 0 && inputInteger <= 10)
                keepGoing = false;
                else System.out.println("Option out of range.  Try again.");
            }
            catch(NumberFormatException ex) {
                System.out.println("Option out of range.  Try again.");
            }
            
        }
        
        return inputInteger;
    }
    
      /**
     * This method checks to see if the move selected can be done on the current bridge state.
     * @param option1 User chosen option.
     * @param theProblem Bridge problem being worked on.
     * @return Returns a boolean, true if the move is valid.
     */
    private static boolean isValidMove(int option1, BridgeProblem theProblem) {
        boolean isValid = false;
        
        if(option1 == 1) {
            isValid = theProblem.getCurrentState().getP1Position() == 
                    theProblem.getCurrentState().getFlashlightPosition();
        }
        if(option1 == 2) {
            isValid = theProblem.getCurrentState().getP2Position() == 
                    theProblem.getCurrentState().getFlashlightPosition();
        } 
        if(option1 == 3) {
            isValid = theProblem.getCurrentState().getP5Position() == 
                    theProblem.getCurrentState().getFlashlightPosition();
        } 
        if(option1 == 4) {
            isValid = theProblem.getCurrentState().getP10Position() == 
                    theProblem.getCurrentState().getFlashlightPosition();
        } 
        if(option1 == 5) {
            isValid = ((theProblem.getCurrentState().getP1Position() ==
                    theProblem.getCurrentState().getP2Position()) &&
                    (theProblem.getCurrentState().getP2Position() ==
                    theProblem.getCurrentState().getFlashlightPosition()));
        } 
        if(option1 == 6) {
            isValid = ((theProblem.getCurrentState().getP1Position() ==
                    theProblem.getCurrentState().getP5Position()) &&
                    (theProblem.getCurrentState().getP5Position() ==
                    theProblem.getCurrentState().getFlashlightPosition()));
        }
        if(option1 == 7) {
            isValid = ((theProblem.getCurrentState().getP1Position() ==
                    theProblem.getCurrentState().getP10Position()) &&
                    (theProblem.getCurrentState().getP10Position() ==
                    theProblem.getCurrentState().getFlashlightPosition()));
        }   
        if(option1 == 8) {
            isValid = ((theProblem.getCurrentState().getP2Position() ==
                    theProblem.getCurrentState().getP5Position()) &&
                    (theProblem.getCurrentState().getP5Position() ==
                    theProblem.getCurrentState().getFlashlightPosition()));
        }   
        if(option1 == 9) {
            isValid = ((theProblem.getCurrentState().getP2Position() ==
                    theProblem.getCurrentState().getP10Position()) &&
                    (theProblem.getCurrentState().getP10Position() ==
                    theProblem.getCurrentState().getFlashlightPosition()));
        }   
        if(option1 == 10) {
            isValid = ((theProblem.getCurrentState().getP5Position() ==
                    theProblem.getCurrentState().getP10Position()) &&
                    (theProblem.getCurrentState().getP10Position() ==
                    theProblem.getCurrentState().getFlashlightPosition()));
        }   
        return isValid;
    }
    
     /**
     * This method changes the bridge state depending on the option selected.
     * @param option1 User chosen option.
     * @param theProblem Bridge problem being worked on.
     */
    private static void performMove(int option1, BridgeProblem theProblem) {
        
            theProblem.setCurrentState(theProblem.getMoves().
                get(option1-1).doMove(theProblem.getCurrentState()));
            
        
    }
    
    
     /**
     * This method determines whether the game is over.  A game is over if the
     * time elapsed exceeds 17 minutes or if the problem is solved.
     * @param theProblem Bridge problem being worked on.
     * @return Returns a boolean, true if the game should end, false if it should continue.
     */
    private static boolean isGameOver(BridgeProblem theProblem) {
        boolean isOver = false;
        if(theProblem.getCurrentState().getTimeSoFar() > 17) {
            System.out.println(FAILUREMESSAGE);
            isOver = true;
        }
        else
        { 
            if(theProblem.getCurrentState().getP1Position() == Position.EAST &&
              theProblem.getCurrentState().getP2Position() == Position.EAST &&
              theProblem.getCurrentState().getP5Position() == Position.EAST &&
              theProblem.getCurrentState().getP10Position() == Position.EAST ) 
                {
                    System.out.println("Congratulations!  You solved the problem in " +
                        moveCount + " moves.");
                    isOver = true;
                }
        }
        return isOver;
    }
    
    /**
       Creates a bridge problem console user interface.  An introduction
       is displayed, the move count is initialized, the initial state is
       displayed, the first move is solicited, and problem solution proceeds.
       @param problem the problem
     */
    public BridgeConsole(BridgeProblem problem) {
        BridgeProblem theProblem = problem;
        int option1 = -1;
        boolean keepGoing = true;
        
        System.out.println(theProblem.getIntroduction());
        printState(theProblem, moveCount);
        printMoveList(theProblem);
 
        while(option1 != 0 && keepGoing) {
           option1 = getOption();
           if (option1 != 0) {
               if (isValidMove(option1,theProblem)) {
                    moveCount++;
                    performMove(option1, theProblem);
                    printMoveList(theProblem);
                    printState(theProblem, moveCount);       
               }
               else
               {
                   System.out.println("Invalid move.  Try again.");
               }
           } 
           keepGoing = !isGameOver(theProblem);
        } //end while
        
        
    }
    
    

    
    /**
     * This method launches the console
     * @param args ignored
     */
    public static void main(String[] args) {
        moveCount = 0;
        new BridgeConsole(new BridgeProblem());
    }

}
