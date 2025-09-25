package com.interview.Interview.OOPs.TuteDude.LambdaAndStreams;

public class Smartphone {

    private String brand;
    private String model;
    private String color;
    private String version;

    public Smartphone(String brand, String model, String color, String version) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.version = version;
    }

    public static void main(String[] args) {
        Smartphone smartphone;
        smartphone = new Smartphone("iphone", "14", "black", "pro max");
        smartphone.getDetails();

    }

    private void getDetails() {
        System.out.println(brand + " " + model + " " + color + " " + version);
    }
}
