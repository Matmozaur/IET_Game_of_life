package agh.cs.lab8.visualisation;

import agh.cs.lab8.map_elements.Animal;
import agh.cs.lab8.maps.Jungle;
import agh.cs.lab8.utils.Vector2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StartingPanel extends JPanel implements ActionListener {
    public static final int HEIGHT = 60;
    public static final int WIDTH = 150;
    private final Integer[] params;
    private int counter=1;

    public StartingPanel(Integer[] params) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.params = params;
        JButton startButton = new JButton("Start Simulation");
        startButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        add(buttonPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
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