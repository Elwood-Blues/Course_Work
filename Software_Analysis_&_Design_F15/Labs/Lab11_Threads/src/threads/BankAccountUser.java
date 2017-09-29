package threads;

import java.util.Iterator;
import java.util.List;

/**
 * This class represents a bank account user.
 * @author tcolburn
 */
public class BankAccountUser extends Thread{

    /**
     * Creates a bank account user with a given name, account, and list of 
     * transactions.
     * A transaction is represented as an integer.
     * A positive integer represents a deposit, while 
     * a negative integer represents a withdrawal.
     * @param name this user's name
     * @param account this user's account
     * @param transactions the list of transactions
     */
    public BankAccountUser(String name, BankAccount account, List<Integer> transactions) {
        super(name);
        this.account = account;
        this.transactions = transactions;
        oneMore = false;
        finished = false;
    }

//    /**
//     * Getter for this user's name.
//     * @return this user's name
//     */
//    public String getName() {
//        return name;
//    }

    /**
     * Getter for this user's account.
     * @return this user's account
     */
    public BankAccount getAccount() {
        return account;
    }

    /**
     * Getter for this user's finished status.
     * @return whether this user has completed all transactions
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Setter for this user's finished status.
     * @param finished whether this user has completed all transactions
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    /**
     * Getter for this user's "one more" status.
     * @return whether this user has one more transaction to do
     */
    public boolean isOneMore() {
        return oneMore;
    }

    /**
     * Setter for this user's "one more" status.
     * @param oneMore whether this user has one more transaction to do
     */
    public void setOneMore(boolean oneMore) {
        this.oneMore = oneMore;
    }
    
    /**
     * This method attempts to process all the transactions on this user's
     * transaction list.
     */
    public void run() {
        Iterator<Integer> iter = transactions.iterator();
        try{    
        while (iter.hasNext()) {
                int amount = iter.next();
                if ( !iter.hasNext() ) {
                    oneMore = true;
                }
                if (amount > 0) {
                    account.deposit(amount, this);
                } 
                else if (amount < 0) {
                    account.withdraw(Math.abs(amount), this);
                } // amount == 0 ignored
                sleep(100);
            }
        }
        catch(InterruptedException ex) {}
    }
    
    private String name;
    private BankAccount account;
    private List<Integer> transactions;
    private boolean oneMore;
    private boolean finished;
    
}
