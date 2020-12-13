package agh.cs.lab8;

import agh.cs.lab8.visualisation.Starter;

import java.io.FileNotFoundException;

public class World {

    public static void main(String[] args) {

        try {
//            PropertiesLoader properties = PropertiesLoader.loadPropFromFile();
            Integer[] defaultMapProperties = {
                    10,10,4,4,6,6,
                    10,10,
                    100,10,1,
                    50,
                    100
            };
            Starter menu = new Starter();
            menu.startSimulation(defaultMapProperties);

        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
            return;
        }
    }

}