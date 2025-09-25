package com.interview.Interview.OOPs.TuteDude.Collections;


public class Smartphone implements Comparable<Smartphone>{

    public String brand;
    public int price;

    public Smartphone(String brand, int price) {
        this.brand = brand;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Smartphone{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Smartphone that = (Smartphone) obj;
        return price == that.price && brand.equals(that.brand);
    }

    @Override
    public int hashCode() {
        int result = brand.hashCode();
        result = 31 * result + price;
        return result;
    }

    @Override
    public int compareTo(Smartphone o) {
        return this.price - o.price;
    }
}
