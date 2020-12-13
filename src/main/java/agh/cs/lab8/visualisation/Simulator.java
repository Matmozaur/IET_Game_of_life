package agh.cs.lab8.visualisation;

import agh.cs.lab8.maps.Jungle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Simulator implements ActionListener {

    public Jungle jungle;
    public JFrame frame;
    public WorldPanel worldPanel;
    public StatsPanel statsPanel;
    public UserPanel userPanel;
    public Timer timer;
    private int id;



    public Simulator(Jungle jungle, int delay, int id, int n) {
        this.jungle = jungle;
        this.id=id;
        timer = new Timer(delay, this);
        frame = new JFrame("Evolution Simulator");
        frame.setSize(1200, 900);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        worldPanel = new WorldPanel(jungle, this);
        worldPanel.setSize(new Dimension(1, 1));
        frame.add(worldPanel);
        statsPanel = new StatsPanel(jungle, this, id, n);
        statsPanel.setSize(new Dimension(1, 1));
        frame.add(statsPanel);
        userPanel = new UserPanel(jungle, this);
        userPanel.setSize(new Dimension(1, 1));
        frame.add(userPanel);
    }

    public void start() {
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        worldPanel.repaint();
        statsPanel.repaint();
        userPanel.repaint();
        jungle.removeDeadAnimals();
        jungle.moveAnimals();
        jungle.eating();
        jungle.mating();
        jungle.addNewPlants();
    }

}