package threads;

import javax.swing.JFrame;

/**
 * This class creates a frame for displaying bank account simulations.
 * @author tcolburn
 */
public class BankAccountFrame extends JFrame {
    
    public BankAccountFrame() {
        super("Bank Account Test");
        add(new BankAccountComponent());
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new BankAccountFrame();
    }
    
}
