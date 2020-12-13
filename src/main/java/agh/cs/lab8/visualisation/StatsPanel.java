package agh.cs.lab8.visualisation;

import agh.cs.lab8.map_elements.Animal;
import agh.cs.lab8.map_elements.Plant;
import agh.cs.lab8.maps.Jungle;
import agh.cs.lab8.utils.Vector2d;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class StatsPanel extends JPanel {

    public Jungle jungle;
    public Simulator simulator;

    public StatsPanel(Jungle jungle, Simulator simulator) {
        this.jungle = jungle;
        this.simulator = simulator;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize((int) (simulator.frame.getWidth() * 0.25), (int)(simulator.frame.getHeight()*0.6));
        this.setLocation(0, 0);
//        int width = this.getWidth();
//        int height = this.getHeight(); //38 is toolbar size
//        g.setColor(Color.gray);
//        g.fillRect(0, 0, width, height);
//        g.setColor(Color.BLACK);
//        Font drawFont = new Font("Arial", Font.BOLD, 18);
//        g.setFont(drawFont);
//        g.drawString("Number of living animals: ", 0,10);
//        g.drawString(""+jungle.getAnimals().size(), 10,30);
//        g.drawString("Number of dead animals: ", 0,60);
//        g.drawString(""+jungle.getAnimals().size(), 10,80);
//        g.drawString("Number of plants: ", 0,110);
//        g.drawString(""+jungle.getAnimals().size(), 10,130);
//        g.drawString("Most common genotype: ", 0,160);
//        g.drawString(""+jungle.getAnimals().size(), 10,180);
//        g.drawString("Mean energy: ", 0,210);
//        g.drawString(""+jungle.getAnimals().size(), 10,230);
//        g.drawString("Mean number of kids: ", 0,260);
//        g.drawString(""+jungle.getAnimals().size(), 10,280);
//        g.drawString("Mean life span among dead: ", 0,310);
//        g.drawString(""+jungle.getAnimals().size(), 10,330);

    }

}
