package com.ranjit.pizza.assignment.enums;

public enum Side {
    COLD_DRINK(55),
    MOUSSE_CAKE(90);

    private final double price;

    Side(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
