package agh.cs.lab8.visualisation;

import agh.cs.lab8.map_elements.Animal;
import agh.cs.lab8.maps.Jungle;
import agh.cs.lab8.utils.AnimalScreenshot;

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
    public boolean followFlag=false;
    private AnimalScreenshot screenshot;
    private Animal followedAnimal;


    public Simulator(Jungle jungle, int delay, int id, int n) {
        this.jungle = jungle;
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
        if(followFlag) {
            if(jungle.day >= screenshot.epoch+screenshot.numberOfEpochs) {
                String message = getUpdate();
                JOptionPane.showMessageDialog(null, message);
                followFlag = false;
            }
        }
    }

    private String getUpdate() {
        StringBuilder report = new StringBuilder("");
        report.append("Id: ").append(followedAnimal.getId()).append("\n");
        report.append("New kids: ").append(followedAnimal.getKids().size() - screenshot.numberOfKids).append("\n");
        report.append("New descendant: ").append(followedAnimal.numberOfDescendant() -
                screenshot.numberOfDescendant);
        if(followedAnimal.getDaysAlive()<screenshot.numberOfEpochs+screenshot.age-1){
            report.append("\n");
            report.append("Died in epoch: ").append(screenshot.epoch-screenshot.age+followedAnimal.getDaysAlive())
                    .append("\n");
            report.append("Dead in the age of: ").append(followedAnimal.getDaysAlive());
        }
        return report.toString();
    }

    public void follow(Animal animal, int n) {
        followFlag = true;
        screenshot = new AnimalScreenshot(animal.getId(), animal.getKids().size(), animal.numberOfDescendant(),
                animal.getDaysAlive(), jungle.day, n);
        followedAnimal = animal;
    }

}