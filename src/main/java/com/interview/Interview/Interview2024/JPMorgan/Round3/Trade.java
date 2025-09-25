package com.interview.Interview.Interview2024.JPMorgan.Round3;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public final class Trade/* implements Comparable<Trade>*/ {

    private final String id;
    private final double amount;
    private final List<String> attributes;

    public Trade(String id, double amount, List<String> attributes) {
        this.id = id;
        this.amount = amount;
        this.attributes = Collections.unmodifiableList(List.copyOf(attributes));
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public List<String> getAttributes() {
        return attributes;
    }


    @Override
    public String toString() {
        return "Trade{id="+id+" , amount = "+amount+" , attributes"+attributes+")";
    }

    /*@Override
    public int compareTo(Trade o1) {
        return Double.compare(o1.amount, this.amount);
    }*/

    public static Comparator <Trade> amountAsComp = new Comparator<Trade>() {

        @Override
        public int compare(Trade o1, Trade o2) {
            return Double.compare(o1.amount, o2.amount);
        }
    };

    public static Comparator <Trade> idAsComp = new Comparator<Trade>() {

        @Override
        public int compare(Trade o1, Trade o2) {
            return o1.id.compareTo(o2.id);
        }
    };
}
