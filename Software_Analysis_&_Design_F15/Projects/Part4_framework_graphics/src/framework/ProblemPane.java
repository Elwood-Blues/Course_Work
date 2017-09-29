/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framework;

import javax.swing.JTabbedPane;


public class ProblemPane extends JTabbedPane {
    
    /**
     * Adds a problem to this problem pane.
     * @param title The name of the problem to add
     * @param gui The component that displays the problem
     */
    public void add(String title, GUI gui) {
        super.add(title, gui);
    }
    
}