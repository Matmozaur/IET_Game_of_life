package agh.cs.lab8.visualisation;

import agh.cs.lab8.maps.Jungle;
import agh.cs.lab8.utils.Vector2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StartingPanel extends JPanel implements ActionListener {
    public static final int HEIGHT = 60;
    public static final int WIDTH = 150;
    private Integer[] params;
    private JButton startButton;

    public StartingPanel(Integer[] params) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.params = params;
        startButton = new JButton("Start Simulation");
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
        Simulator simulator = new Simulator(junge, params[10]);
        simulator.start();
    }
}