/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package demo;

import javax.swing.JApplet;

/**
 *
 * @author tcolburn
 */
public class DemoApplet extends JApplet {

    /**
     * Initialization method that will be called after the applet is loaded
     * into the browser.
     */
    public void init() {
        add(new DemoPanel());
    }

    // TODO overwrite start(), stop() and destroy() methods

}
