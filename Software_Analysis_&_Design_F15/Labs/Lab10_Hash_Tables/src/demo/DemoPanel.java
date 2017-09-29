/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package demo;

import gui.GUIPanel;
import generator.AdHocIntegerGenerator;
import generator.CompleteIntegerGenerator;
import generator.RandomIntegerGenerator;
import generator.KeyGenerator;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

/**
 *
 * @author tcolburn
 */
public class DemoPanel extends JPanel {

    public DemoPanel() {

        perfectGenerator = new CompleteIntegerGenerator();
        goodGenerator = new RandomIntegerGenerator();
        poorGenerator = new AdHocIntegerGenerator();

        guiPanel = new GUIPanel();

        perfectButton = new JRadioButton("Perfect");
        goodButton = new JRadioButton("Good");
        poorButton = new JRadioButton("Poor");

        perfectButton.addActionListener(makeButtonListener(perfectGenerator));
        goodButton.addActionListener(makeButtonListener(goodGenerator));
        poorButton.addActionListener(makeButtonListener(poorGenerator));

        group = new ButtonGroup();

        group.add(perfectButton);
        group.add(goodButton);
        group.add(poorButton);

        group.setSelected(perfectButton.getModel(), true);
        guiPanel.setKeyGenerator(perfectGenerator);

        controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(150,100));
        controlPanel.setLayout(new GridLayout(3, 1, 0, 5));
        controlPanel.setBorder(new TitledBorder("Hash Performance"));

        controlPanel.add(perfectButton);
        controlPanel.add(goodButton);
        controlPanel.add(poorButton);

        add(guiPanel);
        add(controlPanel);

    }

    private ActionListener makeButtonListener(final KeyGenerator keyGenerator) {
        return new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                keyGenerator.reset();
                guiPanel.setKeyGenerator(keyGenerator);
                guiPanel.clear();
            }
        };
    }

    private GUIPanel guiPanel;

    private ButtonGroup group = new ButtonGroup();

    private JRadioButton perfectButton;
    private JRadioButton goodButton;
    private JRadioButton poorButton;

    private JPanel controlPanel;

    private KeyGenerator perfectGenerator;
    private KeyGenerator goodGenerator;
    private KeyGenerator poorGenerator;

}
