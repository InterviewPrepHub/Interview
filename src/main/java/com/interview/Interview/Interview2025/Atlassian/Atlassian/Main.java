package com.example.ClearFit.Atlassian;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        //create product
        Product jira = new Product("Jira");

        //create customer
        Customer c1 = new Customer("C1");

        //create subscription
        Subscription jiraSub = new Subscription(jira, PlanId.BASIC, LocalDate.parse("2021-03-10"));
        c1.addSubscription(jiraSub);

        Map<Product, Subscription> subMap = c1.getSubscriptionMap();
        Subscription sub = subMap.get(jira);
        System.out.println(sub.getPlanId());
        System.out.println(sub.getStartDate());


        //create pricing map
        Map<PlanId,Double> pricing = Map.of(PlanId.BASIC, 9.99, PlanId.STANDARD, 49.99, PlanId.PREMIUM, 249.99);

        //create cost explorer
        CostExplorer costExplorer = new CostExplorerImpl(jiraSub, pricing);

        List<Double> monthlyCosts = costExplorer.monthlyCostList();
        System.out.println("List of monthly cost");
        for (int i = 0; i < monthlyCosts.size(); i++) {
            System.out.print(monthlyCosts.get(i)+", ");
        }

        Double annualCost = costExplorer.annualCost();
        System.out.println("annual cost : "+annualCost);

    }
}
