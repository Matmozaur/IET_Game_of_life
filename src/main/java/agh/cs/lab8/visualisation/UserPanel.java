package agh.cs.lab8.visualisation;

import agh.cs.lab8.map_elements.Animal;
import agh.cs.lab8.maps.Jungle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class UserPanel extends JPanel {

    public Jungle jungle;
    public Simulator simulator;
    private final JButton stopButton;
    private final JTextField animalID;
    private final JTextField nEpochs;
    private final JButton infoButton;
    private final JButton delayedInfoButton;

    public UserPanel(Jungle jungle, Simulator simulator) {
        setLayout(null);
        this.jungle = jungle;
        this.simulator = simulator;
        stopButton = new JButton("Stop");
        stopButton.addActionListener(this::stopAction);
        Rectangle r = new Rectangle(0, 0, 300, 60);
        stopButton.setBounds(r);
        add(stopButton,BorderLayout.CENTER);

        animalID = new JTextField("0");
        animalID.addActionListener(this::stopAction);
        Rectangle r3 = new Rectangle(75, 120, 150, 50);
        animalID.setBounds(r3);
        animalID.setEnabled(false);
        add(animalID,BorderLayout.CENTER);

        infoButton = new JButton("Show info");
        infoButton.addActionListener((this::showInfo));
        Rectangle r2 = new Rectangle(0, 180, 300, 60);
        infoButton.setBounds(r2);
        infoButton.setEnabled(false);
        add(infoButton,BorderLayout.CENTER);

        nEpochs = new JTextField("10");
        nEpochs.addActionListener(this::stopAction);
        Rectangle r4 = new Rectangle(75, 300, 150, 50);
        nEpochs.setBounds(r4);
        nEpochs.setEnabled(false);
        add(nEpochs,BorderLayout.CENTER);

        delayedInfoButton = new JButton("Follow animal");
        delayedInfoButton.addActionListener((this::followAnimal));
        Rectangle r5 = new Rectangle(0, 360, 300, 60);
        delayedInfoButton.setBounds(r5);
        delayedInfoButton.setEnabled(false);
        add(delayedInfoButton,BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize((int) (simulator.frame.getWidth() * 0.25), (int)(simulator.frame.getHeight()*0.5));
        this.setLocation(0, (int)(simulator.frame.getHeight()*0.5));
        int width = this.getWidth();
        int height = this.getHeight();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, width, height);
        Font drawFont = new Font("Arial", Font.BOLD, 20);
        g.setFont(drawFont);
        g.setColor(Color.WHITE);
        g.drawString("Choose animal by ID", 0,100);
        g.drawString("Choose number of epochs", 0,280);
    }

    public void stopAction(ActionEvent e) {
        if (!simulator.timer.isRunning()) {
            stopButton.setText("Stop");
            simulator.timer.start();
            animalID.setEnabled(false);
            infoButton.setEnabled(false);
            nEpochs.setEnabled(false);
            delayedInfoButton.setEnabled(false);
        } else {
            stopButton.setText("Start");
            simulator.timer.stop();
            animalID.setEnabled(true);
            infoButton.setEnabled(true);
            nEpochs.setEnabled(true);
            delayedInfoButton.setEnabled(true);
        }
    }

    public void showInfo(ActionEvent e) {
        try{
            int id = Integer.parseInt(animalID.getText());
            String info = jungle.getAnimalInfo(id);
            JOptionPane.showMessageDialog(null, info);
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Wrong input format!");
        }
    }

    public void followAnimal(ActionEvent e) {
        if(simulator.followFlag) {
            JOptionPane.showMessageDialog(null, "You already following one animal!");
        }
        else{
            try{
                int id = Integer.parseInt(animalID.getText());
                Animal animal = jungle.getAnimals().stream().filter(obj -> obj.getId() == id).findFirst().orElse(null);
                if(animal == null) {
                    JOptionPane.showMessageDialog(null, "There is no such animal alive!");
                }
                else {
                    int n = Integer.parseInt(nEpochs.getText());
                    simulator.follow(animal, n);
                    stopButton.setText("Stop");
                    simulator.timer.start();
                    animalID.setEnabled(false);
                    infoButton.setEnabled(false);
                    nEpochs.setEnabled(false);
                    delayedInfoButton.setEnabled(false);
                }
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Wrong input format!");
            }
        }
    }
}
