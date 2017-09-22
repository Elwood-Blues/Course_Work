/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lab1;

import java.util.*;
import java.io.IOException;

/**
 * This class will be enhanced so that it handles exceptions better.
 * As it stands it is vulnerable to IOExceptions, NumberFormatExceptions,
 * and illogical input.
 * Peter Braband
 * braba006
 * lab1
 * section: 12:00
 */
public class InputTesterRobust {

    /**
     * This method relies on a private helper method getAge that may
     * throw an IOException.
     * @param args Not used.
     */
    public static void main(String[] args)throws IOException {
	boolean more = true;
        char ch;
        while(more)
        {
        int age;
        try
        {
            age = getAge();
        }
        catch (IOException ex)
        {
            //ex.getMessage();
            System.out.println("Bad Input.  Please re-enter age");
            age = getAge();
        }    
            if(age >= 0)
            {
                age++;
                System.out.println("Next year, you'll be " + age);
            }
            else if(age < 0)
                System.out.println("Age can't be negative");
        Scanner in = new Scanner(System.in);
	System.out.println("Would you like to do another? (y/n)");
        ch = in.next().charAt(0);
        if ( ch == 'y'|| ch == 'Y')
            more = true;
        else
            more = false;
        }
    System.exit(0);
    }

    /**
     * This method gets a line of input from the user that is supposed
     * to be the user's age as an integer.  A special input, namely
     * the string "ioerror" triggers a program-thrown IOException for
     * testing purposes.
     */
    private static int getAge() throws IOException, NumberFormatException {
	Scanner in = new Scanner(System.in);
	System.out.println("How old are you? ");
	String input = in.nextLine();
	int ret = 0;
        if (input.equals("ioerror"))
            throw new IOException 
                ("\n This is a bogus I/O exception for testing");
        
        try
        { 
            ret = Integer.parseInt(input);
        }
        catch (NumberFormatException)
        {
            System.out.println("Bad Integer.  Please re-enter age");
            int age = getAge();
            ret = age;
        }
        /*catch (IOException ex)
        {
            ex.getMessage();
            System.out.println("Bad Input.  Please re-enter age");
            int age = getAge();
            ret = age;
        }*/
        return ret;
    }

}
