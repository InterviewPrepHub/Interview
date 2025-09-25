package com.interview.Interview.Interview2025.Atlassian.Round3;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class Product {

    private final String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
