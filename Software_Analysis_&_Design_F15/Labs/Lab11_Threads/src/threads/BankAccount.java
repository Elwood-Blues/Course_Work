package threads;

import java.util.Observable;

/**
 * This class allows the simulation of a simple bank account.
 * It will be used to demonstrate multi-threading.
 * Note that this class extends <b>java.util.Observable</b> so that it
 * can participate in the <b>Observer</b> design pattern.
 * An object of type <b>BankAccount</b> is the subject managing the model, 
 * while a <b>LogView</b> object is the observer providing the view.
 * @author tcolburn
 */
public class BankAccount extends Observable {

    /**
     * Setter for this account's balance.
     * For simplicity all monetary amounts are integers.
     * @param balance the new balance
     */
    public synchronized void setBalance(int balance) {
        this.balance = balance;
        setChanged();
        notifyObservers(null);
    }
    
    /**
     * Deposits an amount into this account.
     * Note that an assertion is performed at the end of this method to test
     * whether it is thread-safe.
     * If there are multiple threads accessing this bank account, it is possible
     * that a race condition will cause an incorrect balance to be accessed.
     * @param amount the amount to deposit
     * @precondition amount must be positive
     */
    public synchronized void deposit(int amount, BankAccountUser user) {
        log("\n" +user.getName()+ " Depositing $" +amount);
        int newBalance = balance + amount;
        balance = balance + amount;
        log(". Balance = " + balance);
        checkFinished(user);
        notifyAll();
        assert(balance == newBalance); // should be true if this method is thread-safe
    }
    
    /**
     * Withdraws an amount from this account.
     * Note that an assertion is performed at the end of this method to test
     * whether it is thread-safe.
     * If there are multiple threads accessing this bank account, it is possible
     * that a race condition will cause an incorrect balance to be accessed.
     * @param amount the amount to withdraw
     * @precondition amount must be positive
     * @throws RuntimeException if amount is greater than the balance
     */
    public synchronized void withdraw(int amount, BankAccountUser user) throws InterruptedException{
        log("\n" +user.getName() + " Withdrawing $" + amount);
        if ( amount > balance ) {
            //throw new RuntimeException("Amount (" +amount+ ") must not be greater than " +balance+ ".");
            wait();
        }
        int newBalance = balance - amount;
        balance = balance - amount;
        log(". Balance = " + balance);
        checkFinished(user);
        assert(balance == newBalance); // should be true if this method is thread-safe
    }

    /**
     * Getter for this account's balance.
     * @return this account's balance
     */
    public int getBalance() {
        return balance;
    }
    
    /**
     * Checks if the user is finished and logs a message if so.
     * @param user the bank account user
     */
    private void checkFinished(BankAccountUser user) {
        if ( user.isOneMore() ) {
            log("\n******************************\n        " + user.getName() + 
                " finished.\n******************************");
            user.setFinished(true);
        }
    }
    
    /**
     * Log's a message from this bank account by sending it to all its observers (including
     * the <b>LogView</b> object.
     * @param message the message to be logged
     */
    private void log(String message) {
        setChanged();
        notifyObservers(message);
    }
    
    private int balance;
    
}
