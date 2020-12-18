package agh.cs.lab8.visualisation;

import agh.cs.lab8.map_elements.Animal;
import agh.cs.lab8.maps.Jungle;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;


public class StatsPanel extends JPanel {

    public Jungle jungle;
    public Simulator simulator;
    private final int id;
    private final int n;
    private StringBuilder raport = new StringBuilder("");

    public StatsPanel(Jungle jungle, Simulator simulator,  int id, int n) {
        this.jungle = jungle;
        this.simulator = simulator;
        this.id = id;
        this.n = n;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize((int) (simulator.frame.getWidth() * 0.25), (int)(simulator.frame.getHeight()*0.5));
        this.setLocation(0, 0);
        int width = this.getWidth();
        int height = this.getHeight(); //38 is toolbar size
        g.setColor(Color.gray);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        Font drawFont = new Font("Arial", Font.BOLD, 22);
        g.setFont(drawFont);

        DecimalFormat f = new DecimalFormat("##.00");
        String tmp = "Day "+jungle.day;
        raport.append(tmp).append('\n');
        g.drawString(tmp, 0,20);
        drawFont = new Font("Arial", Font.BOLD, 18);
        g.setFont(drawFont);
        tmp = "Number of living animals: ";
        raport.append(tmp).append('\n');
        g.drawString(tmp, 0,50);
        tmp = ""+jungle.getAnimals().size();
        raport.append(tmp).append('\n');
        g.drawString(tmp, 10,70);
        tmp = "Number of dead animals: ";
        raport.append(tmp).append('\n');
        g.drawString(tmp, 0,100);
        tmp = ""+jungle.getGraveyard().size();
        raport.append(tmp).append('\n');
        g.drawString(tmp, 10,120);
        tmp = "Number of plants: ";
        raport.append(tmp).append('\n');
        g.drawString(tmp, 0,150);
        tmp = ""+jungle.getPlants().size();
        raport.append(tmp).append('\n');
        g.drawString(tmp, 10,170);
        tmp = "Mean life span among dead: ";
        raport.append(tmp).append('\n');
        g.drawString(tmp, 0,200);
        tmp = ""+f.format(jungle.getGraveyard().stream()
                .mapToInt(Animal::getDaysAlive)
                .average()
                .orElse(0));
        raport.append(tmp).append('\n');
        g.drawString(tmp, 10,220);
        tmp = "Mean energy: ";
        raport.append(tmp).append('\n');
        g.drawString(tmp, 0,250);
        tmp = ""+f.format(jungle.getAnimals().stream()
                .mapToInt(Animal::getEnergy)
                .average()
                .orElse(0));
        raport.append(tmp).append('\n');
        g.drawString(tmp, 10,270);
        tmp = "Mean number of kids: ";
        raport.append(tmp).append('\n');
        g.drawString(tmp, 0,300);
        tmp = ""+f.format(jungle.getAnimals().stream()
                .mapToInt(a -> a.getKids().size())
                .average()
                .orElse(0));
        raport.append(tmp).append('\n');
        g.drawString(tmp, 10,320);
        tmp = "Most common genotype: ";
        raport.append(tmp).append('\n');
        g.drawString(tmp, 0,350);
        drawFont = new Font("Arial", Font.BOLD, 16);
        g.setFont(drawFont);
        String dna = jungle.mostCommonGenotype();
        raport.append(dna).append('\n');
        if(dna.length() > 0) {
            g.drawString("" + dna.substring(1, 31), 10, 370);
            g.drawString("" + dna.substring(31, 63), 10, 390);
            g.drawString("" + dna.substring(63, 95), 10, 410);
        }
        if(n == jungle.day) {
            try {
                File myObj = new File("raport"+id+".txt");
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            try {
                FileWriter myWriter = new FileWriter("raport"+id+".txt");
                myWriter.write(raport.toString());
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

}
