package agh.cs.lab8;

import agh.cs.lab8.utils.ConfigParser;
import agh.cs.lab8.visualisation.Starter;

import java.io.IOException;


public class World {

    public static void main(String[] args) {

        try {
            Integer[] mapProperties = ConfigParser.readConfig("src/main/java/agh/cs/lab8/params.json");
            Starter starter = new Starter();
            starter.startSimulation(mapProperties);

        } catch (IllegalArgumentException | IOException ex) {
            System.out.println(ex);
        }
    }

}