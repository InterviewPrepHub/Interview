package com.interview.Interview.OOPs.TuteDude.Generics.Example;

import java.util.ArrayList;

public class GenericFruit {

    public static void main(String[] args) {

        ArrayList<Apple> a1 = new ArrayList<>();
        a1.add(new Apple());
        a1.add(new Apple());

        GenericFruit gf = new GenericFruit();
        gf.fxn(a1);

    }

    /*public void fxn(ArrayList<Apple> arr1) {
        for (Apple a : arr1) {
            a.eat();
        }
    }*/

    //fruit is parent class of apple, so if we just keep fruit then we wont be able to give Array list of apple in function
    public void fxn(ArrayList<? extends Fruits> arr1) {
        for (Apple f : (ArrayList<Apple>)arr1) {
            f.eat();
        }

    }
}
