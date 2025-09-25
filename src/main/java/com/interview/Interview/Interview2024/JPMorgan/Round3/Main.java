package com.interview.Interview.Interview2024.JPMorgan.Round3;

import java.util.Arrays;

/*
Build trade app

Trade variables - id , amount, list of attributes
Trading system consists of list of trades
Want a function to sort trades in desc order of amount

List<Trade>
 */
public class Main {
    public static void main(String[] args) {
        TradingSystem t = new TradingSystem();


        //create ex trades
        Trade t1 = new Trade("T1", 100.00, Arrays.asList("a1","a2"));
        Trade t2 = new Trade("T2", 180.00, Arrays.asList("a3"));
        Trade t3 = new Trade("T3", 50.00, Arrays.asList("a4"));

        t3.getAttributes().add("a5");
        t.addTrade(t1);
        t.addTrade(t2);
        t.addTrade(t3);

        System.out.println("print before sorting");
        t.printTrades();

        System.out.println();
        System.out.println("print after sorting by amount");
        t.sortTradeByAmount();
        t.printTrades();

        System.out.println();
        System.out.println("print after sorting by id");
        t.sortTradeById();
        t.printTrades();

    }
}