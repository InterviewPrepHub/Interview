package com.interview.Interview.Interview2025.Stripe.Round1;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

Order
{
  "country": "US", // or "CA" for the CA order
  "items": [
    {"product": "mouse", "quantity": 20},
    {"product": "laptop", "quantity": 5}
  ]
}

Shipping Cost

{
  "US": [
    {"product": "mouse", "cost": 550},
    {"product": "laptop", "cost": 1000}
  ],
  "CA": [
    {"product": "mouse", "cost": 750},
    {"product": "laptop", "cost": 1100}
  ]
}


shipping first item - expensive than shipping additional items
upadte shippong matrix to reflect quntity based discount

Order
{
  "country": "US", // or "CA" for the CA order
  "items": [
    {"product": "mouse", "quantity": 20},
    {"product": "laptop", "quantity": 5}
  ]
}

{
  "US": [
    {
      "product": "mouse",
      "costs": [
        {
          "minQuantity": 0,
          "maxQuantity": null,
          "cost": 550
        }
      ]
    },
    {
      "product": "laptop",
      "costs": [
        {
          "minQuantity": 0,
          "maxQuantity": 2,
          "cost": 1000
        },
        {
          "minQuantity": 3,
          "maxQuantity": 4,
          "cost": 950
        },
        {
          "minQuantity": 5,
          "maxQuantity": null,  //2*1000+2*950+1*900
          "cost": 900
        }
      ]
    }
  ],
  "CA": [
    {
      "product": "mouse",
      "costs": [
        {
          "minQuantity": 0,
          "maxQuantity": null,
          "cost": 750
        }
      ]
    },
    {
      "product": "laptop",
      "costs": [
        {
          "minQuantity": 0,
          "maxQuantity": 2,
          "cost": 1100
        },
        {
          "minQuantity": 3,
          "maxQuantity": null,
          "cost": 1000
        }
      ]
    }
  ]
}

 */
public class Solution {

    public static void main(String[] args) {
        /*var shippingCosts = new HashMap<String, List<ItemCost>>();
        shippingCosts.put("US", List.of(new ItemCost("mouse", 550), new ItemCost("laptop", 1000)));
        shippingCosts.put("CA", List.of(new ItemCost("mouse", 750), new ItemCost("laptop", 1100)));

        Order usOrder = new Order("US", List.of(new LineItem("mouse", 20), new LineItem("laptop", 5)));
        Order caOrder = new Order("CA", List.of(new LineItem("mouse", 20), new LineItem("laptop", 5)));

        System.out.println(calculateShippingCost(usOrder, shippingCosts));
        System.out.println(calculateShippingCost(caOrder, shippingCosts));*/
    }

    private static int calculateShippingCost(Order order, HashMap<String, List<ItemCost>> shippingCosts) {

        int totalCost = 0;
        Map<String, Integer> costMap = new HashMap<>();

        List<ItemCost> itemCostsByCountry = shippingCosts.get(order.country);
        if(itemCostsByCountry == null) {
            throw new IllegalArgumentException("shipping cost is not available");
        }
        for (ItemCost itemCost : itemCostsByCountry) {
            costMap.put(itemCost.product, itemCost.cost);
        }

        List<LineItem> items = order.items;

        for (LineItem item : items) {
            if(costMap.containsKey(item.product)) {
                Integer costPerUnit = costMap.get(item.product);
                if(costPerUnit == null) {
                    throw new IllegalArgumentException("The shipping cost of product is not defined");
                }
                if (item.quantity < 0) {
                    throw new IllegalArgumentException("quantity cannot be negative");
                }
                totalCost += item.quantity * costPerUnit;
            }
        }

//        System.out.println(totalCost);
        return totalCost;

    }


    /*private static int calculateShippingCost(Order order, Map<String, ProductShipping> shippingCost) {

        productCostMap -> Map<prodcut, List<ProductShipping>>

                for (LineItem item : order.itenms) {

                    perUnitCost = findApplicaableCost

                     totalCost += item *  perUnitCost ;


                    int findApplicaableCost:
                    for(ShippingTier st : tiers) {
                        boolean aboveMin = quantity >= tier.min;
                        boolean belowMax = quantity <= tier.max || tier.max == null

                                if(aboveMin && belowMax) {
                                    tier.cost
                                }
                    }
                }

    }*/

    class Order {
        public String country;
        public List<LineItem> items;

        public Order(String country, List<LineItem> items) {
            this.country = country;
            this.items = items;
        }
    }

    class LineItem {
        public String product;
        public int quantity;

        public LineItem(String product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }
    }

    class ItemCost {
        public String product;
        public int cost;

        public ItemCost(String product, int cost) {
            this.product = product;
            this.cost = cost;
        }
    }

    class ShippingCost {
        Map<String, List<ItemCost>> shippingCost;

        public ShippingCost(Map<String, List<ItemCost>> shippingCost) {
            this.shippingCost = shippingCost;
        }
    }

    class Shippingtier {
        Integer min;
        Integer max;
        int cost;

        public Shippingtier(Integer min, Integer max, int cost) {
            this.min = min;
            this.max = max;
            this.cost = cost;
        }
    }

    class ProductShipping {
        String product;
        List<Shippingtier> cost;
    }

}


/*
min <= quantity <=max

quantity * cost


calculate_shipping_cost(order_us, shipping_cost) == 15800 // 20 * 550 + 2 * 1000 + 2 * 950 + 1 * 900 = 15800
calculate_shipping_cost(order_ca, shipping_cost) == 20200 // 20 * 750 + 2 * 1100 + 3 * 1000 = 20200
 */




