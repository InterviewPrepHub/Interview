package com.interview.Interview.OOPs.TuteDude.Collections;

import org.interview.prep.code.testing.Collections.Smartphone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Collection {

    public static void main(String[] args) {

        HashSet<Smartphone> smartphones = new HashSet<>();
        smartphones.add(new Smartphone("iphone", 100000));
        smartphones.add(new Smartphone("watch", 50000));
        smartphones.add(new Smartphone("watch", 60000));

//        for (Smartphone smartphone : smartphones) {
//            System.out.println(smartphone);
//        }

        ArrayList<Smartphone> list = new ArrayList<>(smartphones);
        Collections.sort(list);
        for (Smartphone smartphone : list) {
            System.out.println(smartphone);
        }
    }
}
