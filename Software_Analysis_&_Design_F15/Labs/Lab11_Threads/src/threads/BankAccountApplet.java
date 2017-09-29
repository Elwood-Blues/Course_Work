package threads;

import javax.swing.JApplet;

/**
 * This class creates an applet for displaying bank account simulations.
 * @author tcolburn
 */
public class BankAccountApplet extends JApplet {
    
    /**
     * Initialization method that will be called after the applet is loaded
     * into the browser.
     */
    public void init() {
        add(new BankAccountComponent());
    }
    
}
