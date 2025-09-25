package com.interview.Interview.Interview2025.Atlassian.Round3;

import java.time.LocalDate;

public class Subscription {

    private final Product product;
    private final PlanId planId;
    private final LocalDate startDate;

    public Subscription(Product product, PlanId planId, LocalDate startDate) {
        this.product = product;
        this.planId = planId;
        this.startDate = startDate;
    }

    public Product getProduct() {
        return product;
    }

    public PlanId getPlanId() {
        return planId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
}
