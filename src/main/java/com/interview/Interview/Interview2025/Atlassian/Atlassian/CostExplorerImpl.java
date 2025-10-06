package com.example.ClearFit.Atlassian;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class CostExplorerImpl implements CostExplorer {

    private final Subscription subscription;
    private final Map<PlanId, Double> pricing;

    @Override
    public List<Double> monthlyCostList() {

        //Assuming that unit year : 1st Jan 2021 to 31st Dec 2021

        List<Double> cost = new ArrayList<>();
        Double monthlyRate = pricing.get(subscription.getPlanId());

        int year = subscription.getStartDate().getYear();
        int startMonth = subscription.getStartDate().getMonthValue();

        for (int month = 1; month <=12 ; month++) {
            if(month > startMonth) {
                cost.add(monthlyRate);
            } else if(month < startMonth) {
                cost.add(0.0);
            } else {
                //pro-rated
                LocalDate monthStart = LocalDate.of(year, month, 1);
                System.out.println("monthStart : "+monthStart);
                int daysInMonth = monthStart.lengthOfMonth();
                System.out.println("daysInMonth : "+daysInMonth);
                int activeDays = daysInMonth - subscription.getStartDate().getDayOfMonth();
                System.out.println("activeDays : "+activeDays);

                double prorated = (monthlyRate * activeDays)/daysInMonth;
                cost.add(prorated);
            }
        }
        return cost;
    }

    @Override
    public Double annualCost() {
        Double total = 0.0;

        for (Double cost : monthlyCostList()) {
            total += cost;
        }

        return total;
    }
}
