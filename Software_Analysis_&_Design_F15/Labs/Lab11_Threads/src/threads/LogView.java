package threads;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * This class implements the <b>java.util.Observer</b> interface and
 * presents a scrolled view of messages received from an observable.
 * @author tcolburn
 */
public class LogView extends JScrollPane implements Observer {
    
    public LogView(String title) {
        super(transactionArea);
        setBorder(new TitledBorder(title));
    }
    
    public void update(Observable account, Object message) {
        if ( message == null ) {
            transactionArea.setText("");
        }
        else {
            transactionArea.append((String)message);
        }
    }
    
    private static JTextArea transactionArea = new JTextArea(40,45);
    
}
