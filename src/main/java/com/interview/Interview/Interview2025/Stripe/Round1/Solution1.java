package com.interview.Interview.Interview2025.Stripe.Round1;

import java.util.List;
import java.util.Map;

public class Solution1 {

    public static void product_shipping_cost(Order order, ShippingCosts shippingCosts) {
        int totalCost = 0;
        
        List<ProductCost> productCostList = shippingCosts.shippingCost.get(order.country);
        for (Items item : order.items) {
            for (ProductCost productCost : productCostList) {
                if(item.product.equals(productCost.product)) {
                    totalCost += productCost.cost * item.quantity;
                }
            }
        }

        System.out.println(totalCost);
    }

    public static void main(String[] args) {
        // Example usage
        List<Items> items = List.of(new Items("mouse", 20), new Items("laptop", 5));
        Order order_us = new Order("US", items);

        //(20*550)+(5000) = 16000
        List<ProductCost> usCosts = List.of(new ProductCost("mouse", 550), new ProductCost("laptop", 1000));
        List<ProductCost> caCosts = List.of(new ProductCost("mouse", 750), new ProductCost("laptop", 1100));
        ShippingCosts shippingCost = new ShippingCosts(Map.of("US", usCosts, "CA", caCosts));

        product_shipping_cost(order_us, shippingCost);
    }
}


class Order {
    public  String country;
    public  List<Items> items;

    public Order(String country, List<Items> items) {
        this.country = country;
        this.items = items;
    }
}

class Items {
    public  String product;
    public  int quantity;

    public Items(String product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}

class ShippingCosts {
    Map<String, List<ProductCost>> shippingCost;

    public ShippingCosts(Map<String, List<ProductCost>> shippingCost) {
        this.shippingCost = shippingCost;
    }
}

class ProductCost {
    public  String product;
    public  int cost;

    public ProductCost(String product, int cost) {
        this.product = product;
        this.cost = cost;
    }
}