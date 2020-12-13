package agh.cs.lab8.visualisation;

import agh.cs.lab8.map_elements.Animal;
import agh.cs.lab8.maps.Jungle;
import agh.cs.lab8.utils.Vector2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StartingPanel extends JPanel implements ActionListener {
    public static final int HEIGHT = 120;
    public static final int WIDTH = 250;
    private final Integer[] params;
    private int counter=1;
    JButton startButton;
    JButton startButton2
;
    public StartingPanel(Integer[] params) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.params = params;
        startButton = new JButton("Start Simulation");
        startButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        add(buttonPanel);
        startButton2 = new JButton("Start Two Simulations");
        startButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                starting();
                starting();
            }
        });
        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.add(startButton2);
        add(buttonPanel2);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        starting();
    }

    private void starting() {
        Jungle junge = new Jungle(
                params[0],
                params[1],
                new Vector2d(params[2], params[3]),
                new Vector2d(params[4], params[5]),
                params[6],
                params[7],
                params[8],
                params[9]
        );
        Animal.moveEnergy = params[10];
        Simulator simulator = new Simulator(junge, params[11], counter, params[11]);
        counter += 1;
        simulator.start();
    }
}