/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;
import static java.lang.Thread.sleep;

/**
 *
 * @author Peter
 */
public class BankAccountRescuer extends BankAccountUser{
    
    public BankAccountRescuer (String name, BankAccount account, BankAccountUser[] users){
        super(name, account, null);
        this.users = users;
    }
    
    private boolean allFinished(){
        boolean allFinished = true;
        int i = 0;
        
        while(i < users.length && allFinished){
            allFinished = users[i].isFinished();
            i++;
        }
        return allFinished;
    }
    
    private boolean allWaiting(){
        boolean allWaiting = true;
        int i = 0;
        
        while(i < users.length && allWaiting){
            if(users[i].getState() == State.WAITING) allWaiting = true;
            else allWaiting = false;
            i++;
        }
        return allWaiting;
    }
    
    public void run(){
        try{
            while(!allFinished()){
                if(allWaiting()) super.getAccount().deposit(100, this);
                sleep(1000);
            }
        }
        catch(InterruptedException ex){}
        super.setOneMore(true);
        super.getAccount().deposit(0,this);
    }
    private BankAccountUser[] users;
}
