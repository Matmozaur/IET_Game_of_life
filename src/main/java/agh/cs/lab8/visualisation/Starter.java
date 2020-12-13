package agh.cs.lab8.visualisation;

import javax.swing.*;

import javax.swing.*;

public class Starter {
    public JFrame menuFrame;

    public Starter() {

        menuFrame = new JFrame();
        menuFrame.setSize(250, 120);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null);

    }
    public void startSimulation(Integer[] defaultMapProperties){
        menuFrame.add(new StartingPanel(defaultMapProperties));
        menuFrame.setVisible(true);
    }
}