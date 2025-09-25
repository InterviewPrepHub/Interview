package com.interview.Interview.Interview2025.Atlassian.Round3;

import lombok.Getter;
import org.interview.prep.code.Interview2025.Atlassian.Round3.Subscription;

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

    public String getName() {
        return name;
    }

    public Map<Product, Subscription> getSubscriptionMap() {
        return subscriptionMap;
    }
}
