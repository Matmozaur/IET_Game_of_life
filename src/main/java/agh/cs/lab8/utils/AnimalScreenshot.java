package agh.cs.lab8.utils;

public class AnimalScreenshot {
    public int id;
    public int numberOfKids;
    public int numberOfDescendant;
    public int age;
    public int epoch;
    public int numberOfEpochs;

    public AnimalScreenshot(int id, int numberOfKids, int numberOfDescendant, int age, int epoch, int numberOfEpochs) {
        this.id = id;
        this.numberOfKids = numberOfKids;
        this.numberOfDescendant = numberOfDescendant;
        this.age = age;
        this.epoch = epoch;
        this.numberOfEpochs = numberOfEpochs;
    }
}
