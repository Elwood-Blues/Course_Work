package threads;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * This class presents GUI controls for running bank account simulations.
 * @author tcolburn
 */
public class BankAccountControl extends JComponent {
    
    /**
     * Creates combo boxes and a button allowing the configuring and running
     * of bank account simulations.
     * Combo boxes allow the selection of a start balance for the bank account, 
     * the number of siblings using the account, the number of transactions
     * for each sibling, and the transaction limit.
     * The run button listener sets up and runs a simulation.
     * @param account the bank account used in the simulations
     */
    public BankAccountControl(BankAccount account) {
        
        this.account = account;
        
        balanceChoices = makeComboBox("Start Balance", new int[] {100, 500, 1000, 10000});
        usersChoices = makeComboBox("Number of Siblings", new int[] {1, 2, 3, 4, 5});
        limitChoices = makeComboBox("Transaction Limit", new int[] {50, 100, 200, 500});
        transactionsChoices = makeComboBox("Transactions per Sibling", new int[] {10, 25, 50, 100});
        
        runButton = new JButton("RUN");
        
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {  
                setup();
                run();
            }
        });
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 0, 10));
        panel.add(balanceChoices);
        panel.add(usersChoices);
        panel.add(transactionsChoices);
        panel.add(limitChoices);
        panel.add(runButton);
        
        setLayout(new FlowLayout());
        add(panel);
    }
    
    /**
     * Makes a titled combo box of integer options.
     * @param title string to be used in a titled border
     * @param options the integer options offered by the combo box
     * @return the titled combo box
     */
    private JComboBox makeComboBox(String title, int[] options) {
        JComboBox comboBox = new JComboBox();
        comboBox.setPreferredSize(new Dimension(title.length()*8, 50));
        comboBox.setBorder(new TitledBorder(title));
        for (int i = 0; i < options.length; i++) {
            comboBox.addItem(options[i]);
        }
        return comboBox;
    }
    
    /**
     * Sets up a bank account simulation using the selected combo box values.
     * The account's start balance is set and the specified number of 
     * <b>BankAccountUser</b> objects are created and stored in an array.
     */
    private void setup() {
        startBalance = (Integer) balanceChoices.getSelectedItem();
        numUsers = (Integer) usersChoices.getSelectedItem();
        amountLimit = (Integer) limitChoices.getSelectedItem();
        numTransactions = (Integer) transactionsChoices.getSelectedItem();
        
        account.setBalance(startBalance);
        users = new BankAccountUser[numUsers];
        
        for (int i = 0; i < users.length; i++) {
            users[i] = new BankAccountUser("Sibling " + (i+1), account, makeTransactions());
        }
        parent = new BankAccountRescuer("Parent", account, users);
    }
    
    /**
     * The simulation is run by calling the <b>run</b> methods of each of
     * the <b>BankAccountUser</b>s in the array.
     */
    private void run() {
        try {
            for (int i = 0; i < users.length; i++) {
                users[i].start();
            }
            parent.start();
        }
        catch(RuntimeException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    /**
     * Makes a list of random integer values for deposits and withdrawals.
     * Positive integers represent deposits; negative integers represent withdrawals.
     * The size of the list is determined by numTransactions.
     * The amount of deposit or withdrawal is determined by amountLimit.
     * @return the list of integers
     */
    private List<Integer> makeTransactions() {
        List<Integer> transactions = new ArrayList<Integer>();
        for (int i = 0; i < numTransactions; i++) {
            int amount = generator.nextInt(amountLimit) + 1; // 1 <= amount <= amountLimit
            transactions.add(generator.nextBoolean() ? amount : -amount);
        }
        return transactions;
    }
    
    private static Random generator = new Random();
    
    private BankAccount account;
    private BankAccountUser[] users;
    private BankAccountRescuer parent;
    
    private JComboBox balanceChoices;
    private JComboBox usersChoices;
    private JComboBox limitChoices;
    private JComboBox transactionsChoices;
    
    private JButton runButton;
    
    private int startBalance;
    private int numUsers;
    private int amountLimit;
    private int numTransactions;
    
}
