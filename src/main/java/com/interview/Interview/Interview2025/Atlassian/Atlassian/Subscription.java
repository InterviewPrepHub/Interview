package com.example.ClearFit.Atlassian;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Subscription {

    private final Product product;
    private final PlanId planId;
    private final LocalDate startDate;
}
