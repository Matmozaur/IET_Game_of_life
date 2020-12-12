package agh.cs.lab8;

import agh.cs.lab8.map_elements.Animal;
import agh.cs.lab8.maps.*;
import agh.cs.lab8.utils.Vector2d;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class World {

    public static void main(String[] args) {
        Map<Integer, List<Integer>> m =new LinkedHashMap<>();
        m.put(0, new LinkedList<>());
        System.out.println(m.get(0));
        System.out.println(m.get(1));
    }
}
