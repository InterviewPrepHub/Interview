package com.interview.Interview.OOPs.TuteDude.Generics;

public class GenericPractise<c1, c2> {

    private c1 first;
    private c2 second;

    public GenericPractise(c1 first, c2 second) {
        this.first = first;
        this.second = second;
    }

    public c1 getFirst() {
        return first;
    }

    public void setFirst(c1 first) {
        this.first = first;
    }

    public c2 getSecond() {
        return second;
    }

    public void setSecond(c2 second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "GenericPractise{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
