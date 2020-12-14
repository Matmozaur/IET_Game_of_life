package agh.cs.lab8.utils;

import org.json.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ConfigParser {

    public static Integer[] translateJungle(int w, int h, double ratio) {
        double wallRatio = Math.sqrt(ratio);
        int x1 = (int)((w - w*wallRatio)/2);
        int y1 = (int)((h - h*wallRatio)/2);
        int x2 = (int)((w + w*wallRatio)/2);
        int y2 = (int)((h + h*wallRatio)/2);
        return new Integer[]{x1, y1, x2, y2};
    }

    public static Integer[] readConfig(String confPath) throws IOException {
        String content = Files.readString(Paths.get(confPath));
        JSONObject obj = new JSONObject(content);
        Integer[] jungleBorders = translateJungle(obj.getInt("width"),obj.getInt("height"),
                obj.getDouble("jungle_ratio"));
        return new Integer[]{
                obj.getInt("width"), obj.getInt("height"), jungleBorders[0], jungleBorders[1], jungleBorders[2],
                jungleBorders[3], obj.getInt("number_of_plants"), obj.getInt("plants_energy"),
                obj.getInt("start_energy"), obj.getInt("number_of_animals"), obj.getInt("move_energy"),
                obj.getInt("delay"), obj.getInt("days_before_snapshot")
        };
    }
}
