package threads;

import java.awt.FlowLayout;
import javax.swing.JComponent;

/**
 * This class creates a bank account simulation component.
 * @author tcolburn
 */
public class BankAccountComponent extends JComponent {
    
    public BankAccountComponent() {
        
        BankAccount account = new BankAccount();
        LogView view = new LogView("Transaction Log");
        account.addObserver(view);
        BankAccountControl control = new BankAccountControl(account);
        
        setLayout(new FlowLayout());
        add(view);
        add(control);
    }
    
}
