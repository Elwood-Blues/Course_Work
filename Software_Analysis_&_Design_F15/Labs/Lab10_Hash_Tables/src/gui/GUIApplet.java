/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import javax.swing.JApplet;

/**
 *
 * @author tcolburn
 */
public class GUIApplet extends JApplet {

    /**
     * Initialization method that will be called after the applet is loaded
     * into the browser.
     */
    public void init() {
        add(new GUIPanel());
    }

    // TODO overwrite start(), stop() and destroy() methods

}
