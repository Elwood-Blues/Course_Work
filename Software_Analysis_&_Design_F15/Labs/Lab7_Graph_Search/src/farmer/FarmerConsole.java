package farmer;

import framework.Console;

/**
 * A class to run the FWGC problem in a terminal console.
 * @author tcolburn
 */
public class FarmerConsole {
    
    public static void main(String[] args) {
        new Console(new FarmerProblem()).start();
    }
    
}
