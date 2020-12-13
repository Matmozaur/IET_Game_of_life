package agh.cs.lab8.visualisation;

import agh.cs.lab8.map_elements.Animal;
import agh.cs.lab8.map_elements.Plant;
import agh.cs.lab8.maps.Jungle;
import agh.cs.lab8.utils.Vector2d;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class WorldPanel extends JPanel {

    public Jungle jungle;
    public Simulator simulator;

    public WorldPanel(Jungle jungle, Simulator simulator) {
        this.jungle = jungle;
        this.simulator = simulator;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize((int) (simulator.frame.getWidth() * 0.75), simulator.frame.getHeight());
        this.setLocation((int) (0.25 * simulator.frame.getWidth()), 0);
        int width = this.getWidth();
        int height = this.getHeight(); //38 is toolbar size
        int widthScale = width / jungle.getWidth();
        int heightScale = height / jungle.getHeight();
        g.setColor(new Color(150, 204, 83));
        g.fillRect(0, 0, width, height);
        g.setColor(new Color(0, 160, 7));
        g.fillRect(jungle.getJungleLeftDown().getX() * widthScale,
                jungle.getJungleLeftDown().getY() * heightScale,
                (jungle.getJungleRightUp().getX() - jungle.getJungleLeftDown().getX()) * widthScale,
                (jungle.getJungleRightUp().getY() - jungle.getJungleLeftDown().getY()) * heightScale);

        for (Map.Entry<Vector2d,Plant> entry : jungle.getPlants().entrySet()){
            g.setColor(Color.green);
            int y = entry.getKey().getY() * heightScale;
            int x = entry.getKey().getX() * widthScale;
            g.fillRect(x, y, widthScale, heightScale);
        }
        for (Animal a : jungle.getAnimals()) {
            g.setColor(Color.red);
            int y = a.getPosition().getY() * heightScale;
            int x = a.getPosition().getX() * widthScale;
            g.fillOval(x, y, widthScale, heightScale);
        }
    }

}
