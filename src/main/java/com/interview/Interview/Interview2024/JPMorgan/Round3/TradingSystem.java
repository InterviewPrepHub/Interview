package com.interview.Interview.Interview2024.JPMorgan.Round3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TradingSystem {

    private final List<Trade> trades;

    public TradingSystem(){
        this.trades = new ArrayList<>();
    }

    public void addTrade(Trade trade) {
        trades.add(trade);
    }

    public void sortTradeByAmount() {
//        Collections.sort(trades);

        Collections.sort(trades, Trade.amountAsComp);
    }

    public void sortTradeById() {
        Collections.sort(trades, Trade.idAsComp);
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public void printTrades() {
        for(Trade t : trades) {
            System.out.println(t);
        }
    }
}
