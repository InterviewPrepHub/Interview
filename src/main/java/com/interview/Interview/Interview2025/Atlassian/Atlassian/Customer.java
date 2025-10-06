package com.example.ClearFit.Atlassian;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Customer {

    private final String name;
    private Map<Product, Subscription> subscriptionMap;

    public Customer(String name) {
        this.name = name;
        this.subscriptionMap = new HashMap<>();
    }

    public void addSubscription(Subscription subscription) {
        subscriptionMap.put(subscription.getProduct(), subscription);
    }
}
