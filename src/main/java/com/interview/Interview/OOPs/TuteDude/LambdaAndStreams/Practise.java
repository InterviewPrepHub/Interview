package com.interview.Interview.OOPs.TuteDude.LambdaAndStreams;

import org.interview.prep.code.testing.Collections.Smartphone;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Practise {

    public static void main(String[] args) {
        // Create an array of smartphones

        ArrayList<Smartphone> arr = new ArrayList<>();
        arr.add(new Smartphone("iPhone14", 80000));
        arr.add(new Smartphone("iPhone12", 60000));
        arr.add(new Smartphone("samsung galaxy", 30000));

        findPhones(arr);

        findPhonesLambda(arr, (sm) -> {
            if (sm.price>=50000 && sm.price<=80000) {
                return true;
            }
            return false;
        });

    }

    //find smartphones with price range
    //traditional way
    public static void findPhones(ArrayList<Smartphone> smp) {
        System.out.println("Using traditional way");
        for(Smartphone sm : smp) {
            if (sm.price >= 50000 && sm.price <= 80000) {
                System.out.println(sm);
            }
        }
    }

    //using lambda expression
    public static void findPhonesLambda(ArrayList<Smartphone> smp, Predicate<Smartphone> pd) {
        System.out.println();
        System.out.println("Using lambda expression");
        for (Smartphone sp:smp) {
            if(pd.test(sp)) {
                System.out.println(sp);
            }
        }
    }

}
