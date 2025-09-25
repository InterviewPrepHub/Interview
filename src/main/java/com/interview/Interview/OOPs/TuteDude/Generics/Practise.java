package com.interview.Interview.OOPs.TuteDude.Generics;

import org.interview.prep.code.testing.Generics.Example.Apple;
import org.interview.prep.code.testing.Generics.Example.Fruits;

import java.util.ArrayList;

public class Practise {

    public static void main(String[] args) {
        GenericPractise<Integer, String> genericPractise = new GenericPractise<>(1, "Hello");

        Integer x = genericPractise.getFirst();
        System.out.println(x);

        String y = genericPractise.getSecond();
        System.out.println(y);


        GenericPractise<String, String> genericPractise1 = new GenericPractise<>("Hello", "World");

        String a = genericPractise1.getFirst();
        System.out.println(a);
        String b = genericPractise1.getSecond();
        System.out.println(b);

        ArrayList<String> a1 = new ArrayList<>();
        a1.add("Hello");
        a1.add("World");

        ArrayList<String> a2 = new ArrayList<>();
        a2.add("Hi");
        a2.add("There");

        ArrayList<Integer> a3 = new ArrayList<>();
        a3.add(1);
        a3.add(2);

        ArrayList<Integer> a4 = new ArrayList<>();
        a4.add(3);
        a4.add(4);


        Practise p = new Practise();
        /*ArrayList<String> result = p.function(a1, a2);

        System.out.println();
        System.out.println(result);*/

        ArrayList<Integer> result1 = p.function(a3, a4);
        System.out.println();
        System.out.println(result1);



        //Case 3:
        ArrayList<Object> ar1 = new ArrayList<>();
        ArrayList<String> ar2 = new ArrayList<>();

//        ar1 = ar2; -> not allowed
        ArrayList<?> ar3 = new ArrayList<>();   // ? wildcard says that any datatype can be used
        ar3 = ar1;

        ArrayList<? extends Fruits> ar4 = new ArrayList<>();
        ArrayList<Apple> ar5 = new ArrayList<>();

        ar4 = ar5; // allowed because Apple is a subclass of Fruits

        ArrayList<Object> ar6 = new ArrayList<>();
//        ar4 = ar6; // not allowed because Object is a superclass of Fruits

        ArrayList<? super Fruits> ar7 = new ArrayList<>();
        ar7 = ar6; // allowed because Object is a superclass of Fruits
    }


    public <E> ArrayList<E>  function(ArrayList<E> a1, ArrayList<E> a2) {
        ArrayList<E> result = new ArrayList<>();
        result.addAll(a1);
        result.addAll(a2);
        return result;
    }
}
